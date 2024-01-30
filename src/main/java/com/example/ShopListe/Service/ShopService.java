package com.example.ShopListe.Service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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

    public Page<ShopItems> getAll(Pageable pageable) {
        return this.shopRepo.findAll(pageable);
    }

    // UPDATE
    public ShopItems update(ShopItems existingItem) {
        return this.shopRepo.save(existingItem);
    }

    public ShopItems update(String id, String name, String quantity) {
        Optional<ShopItems> optionalItem = this.shopRepo.findById(id);

        ShopItems existingItem = optionalItem.get();
        existingItem.setName(name);
        existingItem.setQuantity(quantity);
        return this.shopRepo.save(existingItem);

    }

    // DELETE
    public void deleteById(String id) {
        this.shopRepo.deleteById(id);
    }

}
