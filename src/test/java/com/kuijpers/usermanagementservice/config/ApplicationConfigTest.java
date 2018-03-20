package com.kuijpers.usermanagementservice.config;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = {ApplicationConfig.class})
public class ApplicationConfigTest {

    @Autowired
    private ApplicationConfig applicationConfig;

    @Before
    public void setup() {

    }

    /**
     * Test if the application context is loaded and contains the correct application name.
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void applicationConfigTest() {

        assertEquals("UserMaintenanceService", this.applicationConfig.getName());

    }

}
