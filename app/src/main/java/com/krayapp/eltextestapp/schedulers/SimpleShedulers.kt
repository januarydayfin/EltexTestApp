package com.krayapp.movieapppoplib.Schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SimpleShedulers : ISchedulers {
    override fun io(): Scheduler =
        Schedulers.io()


    override fun background(): Scheduler = Schedulers.computation()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}