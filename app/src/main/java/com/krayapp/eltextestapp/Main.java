package com.krayapp.eltextestapp;

import com.krayapp.eltextestapp.model.AboutUserEntity;
import com.krayapp.eltextestapp.model.IMainRepo;
import com.krayapp.eltextestapp.model.MainRepo;
import com.krayapp.eltextestapp.model.TokenInfo;
import com.krayapp.eltextestapp.model.retrofit.AuthBody;
import com.krayapp.eltextestapp.model.retrofit.EltexApi;
import com.krayapp.eltextestapp.model.retrofit.RetrofitService;
import com.krayapp.movieapppoplib.Schedulers.ISchedulers;
import com.krayapp.movieapppoplib.Schedulers.SimpleShedulers;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Main {
    public static void main(String[] args) throws JSONException {
        RetrofitService api = EltexApi.getApi();
        IMainRepo repo = new MainRepo(api);
        ISchedulers scheduler = new SimpleShedulers();
        CompositeDisposable disposable = new CompositeDisposable();

        // тело запроса
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "password")
                .addFormDataPart("username", "tester")
                .addFormDataPart("password", "tester")
                .build();
        disposable.add(
                // запрос юзера
               repo
                        /*.getUserInfo("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJleHAiOjE2NTM0NTg2ODMsImlhdCI6MTY1MzQ1NTA4MywianRpIjoiODYwN2E1OTItOGM5Yy00NGExLWIxZWUtM2I0YzczNjhlMzYxIn0.swvmNF6jUdEgwW1BpcNwhgSMNo_HIsTT3tabY5uoxAs")
                        .subscribe(Main::setUser,throwable -> System.out.println(throwable.toString()))*/

                //запрос токена
                        .getToken("Basic YW5kcm9pZC1jbGllbnQ6cGFzc3dvcmQ=", requestBody)
                        .subscribe(Main::setToken, throwable -> System.out.println(throwable.toString()))


        );
    }

    public static void setToken(TokenInfo tokenInfo) {
        String token = tokenInfo.getAccess_token();
        System.out.println(token);
    }

    public static void setUser(AboutUserEntity userEntity) {
        String user = userEntity.getRoleId();
        System.out.println(user);
    }
}
