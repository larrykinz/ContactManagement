package org.example.dto.request;

import lombok.Data;

@Data
public class SearchContactsRequest {
    private String firstName;
    private String lastName;
}
