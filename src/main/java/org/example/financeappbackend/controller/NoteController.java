package org.example.financeappbackend.controller;

import org.example.financeappbackend.entity.Note;
import org.example.financeappbackend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Retrieve notes for a specific date (expects date in YYYY-MM-DD format)
    @GetMapping
    public ResponseEntity<List<Note>> getNotesByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Note> notes = noteService.getNotesByDate(date);
        return ResponseEntity.ok(notes);
    }

    // Create a new note for a specific date
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteService.saveNote(note);
        return ResponseEntity.ok(createdNote);
    }

}
