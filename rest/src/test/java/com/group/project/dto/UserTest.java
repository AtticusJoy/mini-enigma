package com.group.project.dto;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void getUsername() {
        user.setUsername("jane");
        assertThat(user.getUsername()).isEqualTo("jane");
    }

    @Test
    public void getRole() {
        user.setRole("Employee");
        assertThat(user.getRole()).isEqualTo("Employee");
    }
}