package org.example.dto.response;

import lombok.Data;

@Data
public class AddUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String message;
    private String userId;
}
