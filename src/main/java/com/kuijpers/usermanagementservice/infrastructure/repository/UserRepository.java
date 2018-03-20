package com.kuijpers.usermanagementservice.infrastructure.repository;

import com.kuijpers.usermanagementservice.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {


    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<User> findById(final String id);

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    List<User> findByName(String name);

}
