package com.example.ShopListe.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepo extends MongoRepository<ShopItems, String> {

}
