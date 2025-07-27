package it.todolist.note;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public List<Note> findAllNotes() {
        return noteRepository.findAll();
    }

    public Note findNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nota non trovata con id: " + id));
    }

    public Note createNote(NoteRequest request) {
        Note note = noteMapper.toEntity(request);
        noteRepository.save(note);
        return note;
    }

    public void updateNote(Long id, NoteRequest request) {
        Note note = findNoteById(id);
        if (request.getName() != null) {
            note.setName(request.getName());
        }

        if (request.getDescription() != null) {
            note.setDescription(request.getDescription());
        }

        if (request.getDate() != null) {
            note.setDate(request.getDate());
        }

        note.setChecked(request.isChecked());

        noteRepository.save(note);
    }

    public void deleteNote (Long id) {
        Note note = findNoteById(id);
        noteRepository.delete(note);
    }
}