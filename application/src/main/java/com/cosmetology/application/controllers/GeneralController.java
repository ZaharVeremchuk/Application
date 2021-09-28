package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.GeneralDTO;;
import com.cosmetology.application.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/globalAnswer")
public class GeneralController {

    private GeneralService generalService;

    public GeneralController(GeneralService generalService) {
        this.generalService = generalService;
    }

    @PostMapping("/sendAnswers")
    public ResponseEntity<?> sendAnswers(@Valid @RequestBody GeneralDTO dto){
        generalService.saveClientInformation(dto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }
}
