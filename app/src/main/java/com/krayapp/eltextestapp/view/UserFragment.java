package com.krayapp.eltextestapp.view;

import android.icu.util.BuddhistCalendar;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.krayapp.eltextestapp.databinding.UserFragmentBinding;
import com.krayapp.eltextestapp.model.AboutUserEntity;
import com.krayapp.eltextestapp.view.adapter.PermissionAdapter;
import com.krayapp.eltextestapp.viewmodel.UserFragmentViewModel;

public class UserFragment extends Fragment {
    private String token;
    private static final String ARG_KEY = "TOKEN ARG KEY";

    private UserFragmentBinding binding;
    private UserFragmentViewModel viewModel;

    private PermissionAdapter adapter = new PermissionAdapter();

    public static UserFragment newInstance(String token) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(UserFragment.ARG_KEY, token);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString(ARG_KEY);
        }
        viewModel = new ViewModelProvider(requireActivity()).get(UserFragmentViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.permRecycler.setAdapter(adapter);
        viewModel.livedata.observe(getViewLifecycleOwner(), this::renderData);
        viewModel.errorLiveData.observe(getViewLifecycleOwner(), this::displayError);
        viewModel.getInfoFromServer(token);
    }

    private void renderData(AboutUserEntity user) {
        binding.roleId.setText(user.getRoleId());
        binding.username.setText(user.getUsername());
        binding.email.setText(user.getEmail());
        adapter.submitList(user.getPermissions());
    }

    private void displayError(Throwable throwable){
        Toast.makeText(requireContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
    }
}
