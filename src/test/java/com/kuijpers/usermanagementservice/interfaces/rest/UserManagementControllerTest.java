package com.kuijpers.usermanagementservice.interfaces.rest;

import com.kuijpers.usermanagementservice.config.ApplicationConfig;
import com.kuijpers.usermanagementservice.domain.entity.User;
import com.kuijpers.usermanagementservice.domain.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kuijpers.usermanagementservice.auth.security.SecurityConstants.EXPIRATION_TIME;
import static com.kuijpers.usermanagementservice.auth.security.SecurityConstants.SECRET;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {UserManagementController.class, ApplicationConfig.class, UserService.class})

public class UserManagementControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

    }

    /**
     * Test if the endpoint "/usermanagementservice/users" returns all users and the expected headers.
     *
     * @throws Exception
     */
    @Test
    public void allUsers_delegatesToUserService() throws Exception {

        // Arrange
        List<User> expectedUserListResponse = new ArrayList<User>();
        expectedUserListResponse.add(new User("1", "Robbie"));

        // Act
        when(userService.findAll()).thenReturn(expectedUserListResponse);

        // Assert
        mockMvc.perform(
                    get("/usermanagementservice/users")
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .with(getHeaders())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is("1")))
                .andExpect(jsonPath("$.[0].name", is("Robbie")));

    }

    /**
     * Enrich mock http servlet request with header fields.
     *
     * @return
     */
    private RequestPostProcessor getHeaders() {

          // Lambda expression, interface with one method.
        return mockHttpServletRequest -> {
            mockHttpServletRequest.addHeader("Authorization", "Bearer " + generateJWTToken("rob"));
            return mockHttpServletRequest;
        };

        // Non lambda implementation
//        RequestPostProcessor requestPostProcessor = new RequestPostProcessor() {
//            @Override
//            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest) {
//                mockHttpServletRequest.addHeader("", "");
//                return mockHttpServletRequest;
//            }
//        };
//
//        return requestPostProcessor;

    }

    /**
     *
     * @param name
     * @return
     */
    private String generateJWTToken(String name) {

        String token = Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        return token;
    }

}
