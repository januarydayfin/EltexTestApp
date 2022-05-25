package com.krayapp.eltextestapp.model.retrofit;

import androidx.annotation.NonNull;

import com.krayapp.eltextestapp.Constant;

import java.io.IOException;
import java.util.Base64;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class EltexApi {
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthInterceptor()).build();

    public static RetrofitService getApi() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitService.class);
    }

    protected static class AuthInterceptor implements Interceptor {
        private String credentials;

        public AuthInterceptor() {
            this.credentials = Credentials.basic("android-client", "password");
        }

        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder()
                    .header("Authorization", credentials)
                    .build();
            return chain.proceed(authenticatedRequest);
        }
    }
}
