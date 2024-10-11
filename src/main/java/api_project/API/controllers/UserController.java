package api_project.API.controllers;

import api_project.API.model.UserModel;
import api_project.API.model.modeltDTO.UserDTO;
import api_project.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<UserDTO> getAllUser(){
        List<UserModel> userModels = userRepository.findAll();
        return userModels.stream().map(UserDTO::new).collect(Collectors.toList());
//        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id){
        UserDTO userDTO = new UserDTO(userRepository.findById(id).orElse(null));
        return userDTO;
//        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public UserModel createUser(@RequestBody UserModel user){
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable UUID id, @RequestBody UserModel user){
        user.setId(id);
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userRepository.deleteById(id);
    }
}
