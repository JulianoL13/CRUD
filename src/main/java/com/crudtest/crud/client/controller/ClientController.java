package com.crudtest.crud.client.controller;

import com.crudtest.crud.client.dto.ClientDTO;
import com.crudtest.crud.client.dto.ClientMutableDTO;
import com.crudtest.crud.client.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientMutableDTO> findById(@PathVariable Long id) {
        ClientMutableDTO clientMutableDTO = clientService.findById(id);
        return ResponseEntity.ok(clientMutableDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClientMutableDTO>> findAll(Pageable pageable) {
        Page<ClientMutableDTO> clientMutableDTOPage = clientService.findAll(pageable);
        return ResponseEntity.ok(clientMutableDTOPage);
    }

    @PostMapping
    public ResponseEntity<ClientMutableDTO> insert(@RequestBody @Valid ClientDTO clientDTO) {
        ClientMutableDTO clientMutableDTO = clientService.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") .buildAndExpand(clientDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(clientMutableDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientMutableDTO> update(@PathVariable Long id, @RequestBody @Valid ClientMutableDTO clientMutableDTO) {
        clientMutableDTO = clientService.update(id, clientMutableDTO);
        return  ResponseEntity.ok(clientMutableDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
