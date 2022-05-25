package com.krayapp.eltextestapp.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krayapp.eltextestapp.R;
import com.krayapp.eltextestapp.databinding.PermisssionTemplateBinding;
import com.krayapp.eltextestapp.model.AboutUserEntity;

public class PermissionViewHolder extends RecyclerView.ViewHolder{

    TextView permission;
    public PermissionViewHolder(@NonNull View itemView) {
        super(itemView);
        permission = itemView.findViewById(R.id.permissionText);
    }
    public void bind(String permission){
        this.permission.setText(permission);
    }
}
