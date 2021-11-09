package br.ufpb.edoe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.ItemDTO;
import br.ufpb.edoe.entity.Item;
import br.ufpb.edoe.exceptions.DescriptorNotFoundException;
import br.ufpb.edoe.exceptions.UserNotLoggedException;
import br.ufpb.edoe.repository.ItemRepository;
import br.ufpb.edoe.security.JWTSecurity;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private DescriptorService descriptorService;

    @Autowired
    private JWTSecurity jwtSecurity;

    public ItemDTO addItem(Item item, String header) {
        // Obtendo email do usuário logado
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.addItem");
        }

        if (!descriptorService.checkDescriptorExists(item.getDescriptor())) {
            throw new DescriptorNotFoundException(
                    "O descritor não encontrado. Por cadastre-o antes de adicionar o item.", "ItemService.addItem");
        }

        item.setAssociatedEmail(loggedEmail.get());
        repository.save(item);
        return new ItemDTO(item);
    }

}
