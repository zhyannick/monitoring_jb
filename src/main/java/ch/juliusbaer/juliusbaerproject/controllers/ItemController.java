package ch.juliusbaer.juliusbaerproject.controllers;

import ch.juliusbaer.juliusbaerproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;
    //hell0
    @GetMapping
    public void getAll(){
        itemRepository.findAll();

    }
}
