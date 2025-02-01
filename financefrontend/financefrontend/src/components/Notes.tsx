import React, { useState } from 'react';
import '../App.css';

const Notes = () => {
    const [notes, setNotes] = useState<string[]>([]);
    const [noteInput, setNoteInput] = useState('');

    const addNote = () => {
        if (noteInput.trim()) {
            setNotes([...notes, noteInput.trim()]);
            setNoteInput('');
        }
    };

    return (
        <div className="notes-container">
            <h2>Notes</h2>
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
                {notes.map((note, index) => (
                    <li key={index}>{note}</li>
                ))}
            </ul>
        </div>
    );
};

export default Notes;
