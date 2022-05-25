package com.krayapp.eltextestapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.krayapp.eltextestapp.view.MainFragment;
import com.krayapp.eltextestapp.view.UserFragment;


public class MainActivity extends AppCompatActivity{

    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.createMainRepo();
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences(Constant.APP_PREFERENCES, Context.MODE_PRIVATE);
        NetworkListener networkListener = new NetworkListener((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE));

        if (networkListener.isOnline()){
            if (sharedPref.contains(Constant.SHARED_PREF_TOKEN_KEY)) {
                String token = sharedPref.getString(Constant.SHARED_PREF_TOKEN_KEY, "null");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, UserFragment.newInstance(token))
                        .commitAllowingStateLoss();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new MainFragment())
                        .commitAllowingStateLoss();
            }
        }else {
            Toast.makeText(this, "Подключитесь к сети", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sharedPref.edit().clear().apply();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commitAllowingStateLoss();
    }
}