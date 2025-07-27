package it.todolist.note;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class NoteRequest {
    private String name;
    private String description;
    private LocalDate date;
    private boolean checked;
}
