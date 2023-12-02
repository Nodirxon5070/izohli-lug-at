package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    Optional<Note> findByNoteId(Integer noteId);
    Set<Note> findAllByWordId(Integer noteId);
}
