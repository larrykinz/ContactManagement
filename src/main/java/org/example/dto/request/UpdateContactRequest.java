package org.example.dto.request;

import lombok.Data;

@Data
public class UpdateContactRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String ContactId;
}
