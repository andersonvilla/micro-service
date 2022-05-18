package com.pragma.clientms.model.mapper;

import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;
import org.modelmapper.ModelMapper;

public class ClientMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private static ClientMapper instance;

    private ClientMapper() {

    }

    public static ClientMapper singleInstance() {
        if (instance == null) {
            instance = new ClientMapper();
        }
        return instance;
    }

    public Client dtoToDomain(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    public ClientDTO domainToDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

}
