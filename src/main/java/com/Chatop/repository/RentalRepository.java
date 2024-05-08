package com.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.RentalDAO;

// This interface is used to represent a repository for managing rentals in the database
@Repository
public interface RentalRepository extends JpaRepository<RentalDAO, Long> {

    // This interface extends JpaRepository, which provides basic CRUD operations for the RentalDAO entity
    // The Long parameter specifies the type of the primary key for the RentalDAO entity

}
