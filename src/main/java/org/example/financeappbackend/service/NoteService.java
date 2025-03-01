package org.example.financeappbackend.service;

import org.example.financeappbackend.entity.Note;
import org.example.financeappbackend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotesByDate(LocalDate date) {
        return noteRepository.findByDate(date);
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    // Optional: methods for updating or deleting notes can be added here
}
