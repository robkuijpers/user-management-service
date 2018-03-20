package com.kuijpers.usermanagementservice.domain.service;

import com.kuijpers.usermanagementservice.domain.entity.User;
import com.kuijpers.usermanagementservice.domain.service.impl.UserServiceImpl;
import com.kuijpers.usermanagementservice.infrastructure.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {

    }

    @Test
    public void getAllUsersTest() throws Exception {

        // Arrange
        List<User> expectedResponse = new ArrayList<User>();
        expectedResponse.add(new User("1", "rob"));

        when(this.userRepository.findAll()).thenReturn(expectedResponse);

        // Act
        List<User> actualResponse = userService.findAll();

        // Assert
        assertEquals(expectedResponse, actualResponse);

    }

    @Test
    public void findById_UserNotFoundTest() throws Exception {

        // Arrange

        //test type
        this.thrown.expect(Exception.class);

        //test message
        this.thrown.expectMessage(is("User not found with id [1]"));

        //test details
        // this.thrown.expect(hasProperty("errCode"));  //make sure getters n setters are defined.
        // this.thrown.expect(hasProperty("errCode", is(666)));

        List<User> expectedResponse = new ArrayList<User>();

        when(userRepository.findById("1")).thenReturn(expectedResponse);

        // Act
//        try {

            User user = this.userService.findById("1");

//        } catch(Exception e) {
//
//            // Assert
//            assertThat(e.getMessage(), containsString("User not found with id [1]"));
//        }

    }


}

