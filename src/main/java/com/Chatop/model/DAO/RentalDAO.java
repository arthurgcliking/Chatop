package com.Chatop.model.DAO;

import lombok.Data;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This class is used to represent a rental in the database
@Data
@Entity
@Table(name = "rental")
public class RentalDAO {

    // This field is used to represent the unique identifier for the rental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This field is used to represent the name of the rental
    private String name;

    // This field is used to represent the surface area of the rental
    private int surface;

    // This field is used to represent the price of the rental
    private int price;

    // This field is used to represent the path to the rental's picture
    @Column(name = "picture_path")
    private String picturePath;

    // This field is used to represent the description of the rental
    @Column(columnDefinition = "TEXT")
    private String description;

    // This field is used to represent the owner of the rental
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDAO owner;

    // This field is used to represent the creation date of the rental
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // This field is used to represent the last update date of the rental
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // This method is used to set the creation date of the rental before it is persisted to the database
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        if (updatedAt == null) {
            updatedAt = createdAt;
        }
    }

    // This method is used to set the last update date of the rental before it is updated in the database
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    // This field is used to represent the list of messages associated with the rental
    @OneToMany(mappedBy = "rental")
    private List<MessageDAO> messages = new ArrayList<>();

}
