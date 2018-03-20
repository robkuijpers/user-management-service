package com.kuijpers.usermanagementservice.domain.service.impl;

import com.kuijpers.usermanagementservice.domain.entity.User;
import com.kuijpers.usermanagementservice.domain.service.UserService;
import com.kuijpers.usermanagementservice.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    /**
     * Constructor
     */
    public UserServiceImpl(UserRepository repository) {

        this.repository = repository;

    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> findAll() throws Exception {

        return this.repository.findAll();

    }

    /**
     * @inheritDoc
     */
    @Override
    public User findById(String id) throws Exception {

        List<User> users = this.repository.findById(id);

        if(users.size() == 1) {

            return users.get(0);

        } else {

            if(users.size() == 0) {

                throw new Exception("User not found with id [" + id + "]");

            } else  {

                throw new Exception("Multiple users found with id [" + id + "]");
            }
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public List<User> findByName(String name) throws Exception {

        return this.repository.findByName(name);

    }

    /**
     * @inheritDoc
     */
    @Override
    public User add(User user) throws Exception {

        return this.repository.save(user);

    }

    /**
     * @inheritDoc
     */
    @Override
    public User update(User user) throws Exception {

        if (this.repository.exists(user.getId())) {
            return this.repository.save(user);
        } else {
            throw new Exception("Update of user failed, user with id [" + user.getId() + "] does not exist");
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public void delete(String id) throws Exception {

        if(this.repository.exists(id)) {
            this.repository.delete(id);
        } else {
            throw new Exception("Delete of user failed, user with id [" + id + "] does not exist");
        }

    }
}
