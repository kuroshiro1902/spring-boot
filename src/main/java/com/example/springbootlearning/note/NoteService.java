package com.example.springbootlearning.note;

import com.example.springbootlearning.note.dto.NoteDtoMapper;
import com.example.springbootlearning.note.dto.input.CreateNoteInput;
import com.example.springbootlearning.note.entity.Note;
import com.example.springbootlearning.note.repository.NoteRepository;
import com.example.springbootlearning.user.dto.input.FindUserInput;
import com.example.springbootlearning.user.entity.User;
import com.example.springbootlearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {
  private final NoteRepository noteRepository;
  private final NoteDtoMapper noteDtoMapper;
  private final UserService userService;

  public Note createOne(CreateNoteInput createNoteInput, String userId) {
    FindUserInput findUserInput = new FindUserInput();
    findUserInput.setId(userId);
    User user = userService.findOneOrThrow(findUserInput);
    return createOne(createNoteInput, user);
  }

  public Note createOne(CreateNoteInput createNoteInput, User user) {
    Note note = noteDtoMapper.toEntity(createNoteInput, user);
    return noteRepository.save(note);
  }
}
