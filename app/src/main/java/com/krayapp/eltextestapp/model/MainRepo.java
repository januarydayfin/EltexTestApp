package com.krayapp.eltextestapp.model;

import com.krayapp.eltextestapp.Fabric;
import com.krayapp.eltextestapp.model.retrofit.AuthBody;
import com.krayapp.eltextestapp.model.retrofit.RetrofitService;

import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.RequestBody;

public class MainRepo implements IMainRepo {
    private RetrofitService apiService;

    public MainRepo(RetrofitService apiService) {
        this.apiService = apiService;
    }


    @Override
    public Single<TokenInfo> getToken(String credentials, RequestBody loginData) {
        return apiService.getToken(credentials,loginData);
    }

    @Override
    public Single<AboutUserEntity> getUserInfo(String key) {
        return apiService.getUserInfo(key);
    }
}
