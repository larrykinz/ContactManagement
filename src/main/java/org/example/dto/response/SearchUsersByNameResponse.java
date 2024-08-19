package org.example.dto.response;

import lombok.Data;
import org.example.data.models.User;

import java.util.List;

@Data
public class SearchUsersByNameResponse {
    private List<User> users;
    private String message;
}

