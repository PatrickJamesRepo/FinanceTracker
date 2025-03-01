// src/components/Notes.tsx
import React, { useState, useEffect } from 'react';
import '../App.css';

interface Note {
    id?: number;
    content: string;
    date: string; // Format: YYYY-MM-DD
}

interface NotesProps {
    selectedDate: Date;
}

const Notes: React.FC<NotesProps> = ({ selectedDate }) => {
    const [notes, setNotes] = useState<Note[]>([]);
    const [noteInput, setNoteInput] = useState('');

    // Format the date as YYYY-MM-DD
    const formattedDate = selectedDate.toISOString().split('T')[0];

    // Fetch notes for the selected date
    useEffect(() => {
        fetch(`http://localhost:8081/api/notes?date=${formattedDate}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error fetching notes');
                }
                return response.json();
            })
            .then((data: Note[]) => setNotes(data))
            .catch(error => console.error('Error:', error));
    }, [formattedDate]);

    const addNote = () => {
        if (!noteInput.trim()) return;

        const newNote: Note = {
            content: noteInput.trim(),
            date: formattedDate
        };

        // Save note to backend
        fetch('http://localhost:8081/api/notes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newNote)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error saving note');
                }
                return response.json();
            })
            .then((savedNote: Note) => {
                setNotes([...notes, savedNote]);
                setNoteInput('');
            })
            .catch(error => console.error('Error:', error));
    };

    return (
        <div className="notes-container">
            <h2>Notes for {selectedDate.toDateString()}</h2>
            <div className="notes-input">
                <input
                    type="text"
                    value={noteInput}
                    onChange={(e) => setNoteInput(e.target.value)}
                    placeholder="Enter a note"
                />
                <button onClick={addNote}>Add</button>
            </div>
            <ul>
                {notes.map(note => (
                    <li key={note.id}>{note.content}</li>
                ))}
            </ul>
        </div>
    );
};

export default Notes;
