package com.krayapp.eltextestapp.view;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    private String token;
    private static final String ARG_KEY = "TOKEN ARG KEY";

    public static UserFragment newInstance(String token){
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(UserFragment.ARG_KEY, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null){
            token = getArguments().getString(ARG_KEY);
        }
    }
}
