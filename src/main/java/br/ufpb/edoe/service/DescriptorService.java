package br.ufpb.edoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.DescriptorDTO;
import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.exceptions.DescriptorExistsException;
import br.ufpb.edoe.repository.DescriptorRepository;

@Service
public class DescriptorService {

    @Autowired
    private DescriptorRepository repository;

    public DescriptorDTO addDescriptor(Descriptor descriptor){
        if (repository.findByName(descriptor.getName()).isPresent())
            throw new DescriptorExistsException("Descritor já existe", "DescriptorService.addDescriptor");
        repository.save(descriptor);
        return new DescriptorDTO(descriptor);
    }
    
}
