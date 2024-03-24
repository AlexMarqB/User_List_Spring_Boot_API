package com.example.userlist.controllers;

import com.example.userlist.dtos.UserRecordDto;
import com.example.userlist.models.UserModel;
import com.example.userlist.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/create")
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/email{email}")
    public ResponseEntity<List<UserModel>> getUsersByEmail(@PathVariable(value = "email") String email) {
        List<UserModel> users = userRepository.findAllByEmail(email);
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/name{name}")
    public ResponseEntity<List<UserModel>> getUsersByName(@PathVariable(value = "name") String name) {
        List<UserModel> users = userRepository.findAllByName(name);
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/age{age}")
    public ResponseEntity<List<UserModel>> getUsersByAge(@PathVariable(value = "age") int age) {
        List<UserModel> users = userRepository.findAllByAge(age);
        if(users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @DeleteMapping("/user/id{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> user = userRepository.findById(id);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
        userRepository.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully!");
    }
}
