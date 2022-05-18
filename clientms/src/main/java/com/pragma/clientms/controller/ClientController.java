package com.pragma.clientms.controller;

import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;
import com.pragma.clientms.model.image.Image;
import com.pragma.clientms.model.mapper.ClientMapper;
import com.pragma.clientms.service.ClientServiceImpl;
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

    @PostMapping
    ResponseEntity<Client> createClient(@RequestBody ClientDTO client) {
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }

    @GetMapping("/{document}")
    ResponseEntity<ClientDTO> getClientByDoc(@PathVariable("document") Long document) {
        return ResponseEntity.ok(clientService.findById(document));
    }

    @DeleteMapping("/{document}")
    ResponseEntity<Void> deleteClient(@PathVariable("document") Long document) {
        clientService.deleteClient(document);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{document}")
    ResponseEntity<Client> updateClient(@PathVariable("document") Long document, @RequestBody ClientDTO clientDTO) {
        clientService.updateClient(document, clientDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
