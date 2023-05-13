package com.example.checkbookservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookModel implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String status;
    private double price;
}
