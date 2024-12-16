package com.crudtest.crud.client.dto;

import com.crudtest.crud.client.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientMutableDTO {
    private Long id;

    @Size(min = 3, max = 80, message = "name requires at least 3 characters and max 80 characters")
    @NotBlank(message = "field required")
    private String name;
    @PositiveOrZero(message = "income should be zero or greater than zero")
    private double income;
    @PastOrPresent(message = "time cannot be in future")
    private LocalDate birthDate;
    @PositiveOrZero(message = "childrens should be 0 or more")
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
