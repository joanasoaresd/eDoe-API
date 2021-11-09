package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.ItemDTO;
import br.ufpb.edoe.entity.Item;
import br.ufpb.edoe.service.ItemService;

@RestController
public class ItemController {

    @Autowired
    private ItemService service;

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addItem(@RequestBody Item item, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.addItem(item, token), HttpStatus.CREATED);
    }
}
