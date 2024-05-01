package com.Chatop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Chatop.model.DAO.RentalDAO;

@Repository
public interface RentalRepository extends JpaRepository<RentalDAO, Long> {
}
