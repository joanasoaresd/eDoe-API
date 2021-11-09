package br.ufpb.edoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.DescriptorDTO;
import br.ufpb.edoe.entity.Descriptor;
import br.ufpb.edoe.exceptions.DescriptorExistsException;
import br.ufpb.edoe.exceptions.BadRequestParamsException;
import br.ufpb.edoe.repository.DescriptorRepository;

@Service
public class DescriptorService {

    @Autowired
    private DescriptorRepository repository;

    public DescriptorDTO addDescriptor(Descriptor descriptor) {
        descriptor.setName(descriptor.getName().toLowerCase().trim());
        if (!repository.findByNameContainingIgnoreCase(descriptor.getName()).isEmpty())
            throw new DescriptorExistsException("Descritor já existe", "DescriptorService.addDescriptor");
        repository.save(descriptor);
        return new DescriptorDTO(descriptor);
    }

    public List<Descriptor> getAllDescriptors(String ordenation) {
        if (!ordenation.equalsIgnoreCase("asc") && !ordenation.equalsIgnoreCase("desc")) {
            throw new BadRequestParamsException("Parâmetro 'sort' inválido. Valor usado: " + ordenation,
                    "DescriptorService.getAllDescriptors");
        }
        return ordenation.equalsIgnoreCase("asc") ? repository.findAllByOrderByNameAsc()
                : repository.findAllByOrderByNameDesc();
    }

    public boolean checkDescriptorExists(Descriptor descriptor) {
        return !repository.findByNameContainingIgnoreCase(descriptor.getName()).isEmpty();
    }

}
