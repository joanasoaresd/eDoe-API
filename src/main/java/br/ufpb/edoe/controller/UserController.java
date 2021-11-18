package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.UpdateRoleRequestDTO;
import br.ufpb.edoe.dto.UserDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserController", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Usuário" })
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Solicita o cadastro de um novo usuário")
    @ApiImplicitParam(name = "user", value = "Payload contendo o corpo do usuário a ser cadastrado.")
    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Solicita a atualização do papel de usuário (apenas usuários ADMIN)")
    @ApiImplicitParam(name = "uRoleRequestDTO", value = "Payload contendo os campos a serem atualizados no usuário desejado.")
    @PutMapping("/users/role")
    public ResponseEntity<UserDTO> updateRole(@RequestBody UpdateRoleRequestDTO uRoleRequestDTO,
            @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(service.updateRole(uRoleRequestDTO, token), HttpStatus.ACCEPTED);
    }
}
