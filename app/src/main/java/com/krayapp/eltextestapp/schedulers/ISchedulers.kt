package com.krayapp.movieapppoplib.Schedulers

import io.reactivex.Scheduler

interface ISchedulers {
    fun io(): Scheduler
    fun background(): Scheduler
    fun main():Scheduler
}