package org.sid.orderservice.models;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private double prix;
    private int quantity ;

}
