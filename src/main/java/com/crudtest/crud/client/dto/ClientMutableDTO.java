package com.crudtest.crud.client.dto;

import com.crudtest.crud.client.entities.Client;

import java.time.LocalDate;

public class ClientMutableDTO {
    private Long id;
    private String name;
    private double income;
    private LocalDate birthDate;
    private int children;

    public ClientMutableDTO() {}

    public ClientMutableDTO(Long id, String name, double income, LocalDate birthDate, int children) {
        this.id = id;
        this.name = name;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientMutableDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public int getChildren() {
        return children;
    }
}
