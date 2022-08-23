package com.hula.myapplication.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.base.picselect.GlideImageEngine;
import com.hula.myapplication.base.picselect.LubanCompressFileEngine;
import com.hula.myapplication.databinding.ActivityRegisterPicBinding;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterPicActivity extends BaseActivity {
    private ActivityRegisterPicBinding binding;

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
                PictureSelector.create(RegisterPicActivity.this)
                        .openGallery(SelectMimeType.ofImage())
                        .setImageEngine(GlideImageEngine.engine)
                        .setCompressEngine(LubanCompressFileEngine.engine)
                        .isOriginalSkipCompress(false)
                        .forResult(new OnResultCallbackListener<LocalMedia>() {
                            @Override
                            public void onResult(ArrayList<LocalMedia> result) {

                            }

                            @Override
                            public void onCancel() {

                            }
                        });
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPicActivity.this,RegisterIntroActivity.class);
                startActivity(intent);
            }
        });
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
