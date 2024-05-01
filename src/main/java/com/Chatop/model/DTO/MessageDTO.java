package com.Chatop.model.DTO;

import lombok.Data;

@Data
public class MessageDTO {
    private String message;
    private Integer user_id;
    private Long rental_id;
}
