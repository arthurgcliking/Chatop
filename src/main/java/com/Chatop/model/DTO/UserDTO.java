package com.Chatop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import java.util.Date;

// This class is used to represent a user in the application
@Getter
@Data
public class UserDTO {

    // This field is used to represent the unique identifier for the user
    private Integer id;

    // This field is used to represent the name of the user
    private String name;

    // This field is used to represent the email address of the user
    private String email;

    // This field is used to represent the creation date of the user
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date created_at;

    // This field is used to represent the last update date of the user
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date updated_at;

    // This constructor is used to initialize a new instance of the UserDTO class with the specified parameters
    public UserDTO(Integer id, String name, String email, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
