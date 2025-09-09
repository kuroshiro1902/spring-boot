package com.example.springbootlearning.note.dto.input;

import com.example.springbootlearning.common.deserializer.TrimStringDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public record CreateNoteInput(
  @Nullable
  @Size(max = 50, message = "Title length must be less than 50 characters")
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String title,

  @NotBlank(message = "Content must not be blank")
  @Size(max = 5000)
  @JsonDeserialize(using = TrimStringDeserializer.class)
  String content
) {
}
