package com.example.springbootlearning.user;

import com.example.springbootlearning.user.dto.UserMapper;
import com.example.springbootlearning.user.dto.requests.FindRequest;
import jakarta.persistence.EntityNotFoundException;
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

  public User findOneOrThrow(FindRequest request) {
    Specification<User> spec = (root, query, cb) -> null;
    if (request.id != null) {
      spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), request.id));
    }
    if (request.email != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("email")), request.email.toLowerCase()));
    }
    if (request.username != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("username")), request.username.toLowerCase()));
    }
    if (request.name != null) {
      spec = spec.and((root, query, cb) -> cb.equal(cb.lower(root.get("name")), request.name.toLowerCase()));
    }
    return userRepository.findOne(spec).orElseThrow(() -> new EntityNotFoundException("User not found"));
  }
}
