package com.springbootform.dao;

import com.springbootform.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLoginAndPassword(String login, String password);

    Optional<UserEntity> findFirstByLogin(String login);
}
