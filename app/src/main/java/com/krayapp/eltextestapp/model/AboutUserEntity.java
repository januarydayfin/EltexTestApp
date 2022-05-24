package com.krayapp.eltextestapp.model;

import java.util.List;

public class AboutUserEntity {
    private String roleId = " ";
    private String username = " ";
    private String email = " ";
    private List<String> permissions;


    public AboutUserEntity(String roleId, String username, String email){
        this.roleId = roleId;
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public String getRoleId() {
        return roleId;
    }
    public String getUsername() {
        return username;
    }

}
