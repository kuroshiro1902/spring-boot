package com.example.springbootlearning.user.dto.request;

import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FindUserRequest {
  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String id;

  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String name;

  @Size(max = 100)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String email;

  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String username;
}
