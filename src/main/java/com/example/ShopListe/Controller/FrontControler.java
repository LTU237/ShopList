package com.example.ShopListe.Controller;

import com.example.ShopListe.Repo.ShopItems;
import com.example.ShopListe.Service.ShopService;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontControler {

    private ShopService shopService;

    public FrontControler(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("ui/items")
    public String handleGet(Model model) {
        this.addItemsAndNewItemCreate(model);
        return "items";
    }

    @GetMapping("ui/items/edit/{id}")
    public String editItem(@PathVariable String id, Model model) {
        ShopItems item = shopService.findById(id).orElse(null); // Assuming findById returns an Optional<ShopItems>
        model.addAttribute("item", item);
        return "edit";
    }

    @PostMapping("ui/items")
    public String postMethod(@ModelAttribute("newItems") ItemCreate itemCreate, Model model) {
        final var create = this.shopService.create(itemCreate.name(), itemCreate.quantity());
        model.addAttribute("message", "successfully created item with id" + create.getId());
        this.addItemsAndNewItemCreate(model);
        return "items";
    }

    // @PostMapping("ui/items/edit/{id}")
    @PostMapping("ui/items/{id}")
    public String updateItem(@PathVariable String id,
            @ModelAttribute("updatedItem") ItemCreate updatedItem,
            Model model) {
        this.shopService.update(id, updatedItem.name(), updatedItem.quantity());
        model.addAttribute("message", "Successfully updated item with id " + id);

        this.addItemsAndNewItemCreate(model);
        return "redirect:/ui/items";
    }
    // public String updateItem(@PathVariable String id, @ModelAttribute("newItems")
    // ItemCreate itemCreate,
    // Model model) {
    // shopI existingItem = this.shopService.findById(id);
    //
    // this.shopService.update(existingItem);
    // model.addAttribute("message", "Successfully updated item with id " + id);
    // this.addItemsAndNewItemCreate(model);
    // return "items";
    // }

    @DeleteMapping("ui/items/{id}")
    public String delete(@PathVariable String id, Model model) {
        this.shopService.deleteById(id);
        this.addItemsAndNewItemCreate(model);

        return "redirect:/ui/items";
    }

    private void addItemsAndNewItemCreate(Model model) {
        final var itemCreate = new ItemCreate(null, null);
        model.addAttribute("newItems", itemCreate);

        final var items = this.shopService.getAll(Pageable.unpaged()).toList();
        model.addAttribute("items", items);
    }

}
