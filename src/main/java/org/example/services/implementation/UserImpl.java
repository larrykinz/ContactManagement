package org.example.services.implementation;

import org.example.data.models.User;
import org.example.data.repository.UserRepository;
import org.example.dto.request.AddUserRequest;
import org.example.dto.request.DeleteUserRequest;
import org.example.dto.request.SearchUsersByNameRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.*;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserDoesNotExistException;
import org.example.services.interfaces.UserSevices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class UserImpl implements UserSevices {
    @Autowired
    private UserRepository userRepository;

   public AddUserResponse addUser(AddUserRequest addUserRequest){
       for(User user : userRepository.findAll()){
           if(user.getEmail().equals(addUserRequest.getEmail())){
               throw new UserAlreadyExistException("User already exists");
           }

           user.setFirstName(addUserRequest.getFirstName());
           user.setLastName(addUserRequest.getLastName());
           user.setEmail(addUserRequest.getEmail());
           user.setPassword(addUserRequest.getPassword());
           userRepository.save(user);
       }
       AddUserResponse addUserResponse = new AddUserResponse();
       addUserResponse.setFirstName(addUserRequest.getFirstName());
       addUserResponse.setLastName(addUserRequest.getLastName());
       addUserResponse.setEmail(addUserRequest.getEmail());
       addUserResponse.setMessage("Successfully added user");
       return addUserResponse;

   }
    public DeleteUserResponse deleteUser(DeleteUserRequest deleteUserRequest) {
        User user = userRepository.findById(deleteUserRequest.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("User does not exist with this ID."));
        
        userRepository.delete(user);

        DeleteUserResponse deleteUserResponse = new DeleteUserResponse();
        deleteUserResponse.setUserId(deleteUserRequest.getUserId());
        deleteUserResponse.setMessage("User successfully removed.");

        return deleteUserResponse;
    }
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        User userToUpdate = userRepository.findById(updateUserRequest.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("User does not exist with this ID."));
        
        userToUpdate.setFirstName(updateUserRequest.getFirstName());
        userToUpdate.setLastName(updateUserRequest.getLastName());
        userToUpdate.setEmail(updateUserRequest.getEmail());
        userToUpdate.setPassword(updateUserRequest.getPassword());
        userRepository.save(userToUpdate);

        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setFirstName(userToUpdate.getFirstName());
        updateUserResponse.setLastName(userToUpdate.getLastName());
        updateUserResponse.setEmail(userToUpdate.getEmail());
        updateUserResponse.setMessage("User information successfully updated");

        return updateUserResponse;
    }
    public SearchUsersResponse searchAllUsers() {
        List<User> users = userRepository.findAll();
        SearchUsersResponse searchUsersResponse = new SearchUsersResponse();
        searchUsersResponse.setUsers(users);
        searchUsersResponse.setMessage("All users found");

        return searchUsersResponse;
    }

    public SearchUsersByNameResponse searchUsersByName(SearchUsersByNameRequest request) {
        List<User> users = userRepository.findAll();
        List<User> matchedUsers = new ArrayList<>();

        String searchFirstName = request.getFirstName();
        String searchLastName = request.getLastName();

        for (User user : users) {
            boolean firstNameMatches = searchFirstName == null || user.getFirstName().equals(searchFirstName);
            boolean lastNameMatches = searchLastName == null || user.getLastName().equals(searchLastName);

            if (firstNameMatches && lastNameMatches) {
                matchedUsers.add(user);
            }
        }

        SearchUsersByNameResponse searchUsersResponse = new SearchUsersByNameResponse();
        searchUsersResponse.setUsers(matchedUsers);
        searchUsersResponse.setMessage("Users found with the specified criteria");

        return searchUsersResponse;
    }

}
