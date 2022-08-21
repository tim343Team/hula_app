package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.databinding.ActivityRegisterPhoneBinding;

public class RegisterPhoneActivity extends HBaseActivity {
    ActivityRegisterPhoneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPhoneActivity.this,RegisterEmailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
