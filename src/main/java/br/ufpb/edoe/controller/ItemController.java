package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.service.ItemService;

@RestController
public class ItemController {
    
    @Autowired
    private ItemService service;
}
