package com.example.springbootlearning.user;

import com.example.springbootlearning.user.dto.UserMapper;
import com.example.springbootlearning.user.dto.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

  public User create(CreateUserRequest request) {
    User user = userMapper.toEntity(request);
    return userRepository.save(user);
  }
}
