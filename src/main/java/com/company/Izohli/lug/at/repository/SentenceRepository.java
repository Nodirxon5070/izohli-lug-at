package com.company.Izohli.lug.at.repository;


import com.company.Izohli.lug.at.module.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentenceRepository extends JpaRepository<Sentence,Integer> {
    Optional<Sentence> findBySentenceId(Integer sentenceId);

    @Query(
            value = "select *\n" +
                    " from sentence as w where sentence_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    Sentence findBYSentenceId(Integer id);
}
