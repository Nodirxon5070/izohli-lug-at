package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.DayWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DayWordRepository extends JpaRepository<DayWord,Integer> {
    Optional<DayWord> findByDayWordId(Integer dayWordId);

    Set<DayWord> findAllByWordId(Integer wordId);
}
