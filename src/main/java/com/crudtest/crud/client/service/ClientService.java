package com.crudtest.crud.client.service;

import com.crudtest.crud.client.dto.ClientDTO;
import com.crudtest.crud.client.dto.ClientMutableDTO;
import com.crudtest.crud.client.entities.Client;
import com.crudtest.crud.client.repository.ClientRepository;
import com.crudtest.crud.client.service.exceptions.DatabaseException;
import com.crudtest.crud.client.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientMutableDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientMutableDTO::new);

    }

    @Transactional(readOnly = true)
    public ClientMutableDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientMutableDTO(client);
    }
    @Transactional
    public ClientMutableDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        mapDTOToEntity(clientDTO, client);
        Client savedEntity = clientRepository.save(client);
        return new ClientMutableDTO(savedEntity);
    }

    @Transactional
    public ClientMutableDTO update(Long id, ClientMutableDTO clientMutableDTO) {
        Client client = clientRepository.getReferenceById(id);
        maskSensitiveFields(clientMutableDTO, client);
        client = clientRepository.save(client);
        return new ClientMutableDTO(client);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }


    private void mapDTOToEntity(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setChildren(clientDTO.getChildren());
    }

    private void maskSensitiveFields(ClientMutableDTO response, Client client) {
        client.setName(response.getName());
        client.setIncome(response.getIncome());
        client.setBirthDate(response.getBirthDate());
        client.setChildren(response.getChildren());
    }



}
