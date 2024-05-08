package com.Chatop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.UserDAO;

// This interface is used to represent a repository for managing users in the database
@Repository
public interface UserRepository extends CrudRepository<UserDAO, Integer> {

    // This interface extends CrudRepository, which provides basic CRUD operations for the UserDAO entity
    // The Integer parameter specifies the type of the primary key for the UserDAO entity

    // This method is used to find a user by their username
    UserDAO findByName(String username);

    // This method is used to find a user by their email address
    UserDAO findByEmail(String email);

}
