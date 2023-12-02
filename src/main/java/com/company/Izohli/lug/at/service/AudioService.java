package com.company.Izohli.lug.at.service;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAudioDto;
import com.company.Izohli.lug.at.dto.responseDto.AudioDto;
import com.company.Izohli.lug.at.module.Audio;
import com.company.Izohli.lug.at.repository.AudioRepository;
import com.company.Izohli.lug.at.repository.WordRepository;
import com.company.Izohli.lug.at.service.mapper.AudioMapper;
import com.company.Izohli.lug.at.service.mapper.WordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioService  {
    private final AudioMapper audioMapper;
    private final AudioRepository audioRepository;


    public ResponseDto<AudioDto> uploadAudio(MultipartFile audio) {
        try {
            return ResponseDto.<AudioDto>builder()
                    .message("OK")
                    .data(this.audioMapper.toDto(
                            this.audioRepository.save(
                                    Audio.builder()
                                            .path(saveFile(audio))
                                            .audioName(Objects.requireNonNull(audio.getOriginalFilename()).split("\\.")[0])
                                            .ext(Objects.requireNonNull(audio.getOriginalFilename()).split("\\.")[1])
                                            .build())))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<AudioDto>builder()
                    .code(-3)
                    .message("Audio while saving error message :: " + e.getMessage())
                    .build();
        }
    }



    public ResponseDto<AudioDto> downloadAudio(Integer audioId) {
        return this.audioRepository.findByAudioId(audioId)
                .map(audio1 -> {
                    try {
                        AudioDto dto = this.audioMapper.toDtoWithWord(audio1);
                        dto.setData(Files.readAllBytes(Path.of(audio1.getPath())));
                        return ResponseDto.<AudioDto>builder()
                                .success(true)
                                .message("OK")
                                .data(dto)
                                .build();
                    } catch (IOException e) {
                        return ResponseDto.<AudioDto>builder()
                                .code(-3)
                                .message("Audio while getting error message :: " + e.getMessage())
                                .build();
                    }

                })
                .orElse(ResponseDto.<AudioDto>builder()
                        .message(String.format("Audio with %d id is not found", audioId))
                        .code(-1)
                        .build());
    }


    public ResponseDto<AudioDto> updateAudio(MultipartFile audio, Integer audioId) {
        return this.audioRepository.findByAudioId(audioId)
                .map(audio1 -> {
                    try {

                        this.audioMapper.updateAudio(
                                RequestAudioDto.builder()
                                .path(saveFile(audio))
                                .audioName(Objects.requireNonNull(audio.getOriginalFilename()).split("\\.")[0])
                                .ext(Objects.requireNonNull(audio.getOriginalFilename()).split("\\.")[1])
                                .build(), audio1);
                        this.audioRepository.save(audio1);
                        return ResponseDto.<AudioDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.audioMapper.toDto(audio1))
                                .build();
                    } catch (IOException e) {
                        return ResponseDto.<AudioDto>builder()
                                .code(-3)
                                .message("Audio while updating error message :: " + e.getMessage())
                                .build();
                    }
                }).orElse(ResponseDto.<AudioDto>builder()
                        .message(String.format("Audio with %d id is not found", audioId))
                        .code(-1)
                        .build());
    }


    public ResponseDto<AudioDto> deleteAudio(Integer audioId) {
        return this.audioRepository.findByAudioId(audioId)
                .map(audio -> {
                    File file = new File(audio.getPath());
                    if (file.exists()) {
                        file.delete();
                        return ResponseDto.<AudioDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.audioMapper.toDto(audio))
                                .build();
                    } else {
                        return ResponseDto.<AudioDto>builder()
                                .code(-1)
                                .message(String.format("Audio with file path %s is not found", audio.getPath()))
                                .build();
                    }
                })
                .orElse(ResponseDto.<AudioDto>builder()
                        .message(String.format("Audio with file %d id is not found", audioId))
                        .code(-1)
                        .build());
    }




    public String saveFile(MultipartFile audio) throws IOException {
        String folder = String.format("%s/%s", "upload", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        File sFile = new File(folder);
        if (!sFile.exists()) {
            sFile.mkdirs();
        }
        String audioName = String.format("%s/%s", folder, UUID.randomUUID());
        Files.copy(audio.getInputStream(), Path.of(audioName));
        return audioName;
    }

}
