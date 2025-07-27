package it.todolist.note;

import it.todolist.user.model.AppUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public List<NoteResponse> findAllNotes(AppUser user) {
        return noteRepository.findAllByUser(user).stream()
                .map(noteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Note findNoteEntityById(AppUser user, Long id) {
        return noteRepository.findByUserAndId(user, id).orElseThrow(() -> new EntityNotFoundException("Nota non trovata"));
    }

    public NoteResponse findNoteById(AppUser user, Long id) {
        return noteMapper.toResponse(noteRepository.findByUserAndId(user, id).orElseThrow(() -> new EntityNotFoundException("Nota non trovata")));
    }

    public NoteResponse createNote(AppUser user, NoteRequest request) {
        Note note = noteMapper.toEntity(user, request);
        noteRepository.save(note);
        return noteMapper.toResponse(note);
    }

    public void updateNote(AppUser user, Long id, NoteRequest request) {
        Note note = findNoteEntityById(user, id);
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

    public void deleteNote (AppUser user, Long id) {
        Note note = findNoteEntityById(user, id);
        noteRepository.delete(note);
    }
}