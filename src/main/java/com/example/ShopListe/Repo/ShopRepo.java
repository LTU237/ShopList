package com.example.ShopListe.Repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopRepo extends MongoRepository<ShopItems, String> {

    Page<ShopItems> findAll(Pageable pageable);
}
