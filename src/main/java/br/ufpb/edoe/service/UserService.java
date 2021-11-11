package br.ufpb.edoe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.UpdateRoleRequestDTO;
import br.ufpb.edoe.dto.UserDTO;
import br.ufpb.edoe.dto.UserLoginDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.enums.UserRoleType;
import br.ufpb.edoe.exceptions.user.InvalidUserRoleException;
import br.ufpb.edoe.exceptions.user.UserExistsException;
import br.ufpb.edoe.exceptions.user.UserNotFoundException;
import br.ufpb.edoe.exceptions.user.UserNotLoggedException;
import br.ufpb.edoe.repository.UserRepository;
import br.ufpb.edoe.security.JWTSecurity;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTSecurity jwtSecurity;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new UserExistsException("Já Existe usuário com este email", "UserService.addUser");
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateRole(UpdateRoleRequestDTO uRoleRequestDTO, String header) {
        // Obtendo id do usuário logado
        Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado", "UserService.updateRole");
        }

        // Verificando crendenciais do usuário logado
        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.isPresent()) {
            throw new UserNotFoundException("Email do usuário logado não encontrado!", "UserService.updateRole");
        }
        if (!user.get().getPapel().equals(UserRoleType.ADMIN)) {
            throw new InvalidUserRoleException("Usuário não possui o papel de ADMIN", "UserService.updateRole");
        }

        // Atualizando o papel do usuário desejado
        Optional<User> userToUpdate = userRepository.findByEmail(uRoleRequestDTO.getEmail());
        if (!userToUpdate.isPresent()) {
            throw new UserNotFoundException("Email do usuário a ser atualizado não encontrado!",
                    "UserService.updateRole");
        }
        userToUpdate.get().setPapel(uRoleRequestDTO.getPapel());
        userRepository.save(userToUpdate.get());
        return new UserDTO(userToUpdate.get());
    }

    public boolean validateUser(UserLoginDTO user) {
        Optional<User> optUser = userRepository.findByEmail(user.getEmail());
        return (optUser.isPresent() && optUser.get().getSenha().equals(user.getSenha()));
    }
}
