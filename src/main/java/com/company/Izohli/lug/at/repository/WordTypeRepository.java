package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordTypeRepository extends JpaRepository<WordType,Integer> {
    Optional<WordType>findByWordTypeIdAndDeletedAtIsNull(Integer wordTypeId);
}
