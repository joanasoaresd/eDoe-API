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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.DonateDTO;
import br.ufpb.edoe.dto.DonationDTO;
import br.ufpb.edoe.dto.ItemDTO;
import br.ufpb.edoe.dto.UpdateItemRequestDTO;
import br.ufpb.edoe.entity.Item;
import br.ufpb.edoe.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "ItemController", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Item" })
@RestController
public class ItemController {

    @Autowired
    private ItemService service;

    @ApiOperation(value = "Retorna um lista com todos items cadastrados com um id de um descritor específico")
    @GetMapping("/items/descriptors/{id}")
    public ResponseEntity<List<ItemDTO>> getItemsByDescriptorId(@PathVariable int id,
            @RequestParam(value = "type", defaultValue = "0") int type) {
        return new ResponseEntity<>(service.getItemsByDescriptorId(id, type), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um lista com os 10 itens para doação com maior quantidade, ordenada de forma decrescente.")
    @GetMapping("/items/top10")
    public ResponseEntity<List<ItemDTO>> getTop10ItemsByQty(
            @RequestParam(value = "type", defaultValue = "0") int type) {
        return new ResponseEntity<>(service.getTop10ItemsByQty(type), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um lista com todos items a partir de uma string de busca")
    @GetMapping("/items/{search}")
    public ResponseEntity<List<ItemDTO>> getItemsByString(@PathVariable String search,
            @RequestParam(value = "type", defaultValue = "0") int type) {
        return new ResponseEntity<>(service.getItemsByString(search, type), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um lista com todos matches  de itens para doação e itens necessários")
    @GetMapping("/items/matches/{id}")
    public ResponseEntity<List<ItemDTO>> getItemsMatches(@PathVariable int id, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.getItemsMatches(id, token), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna o histórico de doações do sistema")
    @GetMapping("/items/donates/history")
    public ResponseEntity<List<DonationDTO>> getDonatesHistory() {
        return new ResponseEntity<>(service.getDonatesHistory(), HttpStatus.OK);
    }

    @ApiOperation(value = "Solicita o cadastro de um item")
    @ApiImplicitParam(name = "item", value = "Payload contendo o corpo do item a ser cadastrado.")
    @PostMapping("/items")
    public ResponseEntity<ItemDTO> addItem(@RequestBody Item item, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.addItem(item, token), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Solicita o cadastro de uma doação")
    @ApiImplicitParam(name = "donateDTO", value = "Payload contendo o corpo contendo os dados necessários pra realizar uma doação.")
    @PostMapping("/items/donate")
    public ResponseEntity<Void> donateItem(@RequestBody DonateDTO donateDTO,
            @RequestHeader("Authorization") String token) {
        service.donateItem(donateDTO, token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Solicita a remoção de um item")
    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDTO> removeItem(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.removeItem(id, token), HttpStatus.OK);
    }

    @ApiOperation(value = "Solicita a atualização de um item")
    @ApiImplicitParam(name = "uItemRequestDTO", value = "Payload contendo os campos a serem atualizados no item.")
    @PatchMapping("/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable Integer id,
            @RequestBody UpdateItemRequestDTO uItemRequestDTO, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.updateItem(id, uItemRequestDTO, token), HttpStatus.OK);
    }
}
