package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.databinding.ActivityRegisterFavoriteBinding;

public class RegisterFavoriteActivity extends HBaseActivity {
    private ActivityRegisterFavoriteBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterFavoriteActivity.this,RegisterPicActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
