package com.pragma.clientms.service;

import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    List<Client> getClients();
    ClientDTO findById(Long id);
}
