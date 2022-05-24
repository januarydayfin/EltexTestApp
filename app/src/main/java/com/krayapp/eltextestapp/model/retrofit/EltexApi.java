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

public class EltexApi {
    private final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthInterceptor()).build();

    public RetrofitService getApi() {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitService.class);
    }

    protected class AuthInterceptor implements Interceptor {
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
