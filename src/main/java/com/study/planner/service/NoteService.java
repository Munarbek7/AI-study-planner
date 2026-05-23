// 3. NoteService.java
package com.study.planner.service;

import com.study.planner.model.Note;
import com.study.planner.model.User;
import com.study.planner.repository.NoteRepository;
import com.study.planner.repository.UserRepository;
import com.study.planner.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note createNote(Long userId, Note note) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
        note.setUser(user);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUserId(Long userId) {
        return noteRepository.findByUserId(userId);
    }
}