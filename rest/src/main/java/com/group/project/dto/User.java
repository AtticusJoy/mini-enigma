/*******************************************************************************************
 * File: User.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Data transfer object composed of user information
 *
 ******************************************************************************************/

package com.group.project.dto;

public class User {

    private String username;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
