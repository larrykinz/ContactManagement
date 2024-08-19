package org.example.dto.response;

import lombok.Data;

@Data
public class UpdateContactResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String message;

}
