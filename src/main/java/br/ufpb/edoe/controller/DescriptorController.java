package br.ufpb.edoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.DescriptorDTO;
import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.service.DescriptorService;

@RestController
public class DescriptorController {

    @Autowired
    private DescriptorService service;

    @GetMapping("/descriptors")
    public ResponseEntity<List<Descriptor>> getAllDescriptors(@RequestParam("sort") String ordenation) {
        return new ResponseEntity<>(service.getAllDescriptors(ordenation), HttpStatus.OK);
    }

    @PostMapping("/descriptors")
    public ResponseEntity<DescriptorDTO> addDescriptor(@RequestBody Descriptor descriptor) {
        return new ResponseEntity<>(service.addDescriptor(descriptor), HttpStatus.CREATED);
    }

}
