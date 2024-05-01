package com.Chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.UserDAO;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByName(String username);

    UserDAO findByEmail(String email);
}
