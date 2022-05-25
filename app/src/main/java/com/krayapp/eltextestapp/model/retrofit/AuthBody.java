package com.krayapp.eltextestapp.model.retrofit;

public class AuthBody {
    private String grant_type;
    private String username;
    private String password;

    public AuthBody(String grant_type, String username, String password){
        this.grant_type = grant_type;
        this.username = username;
        this.password = password;
    }
}
