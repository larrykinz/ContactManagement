package org.example.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
