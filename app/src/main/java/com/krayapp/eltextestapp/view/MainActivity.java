package com.krayapp.eltextestapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.krayapp.eltextestapp.Constant;
import com.krayapp.eltextestapp.R;
import com.krayapp.eltextestapp.databinding.ActivityMainBinding;
import com.krayapp.eltextestapp.viewmodel.MainActivityViewModel;


public class MainActivity extends AppCompatActivity {
    private final String SHARED_PREF_TOKEN_KEY = "SHARED_PREF_TOKEN";

    private SharedPreferences sharedPref;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPref = getSharedPreferences(Constant.APP_PREFERENCES, Context.MODE_PRIVATE);
        clickListeners();

        if (sharedPref.contains(SHARED_PREF_TOKEN_KEY)){
            String token = sharedPref.getString(SHARED_PREF_TOKEN_KEY, "null");
            checkToken(token);
        }else{
            viewModel.livedata.observe(this, this::checkToken);
        }
    }

    private void clickListeners() {
        binding.usernameEdit.setOnClickListener(v ->
                binding.passwordEdit.setVisibility(View.VISIBLE));
        binding.passwordEdit.setOnClickListener(v ->
                binding.loginButton.setVisibility(View.VISIBLE));
        binding.loginButton.setOnClickListener(v -> checkLogin());
    }

    private void checkLogin() {
        if (binding.usernameEdit.getText().toString().equals("") && binding.passwordEdit.getText().toString().equals("")) {
            Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.checkLoginServer(binding.usernameEdit.getText().toString(), binding.passwordEdit.getText().toString());
        }
    }

    private void checkToken(String token) {
        if (!token.equals("null")) {
            recordToken(token);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, UserFragment.newInstance(token))
                    .addToBackStack("")
                    .commitAllowingStateLoss();
        } else {
            Toast.makeText(this, "Неверный логин/пароль", Toast.LENGTH_SHORT).show();
        }
    }


    private void recordToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SHARED_PREF_TOKEN_KEY, token);
        editor.apply();
    }
}