package com.Chatop.model.DTO;

import lombok.Data;

// This class is used to represent a message in the application
@Data
public class MessageDTO {

    // This field is used to represent the content of the message
    private String message;

    // This field is used to represent the unique identifier for the user who sent the message
    private Integer user_id;

    // This field is used to represent the unique identifier for the rental associated with the message
    private Long rental_id;

}
