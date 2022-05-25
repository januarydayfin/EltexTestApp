package com.krayapp.eltextestapp;

import com.krayapp.eltextestapp.model.IMainRepo;
import com.krayapp.eltextestapp.model.MainRepo;
import com.krayapp.eltextestapp.model.retrofit.EltexApi;
import com.krayapp.eltextestapp.model.retrofit.RetrofitService;
import com.krayapp.eltextestapp.schedulers.ISchedulers;
import com.krayapp.eltextestapp.schedulers.SimpleShedulers;


public class Fabric {
    public static RetrofitService retrofit = EltexApi.getApi();

    public static void createMainRepo() {
        getRepo = new MainRepo(retrofit);
    }
    public static IMainRepo getRepo;

    public static ISchedulers schedulers = new SimpleShedulers();

}
