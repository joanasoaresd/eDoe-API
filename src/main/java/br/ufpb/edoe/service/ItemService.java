package br.ufpb.edoe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public static final int DONATE = 0;
    public static final int IS_REQUIRED = 1;

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
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && item.getIsRequired() == IS_REQUIRED) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR", "UserService.addItem");
        }

        if (!checkItemsIsRequired(item.getIsRequired())) {
            throw new BadRequestParamsException("Parâmetro 'required' inválido.", "ItemService.addItem");
        }

        if (!descriptorService.checkDescriptorExists(item.getDescriptor())) {
            throw new DescriptorNotFoundException(
                    "O descritor não encontrado. Por cadastre-o antes de adicionar o item.", "ItemService.addItem");
        }

        item.setAssociatedEmail(loggedEmail.get());
        repository.save(item);
        return new ItemDTO(item);
    }

    public List<ItemDTO> getItemsByDescriptorId(int id, int type) {
        List<ItemDTO> listItems = new ArrayList<>();
        if (type != 0 && type != 1) {
            throw new BadRequestParamsException("Parâmetro 'type' inválido. Valor usado: " + type,
                    "ItemService.getItemsByDescriptorId");
        }
        repository.findAllByDescriptorId(id).forEach(item -> {
            if (item.getIsRequired() == type)
                listItems.add(new ItemDTO(item));
        });
        return listItems;
    }

    public List<ItemDTO> getTop10ItemsByQty(int type) {
        List<ItemDTO> listItems = new ArrayList<>();
        if (type != 0 && type != 1) {
            throw new BadRequestParamsException("Parâmetro 'type' inválido. Valor usado: " + type,
                    "ItemService.getTop10ItemsByQty");
        }
        repository.findTop10ByOrderByQtyDesc().forEach(item -> {
            if (item.getIsRequired() == type)
                listItems.add(new ItemDTO(item));
        });
        return listItems;
    }

    public List<ItemDTO> getItemsByString(String search, int type) {
        List<ItemDTO> listItems = new ArrayList<>();
        if (type != 0 && type != 1) {
            throw new BadRequestParamsException("Parâmetro 'type' inválido. Valor usado: " + type,
                    "ItemService.getItemsByString");
        }
        repository.findAllByDescriptorNameContainingIgnoreCase(search).forEach(item -> {
            if (item.getIsRequired() == type)
                listItems.add(new ItemDTO(item));
        });
        listItems.sort((item1, item2) -> item1.getDescriptor().getName().compareTo(item2.getDescriptor().getName()));
        return listItems;
    }

    public List<ItemDTO> getItemsMatches(int id, String header){
        Item item = this.repository.findById(id).get();

        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.getItemsMatches");
        }

        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR)) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR", "UserService.getItemsMatches");
        }

        if (item.getIsRequired() != IS_REQUIRED) {
            throw new BadRequestParamsException("Parâmetro 'required' inválido.", "ItemService.getItemsMatches");
        }

        return this.getItemsByDescriptorId(item.getDescriptor().getId(), DONATE);            
    }

    public ItemDTO removeItem(int id, String header) {
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "ItemService.removeItem");
        }

        Item i = this.repository.getById(id);

        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && i.getIsRequired() == 1) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR",
                    "UserService.removeItem");
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
        if (!user.get().getPapel().equals(UserRoleType.APENAS_RECEPTOR) && i.get().getIsRequired() == 1) {
            throw new InvalidUserRoleException("Usuário não possui o papel de APENAS_RECEPTOR",
                    "UserService.updateItem");
        }

        if (!checkItemsIsRequired(i.get().getIsRequired())) {
            throw new BadRequestParamsException("Parâmetro 'required' inválido. ", "ItemService.updateItem");
        }

        i.get().setDescription(dto.getDescription());
        i.get().setQty(dto.getQty());
        this.repository.save(i.get());
        return new ItemDTO(i.get());
    }

    public boolean checkItemsIsRequired(int required) {
        return required != DONATE && required != IS_REQUIRED ? false : true;
    }
}
