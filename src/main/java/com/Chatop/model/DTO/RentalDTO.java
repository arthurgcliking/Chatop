package com.Chatop.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import java.util.Date;

@Getter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalDTO {
    private Long id;

    private String name;

    private int surface;

    private int price;

    @JsonProperty("picture")
    private String picturePath;

    private String description;

    private Integer owner_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date created_at;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date updated_at;
}
