package com.Chatop.model.DAO;

import lombok.Data;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This class is used to represent a user in the database
@Data
@Entity
@Table(name = "user")
public class UserDAO {

    // This field is used to represent the unique identifier for the user
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    // This field is used to represent the name of the user
    private String name;

    // This field is used to represent the email address of the user
    private String email;

    // This field is used to represent the password of the user
    private String password;

    // This field is used to represent the creation date of the user
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // This field is used to represent the last update date of the user
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // This method is used to set the creation date of the user before it is persisted to the database
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }

    // This method is used to set the last update date of the user before it is updated in the database
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    // This field is used to represent the list of rentals owned by the user
    @OneToMany(mappedBy = "owner")
    private List<RentalDAO> rentals;

    // This field is used to represent the list of messages sent by the user
    @OneToMany(mappedBy = "user")
    private List<MessageDAO> messages = new ArrayList<>();

}
