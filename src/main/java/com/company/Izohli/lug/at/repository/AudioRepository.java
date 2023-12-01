package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends JpaRepository<Audio,Integer> {
    Optional<Audio> findByAudioId(Integer audioId);

    @Query(
            value = "select *\n" +
                    " from audio as w where audio_id=?1 and deleted_at is null ",
            nativeQuery = true
    )
    Audio findBYAudioId(Integer id);
}
