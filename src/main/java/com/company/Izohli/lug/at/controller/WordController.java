package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestWordDto;
import com.company.Izohli.lug.at.dto.responseDto.WordDto;
import com.company.Izohli.lug.at.service.WordService;
import com.company.Izohli.lug.at.utill.SimpleRequestCrud;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.constants.SwaggerConstants.*;
import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "word")
public class WordController implements SimpleRequestCrud<Integer, RequestWordDto, WordDto> {
    private final WordService wordService;
    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            )

    })
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto<WordDto>> createEntity(@RequestBody @Valid RequestWordDto entity) {
        return convertStatusCodeByData(this.wordService.createEntity(entity));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            )

    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDto<WordDto>> getEntity(@PathVariable(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordService.getEntity(entityId));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            )

    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto<WordDto>> updateEntity(@PathVariable(value = "id")Integer entityId,
                                                             @RequestBody @Valid  RequestWordDto entity) {
        return convertStatusCodeByData(this.wordService.updateEntity(entityId,entity));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_WORD_NOT_FOUND)
                            )
                    }

            )

    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDto<WordDto>> deleteEntity(@PathVariable(value = "id")Integer entityId) {
        return convertStatusCodeByData(this.wordService.deleteEntity(entityId));
    }
}
