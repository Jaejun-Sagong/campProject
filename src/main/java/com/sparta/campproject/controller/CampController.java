package com.sparta.campproject.controller;

import com.sparta.campproject.dto.CampDto;
import com.sparta.campproject.dto.CampRequestDto;
import com.sparta.campproject.entity.Camp;
import com.sparta.campproject.repository.CampRepository;
import com.sparta.campproject.service.CampService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CampController {

    private final CampService campService;
    private final CampRepository campRepository;
//    @PostMapping(value = "/api/auth/camp", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Secured("ROLE_USER")
    @PostMapping("/api/auth/camp")
    public Camp registerCamp(@RequestBody CampRequestDto requestDto){
        return campService.registerCamp(requestDto);
    }

    @GetMapping("/api/camp")
    public List<CampDto> getCamps() {
        return campService.getCamps();
    }

    @GetMapping("/api/camp/{campid}")
    public Camp showCampDetail(@PathVariable Long campid) {
        return campService.showCampDetail(campid);
    }

    @Secured("ROLE_USER")
    @PutMapping("/api/auth/camp/{campid}")
    public Camp updateMemo(@PathVariable Long campid, @RequestBody CampRequestDto requestDto) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return campService.update(campid, requestDto);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/api/auth/camp/{campid}")
    public boolean deleteMemo(@PathVariable Long campid) {   //RequestBody어노테이션을 써줘야만 Request 안에 Body를 requestDto에 넣어줘야하구나 를 Spring이 안다
        return campService.delete(campid);
    }
}