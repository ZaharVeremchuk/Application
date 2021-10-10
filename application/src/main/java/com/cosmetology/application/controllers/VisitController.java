package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.VisitRequestDTO;
import com.cosmetology.application.dto.response.VisitDTO;
import com.cosmetology.application.service.VisitService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/visits")
public class VisitController {

    private VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<VisitDTO>> getAllVisitForClient(@RequestParam("clientId") Long clientId) {
        return ResponseEntity.status(HttpStatus.OK).body(visitService.searchVisitsByClientId(clientId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody VisitRequestDTO visitRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.save(visitRequestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody VisitRequestDTO visitRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(visitService.save(visitRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(visitService.delete(id));
    }

}
