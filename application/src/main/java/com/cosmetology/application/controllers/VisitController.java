package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.VisitRequestDTO;
import com.cosmetology.application.service.VisitService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/visits")
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getAllVisitForClient(@Param("clientId") Long clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(visitService.searchVisitsByClientId(clientId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody VisitRequestDTO visitRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.save(visitRequestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody VisitRequestDTO visitRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(visitService.save(visitRequestDTO));
    }

    @DeleteMapping("/delete?id={id}")
    public ResponseEntity<?> delete(@Param("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(visitService.delete(id));
    }

}
