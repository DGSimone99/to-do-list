package it.todolist.note;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class NoteRequest {
    @NotBlank
    private String name;
    private String description;
    @FutureOrPresent
    private LocalDate date;
    private boolean checked;
}
