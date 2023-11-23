package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestCategoryDto;
import com.company.Izohli.lug.at.dto.responseDto.CategoryDto;
import com.company.Izohli.lug.at.service.CategoryService;
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
@RequestMapping(value = "category")
public class CategoryController implements SimpleRequestCrud<Integer, RequestCategoryDto, CategoryDto> {
    private final CategoryService categoryService;
    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            )

    })
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto<CategoryDto>> createEntity(@RequestBody @Valid RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.createEntity(entity));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            )

    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDto<CategoryDto>> getEntity(@PathVariable(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.categoryService.getEntity(entityId));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            )

    })
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto<CategoryDto>> updateEntity(@PathVariable(value = "id")Integer entityId,
                                                                 @RequestBody @Valid  RequestCategoryDto entity) {
        return convertStatusCodeByData(this.categoryService.updateEntity(entityId,entity));
    }

    @Override
    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_CATEGORY_NOT_FOUND)
                            )
                    }

            )

    })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDto<CategoryDto>> deleteEntity(@PathVariable(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.categoryService.deleteEntity(entityId));
    }
}
