package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.base.picselect.GlideImageEngine;
import com.hula.myapplication.base.picselect.LubanCompressFileEngine;
import com.hula.myapplication.databinding.ActivityRegisterPicBinding;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.PermissonDialog;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterPicActivity extends BaseActivity {
    private ActivityRegisterPicBinding binding;
    private final List<String> subPic = Arrays.asList("", "", "");
    private String headerPic = "";

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityRegisterPicBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HUtils.selectPic(RegisterPicActivity.this, 1, new HuCallBack1<List<String>>() {
                    @Override
                    public void call(List<String> data) {
                        headerPic = data.get(0);
                        Glide.with(binding.ivHeader)
                                .load(data.get(0))
                                .into(binding.ivHeader);
                        checkSubmit();

                    }
                });
            }
        });
        binding.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HUtils.selectPic(RegisterPicActivity.this, 1, new HuCallBack1<List<String>>() {
                    @Override
                    public void call(List<String> data) {
                        subPic.set(0, data.get(0));
                        Glide.with(binding.iv1)
                                .load(data.get(0))
                                .into(binding.iv1);
                    }
                });
            }
        });
        binding.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HUtils.selectPic(RegisterPicActivity.this, 1, new HuCallBack1<List<String>>() {
                    @Override
                    public void call(List<String> data) {
                        subPic.set(1, data.get(0));
                        Glide.with(binding.iv2)
                                .load(data.get(0))
                                .into(binding.iv2);
                        checkSubmit();
                    }
                });
            }
        });
        binding.iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HUtils.selectPic(RegisterPicActivity.this, 1, new HuCallBack1<List<String>>() {
                    @Override
                    public void call(List<String> data) {
                        subPic.set(2, data.get(0));
                        Glide.with(binding.iv3)
                                .load(data.get(0))
                                .into(binding.iv3);
                        checkSubmit();
                    }
                });
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Object> list = new ArrayList<>();
                list.add(headerPic);
                list.add(subPic);
                HService.getService(PageDataHoldService.class).add("RegisterPicActivity",list);
                Intent intent = new Intent(RegisterPicActivity.this, RegisterIntroActivity.class);
                startActivity(intent);
            }
        });
        checkSubmit();
    }

    private void checkSubmit() {
        boolean enable = !headerPic.isEmpty();
        if (enable) {
            for (int i = 0; i < subPic.size(); i++) {
                if (subPic.get(i).isEmpty()) {
                    enable = false;
                    break;
                }
            }
        }
        binding.tvConfirm.setEnabled(enable);
    }


    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }
}
