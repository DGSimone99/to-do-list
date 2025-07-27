package it.todolist.note;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class NoteResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate date;
    private boolean checked;
    private LocalDate addedAt;
}
