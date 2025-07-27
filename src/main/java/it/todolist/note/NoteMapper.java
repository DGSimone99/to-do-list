package it.todolist.note;

import it.todolist.user.model.AppUser;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoteMapper {
    public Note toEntity(AppUser user, NoteRequest request) {
        Note note = new Note();
        note.setName(request.getName());
        note.setDescription(request.getDescription());
        note.setDate(request.getDate());
        note.setAddedAt(LocalDate.now());
        note.setChecked(false);
        note.setUser(user);
        return note;
    }

    public NoteResponse toResponse(Note note) {
        NoteResponse response = new NoteResponse();
        response.setId(note.getId());
        response.setName(note.getName());
        response.setDescription(note.getDescription());
        response.setDate(note.getDate());
        response.setChecked(note.isChecked());
        response.setAddedAt(note.getAddedAt());
        return response;
    }
}
