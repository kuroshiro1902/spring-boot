package com.example.springbootlearning.user.dto.input;

import com.example.springbootlearning.common.constant.SortOrder;
import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserInput {

  @Size(max = 50, message = "Name length must be at most 50 characters")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String name;

  @Size(max = 100, message = "Email length must be at most 100 characters")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  private String email;

  @Min(value = 0, message = "Page must be greater than or equal to 0")
  @Max(value = 1000, message = "Page must be less than or equal to 1000")
  private Integer page = 0;

  @Min(value = 1, message = "Size must be greater than or equal to 1")
  @Max(value = 100, message = "Size must be less than or equal to 100")
  private Integer size = 10;

  @Size(max = 50, message = "SortBy length must be at most 50 characters")
  private String sortBy = "createdAt";

  private SortOrder order = SortOrder.DESC;
}
