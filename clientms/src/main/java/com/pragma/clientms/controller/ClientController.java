package com.pragma.clientms.controller;

import com.pragma.clientms.exceptions.ImageServiceException;
import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;
import com.pragma.clientms.service.ClientServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientms")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;


    @GetMapping
    ResponseEntity<List<Client>> listClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @CircuitBreaker(name = "imageCB", fallbackMethod = "fallBackImageService")
    @PostMapping
    ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @CircuitBreaker(name = "imageCB", fallbackMethod = "fallBackImageService")
    @GetMapping("/{document}")
    ResponseEntity<ClientDTO> getClientByDoc(@PathVariable("document") Long document) {
        return ResponseEntity.ok(clientService.findById(document));
    }

    @CircuitBreaker(name = "imageCB", fallbackMethod = "fallBackImageService")
    @DeleteMapping("/{document}")
    ResponseEntity<Void> deleteClient(@PathVariable("document") Long document) {
        clientService.deleteClient(document);
        return ResponseEntity.status(204).build();
    }

    @CircuitBreaker(name = "imageCB", fallbackMethod = "fallBackImageService")
    @PutMapping("/{document}")
    ResponseEntity<ClientDTO> updateClient(@PathVariable("document") Long document, @RequestBody ClientDTO clientDTO) {
        clientService.updateClient(document, clientDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<ImageServiceException> fallBackImageService(RuntimeException e){
        throw new ImageServiceException("El servicio imagen no se encuentra disponible");
    }

}
