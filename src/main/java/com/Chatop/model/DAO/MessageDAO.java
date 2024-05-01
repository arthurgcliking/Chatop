package com.Chatop.model.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "messages")
public class MessageDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private UserDAO user;

    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    @JsonProperty("rental_id")
    private RentalDAO rental;

    @Column(nullable = false)
    private String message;
}
