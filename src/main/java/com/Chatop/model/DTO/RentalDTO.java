package com.Chatop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

// This class is used to represent a rental in the application
@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalDTO {

    // This field is used to represent the unique identifier for the rental
    private Long id;

    // This field is used to represent the name of the rental
    private String name;

    // This field is used to represent the surface area of the rental
    private int surface;

    // This field is used to represent the price of the rental
    private int price;

    // This field is used to represent the path to the rental's picture
    @JsonProperty("picture")
    private String picturePath;

    // This field is used to represent the description of the rental
    private String description;

    // This field is used to represent the unique identifier for the owner of the rental
    private Integer owner_id;

    // This field is used to represent the creation date of the rental
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date created_at;

    // This field is used to represent the last update date of the rental
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date updated_at;
}
