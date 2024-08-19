package org.example.dto.response;

import lombok.Data;

@Data

public class DeleteContactResponse {
    private String contactId;
    private String message="";
}
