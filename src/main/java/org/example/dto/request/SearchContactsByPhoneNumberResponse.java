package org.example.dto.request;

import lombok.Data;
import org.example.data.models.Contact;

import java.util.List;

@Data
public class SearchContactsByPhoneNumberResponse {
    private List<Contact> contacts;
    private String message;
}
