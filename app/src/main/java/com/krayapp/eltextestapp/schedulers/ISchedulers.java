package com.krayapp.eltextestapp.schedulers;

import io.reactivex.Scheduler;

public interface ISchedulers {
    Scheduler io();

    Scheduler background();

    Scheduler main();
}
