package com.example.ShopListe.Repo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "list")
public class ShopItems {

    @Id
    private String id;

    public String name;
    public String quantity;

    // constructor
    public ShopItems() {
    }

    public ShopItems(String id, String name, String quantity) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public ShopItems(String name, String quantity) {
        super();
        this.name = name;
        this.quantity = quantity;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
