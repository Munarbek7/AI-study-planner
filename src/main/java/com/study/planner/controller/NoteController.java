// 3. NoteController.java
package com.study.planner.controller;

import com.study.planner.model.Note;
import com.study.planner.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createNote(@PathVariable Long userId, @RequestBody Note note) {
        return ResponseEntity.ok(noteService.createNote(userId, note));
    }

    @GetMapping("/user/{userId}") // Должно быть так
    public ResponseEntity<?> getNotes(@PathVariable Long userId) {
        return ResponseEntity.ok(noteService.getNotesByUserId(userId));
    }
}