package org.example.dto.response;

import lombok.Data;
import org.example.data.models.Contact;

import java.util.List;

@Data
public class SearchContactsResponse {
    private List<Contact> contacts;
    private String message;
}
