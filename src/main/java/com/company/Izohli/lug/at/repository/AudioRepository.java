package com.company.Izohli.lug.at.repository;

import com.company.Izohli.lug.at.module.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends JpaRepository<Audio,Integer> {
    Optional<Audio>findByAudioIdAndDeletedAtIsNull(Integer audioId);
}
