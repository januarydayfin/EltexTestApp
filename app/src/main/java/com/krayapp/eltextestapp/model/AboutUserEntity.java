package com.krayapp.eltextestapp.model;

import java.util.ArrayList;
import java.util.List;

public class AboutUserEntity {
    private String roleId = " ";
    private String username = " ";
    private String email = " ";
    private List<String> permissions;


    public AboutUserEntity(String roleId, String username, String email, List<String> permissions){
        this.roleId = roleId;
        this.username = username;
        this.email = email;
        this.permissions = permissions;
    }

    public String getEmail() {
        if (email == null ) {
            return "Отсутствует";
        }else
            return email;
    }
    public String getRoleId() {
        return roleId;
    }
    public String getUsername() {
        return username;
    }
    public List<String> getPermissions() {
        return permissions;
    }
}
