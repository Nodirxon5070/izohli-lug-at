package com.company.Izohli.lug.at.controller;

import com.company.Izohli.lug.at.dto.ResponseDto;
import com.company.Izohli.lug.at.dto.requestDto.RequestAuthDto;
import com.company.Izohli.lug.at.module.Authorities;
import com.company.Izohli.lug.at.service.AuthorityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth")
@RequiredArgsConstructor
@RequestMapping(value = "auth")
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<Authorities> create(@RequestBody RequestAuthDto dto) {
        return this.authorityService.create(dto);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<Authorities> getAuth(@PathVariable(value = "id") Integer authId) {
        return this.authorityService.getAuth(authId);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<Authorities> updateAuth(
            @RequestBody RequestAuthDto dto,
            @PathVariable(value = "id") Integer authId
    ) {
        return this.authorityService.update(dto, authId);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ResponseDto<Authorities> deleteAuth(@PathVariable(value = "id") Integer authId) {
        return this.authorityService.deleteAuth(authId);
    }


}
