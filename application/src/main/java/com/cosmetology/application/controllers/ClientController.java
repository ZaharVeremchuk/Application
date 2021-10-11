package com.cosmetology.application.controllers;

import com.cosmetology.application.dto.request.ClientDTO;
import com.cosmetology.application.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:8080","http://localhost:3000"})
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@RequestParam("id") Long id){
        clientService.deleteClient(id);
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllClients(){
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInfoAboutClient(){
        return  ResponseEntity.status(HttpStatus.OK).body(clientService.getAllInfoAboutClient());
    }
}
