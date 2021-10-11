package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.GeneralDTO;;
import com.cosmetology.application.service.GeneralService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000"})
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
