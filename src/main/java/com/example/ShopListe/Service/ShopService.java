package com.example.ShopListe.Service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.ShopListe.Repo.ShopItems;
import com.example.ShopListe.Repo.ShopRepo;

@Service

public class ShopService {
    private ShopRepo shopRepo;

    // constructor
    public ShopService(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    // CREATE
    public ShopItems create(String name, String quantity) {
        final ShopItems shopItems = new ShopItems(name, quantity);
        return this.shopRepo.save(shopItems);
    }

    // READ
    public Optional<ShopItems> findById(String id) {
        return this.shopRepo.findById(id);
    }

    public Iterable<ShopItems> getAll() {
        return this.shopRepo.findAll();
    }

    // UPDATE
    public ShopItems update(ShopItems item) {
        return this.shopRepo.save(item);
    }

    // DELETE
    public void deleteById(String id) {
        this.shopRepo.deleteById(id);
    }

}
