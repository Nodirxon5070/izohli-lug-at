package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.LoginDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestRegisterConfirmDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestUserDto;
import com.company.Izohli.lug.at.dto.responseDto.TokenResponseDto;
import com.company.Izohli.lug.at.dto.responseDto.UserDto;
import com.company.Izohli.lug.at.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.company.Izohli.lug.at.constants.SwaggerConstants.*;
import static com.company.Izohli.lug.at.dto.SimpleResponseDto.convertStatusCodeByData;

@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping(value = "users")
public class UserController {

    public final UserService userService;


    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_USER_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_USER_NOT_FOUND)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_USER_NOT_FOUND)
                            )
                    }

            )

    })
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody RequestUserDto userDto) {
        return convertStatusCodeByData(this.userService.register(userDto));
    }


    @ApiResponses(value={
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_LOGIN_SUCCESS)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_LOGIN_UNSUCCESSFUL)
                            )
                    }

            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = EXAMPLE_LOGIN_UNSUCCESSFUL)
                            )
                    }

            )

    })
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDto<TokenResponseDto>> login(@RequestBody LoginDto dto) {
        return convertStatusCodeByData(this.userService.login(dto));
    }

    @PostMapping(value = "/register-confirm")
    public ResponseEntity<ResponseDto<TokenResponseDto>> registerConfirm(@RequestBody RequestRegisterConfirmDto dto){
        return convertStatusCodeByData(this.userService.registerConfirm(dto));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<ResponseDto<UserDto>> logout(@RequestParam String username){
        return convertStatusCodeByData(this.userService.logout(username));
    }

    @GetMapping(value = "/refresh-token")
    public ResponseEntity<ResponseDto<TokenResponseDto>> refreshToken(@RequestParam String username){
        return convertStatusCodeByData(this.userService.refreshToken(username));
    }


}
