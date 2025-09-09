package com.example.springbootlearning.note.controller;

import com.example.springbootlearning.note.NoteService;
import com.example.springbootlearning.note.dto.input.CreateNoteInput;
import com.example.springbootlearning.note.entity.Note;
import com.example.springbootlearning.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
  private final NoteService noteService;

  @PostMapping()
  public Note signup(@Valid @RequestBody CreateNoteInput request, @AuthenticationPrincipal User user) {
    return noteService.createOne(request, user);
  }

}
