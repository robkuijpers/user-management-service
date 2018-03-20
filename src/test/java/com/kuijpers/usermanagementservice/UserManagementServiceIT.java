package com.kuijpers.usermanagementservice;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.kuijpers.usermanagementservice.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {UserManagementServiceApplication.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("it")
@DatabaseSetup("user.xml")
public class UserManagementServiceIT {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    /**
     * @throws Exception
     */
    @Test
    public void getAllUsersTest() throws Exception {

        // Arrange
        final List<User> users = new ArrayList<User>();
        users.add(new User("1", "Rob"));
        users.add(new User("1", "Bram"));

        final HttpEntity requestEntity = new HttpEntity(new HttpHeaders());

        // Act
        final ResponseEntity response = testRestTemplate.exchange("/usermanagementservice/users", HttpMethod.GET, requestEntity, Object.class);

        // Assert
        //assertThat(response.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0)).isNotEmpty();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).asList().size().isEqualTo(2);
        // assertThat() elements in array, should be json!

    }

    /**
     * @throws Exception
     */
    @Test
    public void getUserTest() throws Exception {

        // Arrange

        // Act
        final ResponseEntity<User> response = testRestTemplate.getForEntity("/usermanagementservice/users/{id}", User.class, "1");

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId().equals("1"));
        assertThat(response.getBody().getName().equals("rob"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void addUserTest() throws Exception {

        // Arrange
        final User user = new User("1", "rob");

        // Act
        final ResponseEntity<User> response = testRestTemplate.postForEntity("/usermanagementservice/users", user, User.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId().equals("1"));
        assertThat(response.getBody().getName().equals("rob"));

    }

    /**
     * @throws Exception
     */
    @Test
    public void updateUserTest() throws Exception {

        // Arrange
        final User user = new User("1", "rob");

        // Act
        testRestTemplate.put("/usermanagementservice/users/{id}", user, "1");

        // Assert

    }

    /**
     * @throws Exception
     */
    @Test
    public void deleteUserTest() throws Exception {

        // Arrange

        // Act
        testRestTemplate.delete("/usermanagementservice/users/{id}", "1");

        // Assert

    }

}
