package com.krayapp.eltextestapp.model.retrofit;

import com.krayapp.eltextestapp.Constant;
import com.krayapp.eltextestapp.model.AboutUserEntity;
import com.krayapp.eltextestapp.model.TokenInfo;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST(Constant.TOKEN_URL)
    public Single<TokenInfo> getToken(@Body AuthBody loginData);

    public Single<AboutUserEntity> getUserInfo(String key);
}
