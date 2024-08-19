package org.example.dto.request;

import lombok.Data;

@Data
public class SearchUsersByNameRequest {
    private String firstName;
    private String lastName;
}

