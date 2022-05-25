package com.krayapp.eltextestapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.krayapp.eltextestapp.Fabric;
import com.krayapp.eltextestapp.model.AboutUserEntity;
import com.krayapp.eltextestapp.model.IMainRepo;
import com.krayapp.eltextestapp.schedulers.ISchedulers;


import io.reactivex.disposables.CompositeDisposable;

public class UserFragmentViewModel extends ViewModel {

    private IMainRepo repo = Fabric.getRepo;
    private ISchedulers schedulers = Fabric.schedulers;

    private MutableLiveData<AboutUserEntity> _livedata = new MutableLiveData();
    public LiveData<AboutUserEntity> livedata = _livedata;


    private MutableLiveData<Throwable> _errorLivedata = new MutableLiveData();
    public LiveData<Throwable> errorLiveData = _errorLivedata;

    private CompositeDisposable disposables = new CompositeDisposable();


    public void getInfoFromServer(String token) {
        disposables.add(
                repo
                        .getUserInfo("Bearer " + token)
                        .observeOn(schedulers.main())
                        .subscribeOn(schedulers.io())
                        .subscribe(_livedata::postValue, _errorLivedata::postValue)
        );
    }
}
