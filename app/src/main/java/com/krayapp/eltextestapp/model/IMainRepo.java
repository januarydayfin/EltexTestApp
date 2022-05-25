package com.krayapp.eltextestapp.model;

import com.krayapp.eltextestapp.model.retrofit.AuthBody;

import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.RequestBody;

public interface IMainRepo {
     Single<TokenInfo> getToken(String credentials, RequestBody loginData);
     Single<AboutUserEntity> getUserInfo(String key);
}
