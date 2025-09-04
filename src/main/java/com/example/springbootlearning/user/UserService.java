package com.example.springbootlearning.user;

import com.example.springbootlearning.common.exceptions.NotFoundException;
import com.example.springbootlearning.user.dto.UserMapper;
import com.example.springbootlearning.user.dto.requests.FindUserRequest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Page<User> search(
      Optional<String> name,
      Optional<String> email,
      int page,
      int size,
      Optional<String> sortBy
  ) {
    Pageable pageable = PageRequest.of(
        page,
        size,
        Sort.by(sortBy.orElse("createdAt")).descending()
    );

    Specification<User> spec = (root, query, builder) -> null;

    if (name.isPresent()) {
      spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.get().toLowerCase() + "%"));
    }
    if (email.isPresent()) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("email")), email.get().toLowerCase()));
    }

    return userRepository.findAll(spec, pageable);
  }

  public User create(User user) {
    return userRepository.save(user);
  }

  public User findOneOrThrow(FindUserRequest request) {
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
