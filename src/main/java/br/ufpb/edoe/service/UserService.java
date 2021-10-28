package br.ufpb.edoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.UserDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.exceptions.UserExistsException;
import br.ufpb.edoe.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExistsException("Já Existe usuário com este email");
        userRepository.save(user);
        return new UserDTO(user);
    }

    
}
