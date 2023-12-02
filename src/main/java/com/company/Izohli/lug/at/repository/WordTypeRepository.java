package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WordTypeRepository extends JpaRepository<WordType,Integer> {
    Optional<WordType> findByWordTypeId(Integer wordTypeId);
    Set<WordType> findAllByWordId(Integer wordId);

    @Query(
            value = "select *\n" +
                    " from word_type as w where type_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    WordType findWordTypeByTypeId(Integer typeId);
}
