package br.ufpb.edoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.DescriptorDTO;
import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.service.DescriptorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "DescriptorController", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Descritor" })
@RestController
public class DescriptorController {

    @Autowired
    private DescriptorService service;

    @ApiOperation(value = "Retorna um lista com todos descritores cadastrados")
    @ApiImplicitParam(name = "sort", value = "Propriedade com o tipo de ordenação desejada:\n\n__asc__: Recupera a lista em ordem crescente.\n__desc__: Recupera a lista em ordem decrescente.")
    @GetMapping("/descriptors")
    public ResponseEntity<List<Descriptor>> getAllDescriptors(
            @RequestParam(value = "sort", defaultValue = "asc") String ordenation) {
        return new ResponseEntity<>(service.getAllDescriptors(ordenation), HttpStatus.OK);
    }

    @ApiOperation(value = "Solicita o cadastro de um novo descritor")
    @ApiImplicitParam(name = "descriptor", value = "Payload contendo o corpo do descritor a ser cadastrado.")
    @PostMapping("/descriptors")
    public ResponseEntity<DescriptorDTO> addDescriptor(@RequestBody(required = true) Descriptor descriptor) {
        return new ResponseEntity<>(service.addDescriptor(descriptor), HttpStatus.CREATED);
    }

}
