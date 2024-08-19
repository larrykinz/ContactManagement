package org.example.services.interfaces;

import org.example.dto.request.*;
import org.example.dto.response.*;

public interface UserSevices {
    public AddUserResponse addUser(AddUserRequest addUserRequest);

    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest);

    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);

    public SearchUsersResponse searchAllUsers();

    public SearchUsersByNameResponse searchUsersByName(SearchUsersByNameRequest request);
}
