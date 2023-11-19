package com.company.Izohli.lug.at.dto.requestDto;

import com.company.Izohli.lug.at.dto.responseDto.DayWordDto;
import com.company.Izohli.lug.at.dto.responseDto.NoteDto;
import com.company.Izohli.lug.at.dto.responseDto.WordInSentenceDto;
import com.company.Izohli.lug.at.dto.responseDto.WordTypeDto;
import com.company.Izohli.lug.at.module.DayWord;
import com.company.Izohli.lug.at.module.Note;
import com.company.Izohli.lug.at.module.WordInSentence;
import com.company.Izohli.lug.at.module.WordType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestWordDto {
    @NotBlank(message = "Label cannot be null or empty")
    private String label;
    private String transcript;
    private Integer numView;
    private Integer numLike;
    private Integer numShare;

    private Integer categoryId;
    private Integer audioId;

    private Set<WordTypeDto> wordType;
    private Set<NoteDto> notes;
    private Set<WordInSentenceDto> wordInSentences;
    private Set<DayWordDto> dayWords;
}
