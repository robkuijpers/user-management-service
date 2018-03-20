package com.kuijpers.usermanagementservice.domain.service;

import com.kuijpers.usermanagementservice.domain.entity.User;

import java.util.List;

/**
 *
 */
public interface UserService {

    /**
     * Return a list with all users.
     *
     * @return a list with User {@link com.kuijpers.usermanagementservice.domain.entity.User} instances.
     * @throws Exception
     */
    List<User> findAll() throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    User findById(final String name) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<User> findByName(final String name) throws Exception;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    User add(final User user) throws Exception;

    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    User update(final User user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    void delete(final String id) throws Exception;

}
