package com.krayapp.eltextestapp.model.retrofit;

import com.krayapp.eltextestapp.Constant;
import com.krayapp.eltextestapp.model.AboutUserEntity;
import com.krayapp.eltextestapp.model.TokenInfo;

import org.json.JSONObject;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    @POST(Constant.TOKEN_URL)
    Single<TokenInfo> getToken(
            @Header("Authorization") String credentials,
            @Body RequestBody loginData
    );


    @GET(Constant.USER_INFO)
    Single<AboutUserEntity> getUserInfo(@Header("Authorization") String key);
}
