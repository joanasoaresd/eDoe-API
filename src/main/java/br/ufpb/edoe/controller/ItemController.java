package br.ufpb.edoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.ItemDTO;
import br.ufpb.edoe.dto.UpdateItemRequestDTO;
import br.ufpb.edoe.entity.Item;
import br.ufpb.edoe.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ItemController", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Item" })
@RestController
public class ItemController {

    @Autowired
    private ItemService service;

    @ApiOperation(value = "Retorna um lista com todos items cadastrados com um id de um descritor específico")
    @GetMapping("/items/descriptors/{id}")
    public ResponseEntity<List<ItemDTO>> getItemsByDescriptorId(@PathVariable int id) {
        return new ResponseEntity<>(service.getItemsByDescriptorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Solicita o cadastro de um item")
    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addItem(@RequestBody Item item, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.addItem(item, token), HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDTO> removeItem(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.removeItem(id, token), HttpStatus.OK);
    }

    @PatchMapping("/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Integer id, @RequestBody UpdateItemRequestDTO dto,
            @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.updateItem(id, dto, token), HttpStatus.OK);
    }
}
