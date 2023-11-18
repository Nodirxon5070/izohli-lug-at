package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.WordInSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordInSentenceRepository extends JpaRepository<WordInSentence,Integer> {
    Optional<WordInSentence>findByWordInSentenceIdAndDeletedAtIsNull(Integer wordInSentenceId);
}
