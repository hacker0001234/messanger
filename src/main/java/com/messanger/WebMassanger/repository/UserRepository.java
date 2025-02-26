package com.messanger.WebMassanger.repository;

import com.messanger.WebMassanger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByName(String name);

}
