package com.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.MessageDAO;

@Repository
public interface MessageRepository extends JpaRepository<MessageDAO, Long> {
}
