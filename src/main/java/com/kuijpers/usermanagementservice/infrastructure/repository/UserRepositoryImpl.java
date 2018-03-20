package com.kuijpers.usermanagementservice.infrastructure.repository;

import com.kuijpers.usermanagementservice.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Override
    public void someSpecificMethod() {
        System.out.printf("some specific method invoked");
    }
}
