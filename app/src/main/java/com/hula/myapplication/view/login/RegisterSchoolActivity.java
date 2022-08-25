package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.databinding.ActivityRegisterSchoolBinding;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.BottomSelectDialog;

import java.util.ArrayList;
import java.util.List;

public class RegisterSchoolActivity extends HBaseActivity {
    ActivityRegisterSchoolBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterSchoolBinding.inflate(getLayoutInflater());
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
                Intent intent = new Intent(RegisterSchoolActivity.this,RegisterFavoriteActivity.class);
                startActivity(intent);
            }
        });

        binding.tvSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Test> list = new ArrayList<>();
                list.add(new Test());
                list.add(new Test());
                list.add(new Test());

                BottomSelectDialog<Test> dialog = new BottomSelectDialog<>();
                dialog.callBack = new HuCallBack1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                };
                dialog.transfor = new HuCallBack1.HuCallBackR<Test, String>() {
                    @Override
                    public String call(Test test) {
                        return test.toString();
                    }
                };
                dialog.title = "Select School";
                dialog.data = list;
                dialog.show(getSupportFragmentManager(),"");
            }
        });

    }
    static class Test{}


    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
