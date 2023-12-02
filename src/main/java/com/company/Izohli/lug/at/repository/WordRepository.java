package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Integer> {
    Optional<Word> findWordByWordId(Integer wordId);

    @Query(
            value = "select *\n" +
                    " from word as w where word_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    Word findWordBYWordId(Integer id);

    Optional<Word> findAllByCategoryId(Integer id);
    Word findByAudioId(Integer id);

    Word findByCategoryId(Integer id);

}
