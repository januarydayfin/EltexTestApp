package com.krayapp.eltextestapp.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SimpleShedulers implements ISchedulers{
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler background() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }
}
