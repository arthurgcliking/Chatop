package com.Chatop.model.DAO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;

// This class is used to represent a message in the database
@Data
@Entity
@Table(name = "messages")
public class MessageDAO {

    // This field is used to represent the unique identifier for the message
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This field is used to represent the user who sent the message
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty("user_id")
    private UserDAO user;

    // This field is used to represent the rental associated with the message
    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    @JsonProperty("rental_id")
    private RentalDAO rental;

    // This field is used to represent the content of the message
    @Column(nullable = false)
    private String message;
}
