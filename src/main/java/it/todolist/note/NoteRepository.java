package it.todolist.note;


import it.todolist.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUser(AppUser appUser);
    Optional<Note> findByUserAndId(AppUser user, Long id);
}