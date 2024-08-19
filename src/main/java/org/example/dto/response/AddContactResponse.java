package org.example.dto.response;

import lombok.Data;

@Data
public class AddContactResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
