package org.example.services.implementation;

import org.example.data.repository.UserRepository;
import org.example.dto.request.AddUserRequest;
import org.example.dto.request.DeleteUserRequest;
import org.example.dto.request.SearchUsersByNameRequest;
import org.example.dto.request.UpdateUserRequest;
import org.example.dto.response.*;
import org.example.exception.UserAlreadyExistException;
import org.example.exception.UserDoesNotExistException;
import org.example.services.interfaces.UserSevices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSevices userSevices;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void addUserTest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Johns");
        addUserRequest.setLastName("Does");
        addUserRequest.setEmail("johncenasss.doe@example.com");
        addUserRequest.setPassword("password123");

        AddUserResponse addUserResponse = userSevices.addUser(addUserRequest);
        assertThat(addUserResponse).isNotNull();
        assertThat(addUserResponse.getFirstName()).isEqualTo("Johns");
        assertThat(addUserResponse.getLastName()).isEqualTo("Does");
        assertThat(addUserResponse.getEmail()).isEqualTo("johncenasss.doe@example.com");
        assertThat(addUserResponse.getMessage()).isEqualTo("Successfully added user");
    }

    @Test
    public void testToAddAlreadyExistingUserThrowsException() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("John");
        addUserRequest.setLastName("Doe");
        addUserRequest.setEmail("john.doe@example.com");
        addUserRequest.setPassword("password123");
        userSevices.addUser(addUserRequest);

        try {
            userSevices.addUser(addUserRequest);
        } catch (UserAlreadyExistException e) {
            assertThat(e.getMessage()).isEqualTo("User already exists");
        }
    }
@Test
public void DeleteUserTest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setUserId("5567");
        userSevices.addUser(addUserRequest);

        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        DeleteUserResponse deleteUserResponse = userSevices.deleteUser(deleteUserRequest);
        deleteUserRequest.setUserId(addUserRequest.getUserId());
        assertThat(deleteUserRequest).isNotNull();
        assertThat(deleteUserRequest.getUserId()).isEqualTo(addUserRequest.getUserId());
        assertThat(deleteUserResponse.getMessage()).isEqualTo("User successfully removed.");
}

    @Test
    public void testToDeleteUserThrowsException() {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserId("non-existent-id");
        try {
            userSevices.deleteUser(deleteUserRequest);
        } catch (UserDoesNotExistException e) {
            assertThat(e.getMessage()).isEqualTo("User does not exist with this ID.");
        }
    }

    @Test
    public void updateUserTest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Alice");
        addUserRequest.setLastName("Smith");
        addUserRequest.setEmail("alice.smith@example.com");
        addUserRequest.setPassword("password123");
        AddUserResponse addUserResponse = userSevices.addUser(addUserRequest);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setUserId(addUserResponse.getUserId());
        updateUserRequest.setFirstName("Alice");
        updateUserRequest.setLastName("Johnson");
        updateUserRequest.setEmail("alice.johnson@example.com");
        updateUserRequest.setPassword("newpassword123");

        UpdateUserResponse updateUserResponse = userSevices.updateUser(updateUserRequest);
        assertThat(updateUserResponse).isNotNull();
        assertThat(updateUserResponse.getFirstName()).isEqualTo("Alice");
        assertThat(updateUserResponse.getLastName()).isEqualTo("Johnson");
        assertThat(updateUserResponse.getEmail()).isEqualTo("alice.johnson@example.com");
        assertThat(updateUserResponse.getMessage()).isEqualTo("User information successfully updated");
    }

    @Test
    public void searchAllUsersTest() {
        AddUserRequest addUserRequest1 = new AddUserRequest();
        addUserRequest1.setFirstName("Bob");
        addUserRequest1.setLastName("Brown");
        addUserRequest1.setEmail("bob.brown@example.com");
        addUserRequest1.setPassword("password123");
        userSevices.addUser(addUserRequest1);

        AddUserRequest addUserRequest2 = new AddUserRequest();
        addUserRequest2.setFirstName("Charlie");
        addUserRequest2.setLastName("Davis");
        addUserRequest2.setEmail("charlie.davis@example.com");
        addUserRequest2.setPassword("password123");
        userSevices.addUser(addUserRequest2);

        SearchUsersResponse searchUsersResponse = userSevices.searchAllUsers();
        assertThat(searchUsersResponse).isNotNull();
        assertThat(searchUsersResponse.getUsers()).hasSize(2);
        assertThat(searchUsersResponse.getMessage()).isEqualTo("All users found");
    }

    @Test
    public void searchUsersByNameTest() {
        AddUserRequest addUserRequest1 = new AddUserRequest();
        addUserRequest1.setFirstName("David");
        addUserRequest1.setLastName("Clark");
        addUserRequest1.setEmail("david.clark@example.com");
        addUserRequest1.setPassword("password123");
        userSevices.addUser(addUserRequest1);

        AddUserRequest addUserRequest2 = new AddUserRequest();
        addUserRequest2.setFirstName("Eva");
        addUserRequest2.setLastName("White");
        addUserRequest2.setEmail("eva.white@example.com");
        addUserRequest2.setPassword("password123");
        userSevices.addUser(addUserRequest2);

        SearchUsersByNameRequest searchUsersByNameRequest = new SearchUsersByNameRequest();
        searchUsersByNameRequest.setFirstName("Eva");
        searchUsersByNameRequest.setLastName("White");

        SearchUsersByNameResponse searchUsersByNameResponse = userSevices.searchUsersByName(searchUsersByNameRequest);
        assertThat(searchUsersByNameResponse).isNotNull();
        assertThat(searchUsersByNameResponse.getUsers()).hasSize(1);
        assertThat(searchUsersByNameResponse.getUsers().get(0).getEmail()).isEqualTo("eva.white@example.com");
        assertThat(searchUsersByNameResponse.getMessage()).isEqualTo("Users found with the specified criteria");
    }
}
