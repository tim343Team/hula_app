package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.databinding.ActivityRegisterEmailBinding;

public class RegisterEmailActivity extends HBaseActivity {
    ActivityRegisterEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterEmailBinding.inflate(getLayoutInflater());
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
                Intent intent = new Intent(RegisterEmailActivity.this,RegisterSchoolActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
