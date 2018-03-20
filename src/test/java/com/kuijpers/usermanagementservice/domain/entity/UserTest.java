package com.kuijpers.usermanagementservice.domain.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserTest {

    @Before
    public void setup() {

    }


    @Test
    public void testConstructor() {

        User user = new User("1","rob");

        assertEquals(user.getId(), "1");
        assertEquals(user.getName(), "rob");


    }

    @Test
    public void testToString() {

        User user = new User("1", "rob");

        assertEquals(user.toString(), "User(id=1, name=rob)");

    }

}
