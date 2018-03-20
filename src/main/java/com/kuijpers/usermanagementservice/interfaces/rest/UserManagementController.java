package com.kuijpers.usermanagementservice.interfaces.rest;

import com.kuijpers.usermanagementservice.config.ApplicationConfig;
import com.kuijpers.usermanagementservice.domain.entity.User;
import com.kuijpers.usermanagementservice.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@RestController()
@RequestMapping(value = "/usermanagementservice")
public class UserManagementController {

    @Autowired
    private UserService userService;

    /**
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

    /**
     * Return info (html format) about this endpoint.
     *
     * @return
     */
    @GetMapping(value = "/info", produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String info() {

        LOGGER.debug("UMS --- info");

        String html = "<p>User management service maintains users of the system.</p> It contains the following REST functions:";
        html += "<ul><li>/usermanagementservice/api/users [GET] -> return a list of all users.</li>";
        html += "<li>/usermanagementservice/api/users/:id [GET] -> ....</li>";
        html += "<li>/usermanagementservice/api/users [POST] -> add a new user, json payload contains user data, return new user.</li>";
        html += "<li>/usermanagementservice/api/users/:id [PUT] -> ....</li>";
        html += "<li>/usermanagementservice/api/users/:id [DELETE] -> ....</li></ul>";
        html += "<p>Return codes and messages</p>";
        html += "<ul><li>200 : successfully executed.</li>";
        html += "<li>404 : ....</li>";
        html += "<li>500 : ....</li></ul>";

        return html;

    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> all() throws Exception {

        LOGGER.debug("UMS --- list all users");

        List<User> users = userService.findAll();

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noStore())
                .body(users);

        //return users;

        //return Collections.singletonMap("data", users);

    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/users/{id}", produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> get(@PathVariable String id) throws Exception {

        LOGGER.debug("UMS --- get user with id [" + id + "]");

        try {

            User user = this.userService.findById(id);

            return ResponseEntity
                    .ok()
                    .cacheControl(CacheControl.noStore())
                    .body(user);

        } catch(Exception e) {

            LOGGER.warn("UMS --- error getting user with id [" + id + "]", e);

            throw e;
        }



    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/users", consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE, produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> add(@RequestBody User user) throws Exception {

        LOGGER.debug("UMS --- add user");

        try {

            User newUser = this.userService.add(user);

            return ResponseEntity
                    .created(new URI("/usermanagementservice/user/" + newUser.getId()))
                    .cacheControl(CacheControl.noStore())
                    .body(newUser);

        } catch(Exception e) {

            LOGGER.warn("UMS --- error adding user with name [" + user.getName() + "]",  e);

            throw e;
        }

    }

    /**
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping(value = "/users/{id}", consumes =  MediaType.APPLICATION_JSON_UTF8_VALUE, produces =  MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User update(@PathVariable String id, @RequestBody User user) throws Exception {

        LOGGER.debug("UMS --- edit user with id [" + id + "]");

        try {

            return this.userService.update(user);

        } catch(Exception e) {

            LOGGER.warn("UMS --- error updating user with id [" + id + "]", e);

            throw e;
        }
    }

    /**
     *
     * @param id
     */
    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable String id) throws Exception {

        LOGGER.debug("UMS --- delete user with id [" + id + "]");

        try {

            this.userService.delete(id);

        } catch(Exception e) {

            LOGGER.warn("UMS --- error deleting user with id [" + id + "]", e);

            throw e;
        }

    }


    @PostConstruct
    public void init() throws Exception {

    }

    @PreDestroy
    public void cleanup() throws Exception {

    }

      // Also possible to implement exception handling on every controller i.s.o ErrorController with @RestControllerAdvice
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(Exception ex) {
//
//
//        ErrorResponse error = new ErrorResponse();
//        error.setError(ex.toString());
//        error.setMessage("");
//        error.setPath("");
//        error.setStatus(404);
//        error.setTimestamp(new Date().getTime());
//
//        return ResponseEntity
//                .badRequest()
//                .cacheControl(CacheControl.noStore())
//                .body(error);
//    }

}