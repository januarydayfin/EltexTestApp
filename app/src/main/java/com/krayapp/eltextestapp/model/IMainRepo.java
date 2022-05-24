package com.krayapp.eltextestapp.model;

import com.krayapp.eltextestapp.model.retrofit.AuthBody;

import io.reactivex.Single;

public interface IMainRepo {
    public Single<TokenInfo> getToken(AuthBody loginData);
    public Single<AboutUserEntity> getUserInfo(String key);
}
