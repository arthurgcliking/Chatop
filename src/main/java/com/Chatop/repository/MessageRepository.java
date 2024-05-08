package com.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.MessageDAO;

// This interface is used to represent a repository for managing messages in the database
@Repository
public interface MessageRepository extends JpaRepository<MessageDAO, Long> {

    // This interface extends JpaRepository, which provides basic CRUD operations for the MessageDAO entity
    // The Long parameter specifies the type of the primary key for the MessageDAO entity

}
