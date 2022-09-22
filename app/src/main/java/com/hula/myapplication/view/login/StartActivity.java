package com.hula.myapplication.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.databinding.ActivityStartBinding;
import com.hula.myapplication.sp.SharedPrefsHelper;

public class StartActivity extends HBaseActivity {

    public static void start(Context context){
       Intent intent = new Intent(context,StartActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       context.startActivity(intent);
       HService.getService(ServiceProfile.class).cleanUserInfo();
       SharedPrefsHelper.getInstance().saveToken("");
    }


    private ActivityStartBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvLogin.setOnClickListener(v -> {
            HService.getService(PageDataHoldService.class).add("StartActivity.LoginOrRegister",true);
            Intent intent = new Intent(this,RegisterOrLoginActivity.class);
            startActivity(intent);
        });

        binding.tvRegister.setOnClickListener(v -> {
            HService.getService(PageDataHoldService.class).add("StartActivity.LoginOrRegister",false);
            Intent intent = new Intent(this,RegisterOrLoginActivity.class);
            startActivity(intent);
        });

    }

}
