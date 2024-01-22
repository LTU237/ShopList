package com.example.ShopListe.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.ShopListe.Repo.ShopItems;
import com.example.ShopListe.Service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController

@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    // constructor
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // CREATE Items in DB
    @PostMapping("/create")
    public ResponseEntity<ShopItems> createItems(@RequestBody ShopPostRequestBody requestBody) {

        final var item = this.shopService.create(requestBody.name(), requestBody.quantity());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // READ items from DB
    @GetMapping("/read/{id}")
    public ResponseEntity<ShopItems> readById(@PathVariable String id) {
        final var item = this.shopService.findById(id);
        if (item.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item.get(), HttpStatus.OK);
    }

    @GetMapping("/readAll")
    public ResponseEntity<Iterable<ShopItems>> ReadItems() {
        return new ResponseEntity<>(shopService.getAll(), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<ShopItems> editItems(@PathVariable String id, @RequestBody ShopPutRequestBody requestBody) {
        final var updateItem = new ShopItems(id, requestBody.name(), requestBody.quantity());
        final var updateItems = this.shopService.update(updateItem);

        return new ResponseEntity<>(updateItems, HttpStatus.OK);

    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItems(@PathVariable String id) {
        this.shopService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
