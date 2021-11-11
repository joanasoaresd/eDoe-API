package br.ufpb.edoe.service;

import java.util.ArrayList;
import java.util.List;
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

    public List<ItemDTO> getItemsByDescriptorId(int id) {
        List<ItemDTO> listItems = new ArrayList<>();
        repository.findAllByDescriptorId(id).forEach(item -> listItems.add(new ItemDTO(item)));
        return listItems;
    }

    public ItemDTO removeItem(int id, String header) {
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.removeItem");
        }

        Item i = this.repository.getById(id);
        this.repository.delete(i);
        return new ItemDTO(i);
    }

}
