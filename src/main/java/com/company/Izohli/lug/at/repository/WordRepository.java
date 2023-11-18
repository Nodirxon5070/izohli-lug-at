package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Integer> {
    Optional<Word> findByWordIdAndDeletedAtIsNull(Integer wordId);
}
