package it.todolist.note;

import it.todolist.user.model.AppUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> allNotes(@AuthenticationPrincipal AppUser user) {
        return ResponseEntity.ok(noteService.findAllNotes(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNote(@AuthenticationPrincipal AppUser user, @PathVariable Long id) {
        return ResponseEntity.ok(noteService.findNoteById(user, id));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> postNote(@AuthenticationPrincipal AppUser user, @RequestBody @Valid NoteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(user, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putNote(@AuthenticationPrincipal AppUser user, @PathVariable Long id, @RequestBody NoteRequest request) {
        noteService.updateNote(user, id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@AuthenticationPrincipal AppUser user, @PathVariable Long id) {
        noteService.deleteNote(user, id);
        return ResponseEntity.noContent().build();
    }
}
