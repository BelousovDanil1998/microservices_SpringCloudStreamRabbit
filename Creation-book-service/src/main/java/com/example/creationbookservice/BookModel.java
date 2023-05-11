package com.example.creationbookservice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookModel implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String status = "unchecked";
    private double price;

    public BookModel(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
