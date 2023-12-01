package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.WordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface WordTypeRepository extends JpaRepository<WordType,Integer> {
    Optional<WordType>findByWordTypeIdAndDeletedAtIsNull(Integer wordTypeId);
    Set<WordType> findByWordIdAndDeletedAtIsNull(Integer wordTypeId);

    @Query(
            value = "select *\n" +
                    " from word_type as w where type_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    WordType findWordTypeByTypeId(Integer id);
}
