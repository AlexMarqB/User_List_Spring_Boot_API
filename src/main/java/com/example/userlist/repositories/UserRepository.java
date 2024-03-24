package com.example.userlist.repositories;

import com.example.userlist.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    List<UserModel> findAllByEmail(String email);
    List<UserModel> findAllByName(String name);
    List<UserModel> findAllByAge(int age);
}
