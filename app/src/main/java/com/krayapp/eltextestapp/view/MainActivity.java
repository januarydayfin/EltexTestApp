package com.krayapp.eltextestapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.krayapp.eltextestapp.Constant;
import com.krayapp.eltextestapp.Fabric;
import com.krayapp.eltextestapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.createMainRepo();
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getSharedPreferences(Constant.APP_PREFERENCES, Context.MODE_PRIVATE);

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MainFragment())
                .commitAllowingStateLoss();
    }
}