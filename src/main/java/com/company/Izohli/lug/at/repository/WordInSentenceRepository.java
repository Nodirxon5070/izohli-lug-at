package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.WordInSentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WordInSentenceRepository extends JpaRepository<WordInSentence,Integer> {
    Optional<WordInSentence> findByWordInSentenceId(Integer wordInSentenceId);

    Set<WordInSentence> findAllByWordId(Integer wordId);

    WordInSentence findBySentenceId(Integer sentenceId);

    @Query(
            value = "select *\n" +
                    " from word_in_sentence as w where sentence_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    WordInSentence findWordInSentence(Integer id);
}
