package com.krayapp.eltextestapp.model;

public class TokenInfo {
    private String access_token = " ";
    private String token_type = " ";
    private String refresh_token = " ";
    private String expires_in = " ";
    private String scope = " ";

    public TokenInfo(String access_token, String token_type, String refresh_token,
                     String expires_in, String scope) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }
}
