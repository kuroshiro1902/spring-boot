package com.example.springbootlearning.user.service;

import com.example.springbootlearning.auth.entity.Permission;
import com.example.springbootlearning.auth.service.PermissionService;
import com.example.springbootlearning.auth.service.UserPermissionService;
import com.example.springbootlearning.common.constant.SortOrder;
import com.example.springbootlearning.common.exception.NotFoundException;
import com.example.springbootlearning.user.dto.UserWithoutPassword;
import com.example.springbootlearning.user.dto.input.SearchUserInput;
import com.example.springbootlearning.user.repository.UserRepository;
import com.example.springbootlearning.user.dto.UserDtoMapper;
import com.example.springbootlearning.user.dto.input.FindUserInput;
import com.example.springbootlearning.user.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserDtoMapper userDtoMapper;
  private final PermissionService permissionService;
  private final UserPermissionService userPermissionService;

  public Page<UserWithoutPassword> search(SearchUserInput input) {
    Sort sort;
    if (input.getOrder() == SortOrder.DESC) {
      sort = Sort.by(input.getSortBy()).descending();
    } else {
      sort = Sort.by(input.getSortBy()).ascending();
    }

    Pageable pageable = PageRequest.of(
        input.getPage(),
        input.getSize(),
        sort
    );

    Specification<User> spec = (root, query, builder) -> null;

    if (input.getName() != null && !input.getName().trim().isEmpty()) {
      spec = spec.and((root, query, cb) -> 
          cb.like(cb.lower(root.get("name")), "%" + input.getName().toLowerCase() + "%"));
    }
    if (input.getEmail() != null && !input.getEmail().trim().isEmpty()) {
      spec = spec.and((root, query, cb) -> 
          cb.equal(cb.lower(root.get("email")), input.getEmail().toLowerCase()));
    }

    return userRepository.findAll(spec, pageable).map(userDtoMapper::toUserWithoutPassword);
  }

  @Transactional
  public User createOne(User user) {
    Permission defaultPermission = permissionService.findOneByCodeOrThrow("default_user");
    User savedUser = userRepository.save(user);
    userPermissionService.assignPermission(savedUser, defaultPermission);

    return savedUser;
  }

  public User findOneOrThrow(FindUserInput request) {
    Specification<User> spec = (root, query, cb) -> null;
    if (request.getId() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), request.getId()));
    }
    if (request.getEmail() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("email")), request.getEmail().toLowerCase()));
    }
    if (request.getUsername() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("username")), request.getUsername().toLowerCase()));
    }
    if (request.getName() != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("name")), request.getName().toLowerCase()));
    }
    return userRepository.findOne(spec).orElseThrow(() -> new NotFoundException("User not found"));
  }
}
