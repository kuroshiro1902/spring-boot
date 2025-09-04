package com.example.springbootlearning.user.dto.requests;

import com.example.springbootlearning.base.deserializers.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FindRequest {
  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  public String id;

  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  public String name;

  @Size(max = 100)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  public String email;

  @Size(max = 50)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  public String username;
}
