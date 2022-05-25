package com.krayapp.eltextestapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.krayapp.eltextestapp.Constant;
import com.krayapp.eltextestapp.R;
import com.krayapp.eltextestapp.databinding.MainFragmentBinding;
import com.krayapp.eltextestapp.viewmodel.MainFragmentViewModel;

public class MainFragment extends Fragment {

    private MainFragmentBinding binding;
    private MainFragmentViewModel viewModel;

    private SharedPreferences sharedPref;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickListeners();
        viewModel.livedata.observe(getViewLifecycleOwner(), this::checkToken);
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
            Toast.makeText(requireContext(), "Введите логин и пароль", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.checkLoginServer(binding.usernameEdit.getText().toString(), binding.passwordEdit.getText().toString());
        }
    }

    private void displayError(Throwable throwable) {
        Toast.makeText(requireContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
    }

    private void checkToken(String token) {
        if (!token.equals("null")) {
            recordToken(token);
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, UserFragment.newInstance(token))
                    .addToBackStack("")
                    .commitAllowingStateLoss();
        } else {
            Toast.makeText(requireContext(), "Неверный логин/пароль", Toast.LENGTH_SHORT).show();
        }
    }

    private void recordToken(String token) {
        sharedPref = requireActivity().getSharedPreferences(Constant.APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constant.SHARED_PREF_TOKEN_KEY, token);
        editor.apply();
    }
}
