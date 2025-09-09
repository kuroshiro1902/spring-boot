package com.example.springbootlearning.note.dto;

import com.example.springbootlearning.note.dto.input.CreateNoteInput;
import com.example.springbootlearning.note.entity.Note;
import com.example.springbootlearning.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NoteDtoMapper {
  public Note toEntity(CreateNoteInput createNoteInput, User user) {
    Note note = new Note();
    note.setTitle(createNoteInput.title());
    note.setContent(createNoteInput.content());
    note.setUser(user);
    return note;
  }
}
