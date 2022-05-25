package com.krayapp.eltextestapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.krayapp.eltextestapp.Fabric;
import com.krayapp.eltextestapp.model.IMainRepo;
import com.krayapp.movieapppoplib.Schedulers.ISchedulers;

import java.util.Base64;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivityViewModel extends ViewModel {

    private IMainRepo repo = Fabric.createMainRepo;
    private ISchedulers schedulers = Fabric.schedulers;

    private MutableLiveData<String> _livedata = new MutableLiveData();
    public LiveData<String> livedata = _livedata;

    private CompositeDisposable disposables = new CompositeDisposable();


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    public void checkLoginServer(String username, String password) {
        String encoded = Base64.getEncoder().encodeToString("android-client:password".getBytes());
        disposables.add(
                repo
                        .getToken("Basic " + encoded, getBody(username, password))
                        .observeOn(schedulers.main())
                        .subscribeOn(schedulers.io())
                        .subscribe(token -> _livedata.postValue(token.getAccess_token()), throwable -> _livedata.postValue("null"))
        );

    }

    private RequestBody getBody(String username, String password) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "password")
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();
        return requestBody;
    }
}
