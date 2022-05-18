package com.pragma.clientms.service;

import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;
import com.pragma.clientms.model.image.Image;

import java.util.List;

public interface ClientService {
    Client createClient(ClientDTO clientDTO);
    Client updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    List<Client> getClients();
    ClientDTO findById(Long id);
}
