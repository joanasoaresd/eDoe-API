package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.UserDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.service.UserService;

@RestController
public class UserController {
   
    @Autowired
    private UserService service;
    
    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {        
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);        
    }
}
