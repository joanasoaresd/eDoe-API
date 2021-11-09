package br.ufpb.edoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;
    
}
