package br.ufpb.edoe.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.ItemDTO;
import br.ufpb.edoe.dto.UpdateItemRequestDTO;
import br.ufpb.edoe.entity.Item;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.enums.UserRoleType;
import br.ufpb.edoe.exceptions.BadRequestParamsException;
import br.ufpb.edoe.exceptions.descriptor.DescriptorNotFoundException;
import br.ufpb.edoe.exceptions.item.ItemNotFoundException;
import br.ufpb.edoe.exceptions.user.InvalidUserRoleException;
import br.ufpb.edoe.exceptions.user.UserNotLoggedException;
import br.ufpb.edoe.repository.ItemRepository;
import br.ufpb.edoe.repository.UserRepository;
import br.ufpb.edoe.security.JWTSecurity;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private DescriptorService descriptorService;

    @Autowired
    private JWTSecurity jwtSecurity;

    @Autowired
    private UserRepository userRepository;

    public ItemDTO addItem(Item item, String header) {
        // Obtendo email do usuário logado
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.addItem");
        }

        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && item.getIsRequired()==1) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR", "UserService.addItem");
        }

        if(!checkItemsIsRequired(item.getIsRequired())){
            throw new BadRequestParamsException("Parâmetro 'required' inválido.",
                    "ItemService.addItem");
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

    public List<ItemDTO> getTop10ItemsByQty() {
        List<ItemDTO> listItems = new ArrayList<>();
        repository.findTop10ByOrderByQtyDesc().forEach(item -> listItems.add(new ItemDTO(item)));
        return listItems;
    }

    public List<ItemDTO> getItemsByString(String search) {
        List<ItemDTO> listItems = new ArrayList<>();
        repository.findAllByDescriptorNameContainingIgnoreCase(search)
                .forEach(item -> listItems.add(new ItemDTO(item)));
        listItems.sort((item1, item2) -> item1.getDescriptor().getName().compareTo(item2.getDescriptor().getName()));
        return listItems;
    }

    public ItemDTO removeItem(int id, String header) {
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.removeItem");
        }

        Item i = this.repository.getById(id);

        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && i.getIsRequired()==1) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR", "UserService.removeItem");
        }

        this.repository.delete(i);
        return new ItemDTO(i);
    }

    public ItemDTO updateItem(int id, UpdateItemRequestDTO dto, String header) {
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.updateItem");
        }

        Optional<Item> i = repository.findById(id);
        if (!i.isPresent()) {
            throw new ItemNotFoundException("Id de Item não encontrado!", "ItemService.updateItem");
        }

        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && i.get().getIsRequired()==1) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR", "UserService.updateItem");
        }

        if(!checkItemsIsRequired(i.get().getIsRequired())){
            throw new BadRequestParamsException("Parâmetro 'required' inválido. ",
                    "ItemService.updateItem");
        }

        i.get().setDescription(dto.getDescription());
        i.get().setQty(dto.getQty());
        this.repository.save(i.get());
        return new ItemDTO(i.get());
    }

    public boolean checkItemsIsRequired(int required) {
        return required != 0 && required != 1 ? false : true;
    }
}
