package com.pragma.clientms.service;

import com.pragma.clientms.exceptions.EmptyDataException;
import com.pragma.clientms.exceptions.ErrorConstants;
import com.pragma.clientms.exceptions.NoDataFoundException;
import com.pragma.clientms.exceptions.ResourceNotFoundException;
import com.pragma.clientms.feign.ImageFeignClient;
import com.pragma.clientms.model.client.Client;
import com.pragma.clientms.model.dto.ClientDTO;
import com.pragma.clientms.model.image.Image;
import com.pragma.clientms.model.mapper.ClientMapper;
import com.pragma.clientms.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ImageFeignClient imageFeignClient;

    static ClientMapper mapper = ClientMapper.singleInstance();

    @Override
    public Client createClient(ClientDTO clientDTO) {
        if (clientDTO.getDocument() == null || clientDTO.getFirstName().isEmpty() || clientDTO.getLastName().isEmpty() || clientDTO.getCity().isEmpty() || clientDTO.getTypeOfId().isEmpty() || clientDTO.getAge().isEmpty() || clientDTO.getImage().isEmpty()) {
            throw new EmptyDataException(ErrorConstants.EMPTYDATA);
        } else if (clientRepository.findById(clientDTO.getDocument()).isPresent()) {
            throw new EmptyDataException("Ya existe un usuario con el ID -> " + clientDTO.getDocument());
        }
        Image image = new Image(clientDTO.getDocument(), clientDTO.getImage());
        imageFeignClient.save(image);
        return clientRepository.save(mapper.dtoToDomain(clientDTO));
    }

    @Override
    public Client updateClient(Long id, ClientDTO client) {
        if (client.getFirstName().isEmpty() || client.getLastName().isEmpty() || client.getCity().isEmpty() || client.getTypeOfId().isEmpty() || client.getAge().isEmpty() || client.getDocument() == null || client.getImage().isEmpty()) {
            throw new EmptyDataException(ErrorConstants.EMPTYDATA);
        } else if (id.longValue() != client.getDocument().longValue()) {
            throw new EmptyDataException("Los id no coinciden!!!");
        }
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(ErrorConstants.NOTFOUND + id);
        });
        Image imageToUpdate = imageFeignClient.getImageById(id);
        imageToUpdate.setPhoto(client.getImage());
        clientToUpdate.setFirstName(client.getFirstName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setCity(client.getCity());
        clientToUpdate.setTypeOfId(client.getTypeOfId());
        clientToUpdate.setAge(client.getAge());
        imageFeignClient.save(imageToUpdate);
        return clientRepository.save(clientToUpdate);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException(ErrorConstants.NOTFOUND + id);
        }
        imageFeignClient.deleteImage(id);
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getClients() {
        if (clientRepository.findAll().isEmpty()) {
            throw new NoDataFoundException(ErrorConstants.NOTDATA);
        }
        return clientRepository.findAll();
    }

    @Override
    public ClientDTO findById(Long id) {
        ClientDTO clientDTO = mapper.domainToDto(clientRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException(ErrorConstants.NOTFOUND + id);
        }));
        clientDTO.setImage(imageFeignClient.getImageById(id).getPhoto());
        return clientDTO;
    }

}
