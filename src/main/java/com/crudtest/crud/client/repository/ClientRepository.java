package com.crudtest.crud.client.repository;


import com.crudtest.crud.client.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
