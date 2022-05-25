package com.krayapp.eltextestapp;

import com.krayapp.eltextestapp.model.IMainRepo;
import com.krayapp.eltextestapp.model.MainRepo;
import com.krayapp.eltextestapp.model.retrofit.EltexApi;
import com.krayapp.eltextestapp.model.retrofit.RetrofitService;
import com.krayapp.movieapppoplib.Schedulers.ISchedulers;
import com.krayapp.movieapppoplib.Schedulers.SimpleShedulers;

public class Fabric {
    public static RetrofitService retrofit = EltexApi.getApi();
    public static IMainRepo createMainRepo = new MainRepo(retrofit);
    public static ISchedulers schedulers = new SimpleShedulers();
}
