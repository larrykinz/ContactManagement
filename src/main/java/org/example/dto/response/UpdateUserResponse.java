package org.example.dto.response;

import lombok.Data;

@Data
public class UpdateUserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String message;
}
