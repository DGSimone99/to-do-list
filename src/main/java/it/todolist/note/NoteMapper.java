package it.todolist.note;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class NoteMapper {
    public Note toEntity(NoteRequest request) {
        Note note = new Note();
        note.setName(request.getName());
        note.setDescription(request.getDescription());
        note.setDate(request.getDate());
        note.setAddedAt(LocalDate.now());
        note.setChecked(false);
        return note;
    }
}
