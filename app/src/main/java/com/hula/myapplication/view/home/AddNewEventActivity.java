package com.hula.myapplication.view.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.base.picselect.GlideImageEngine;
import com.hula.myapplication.base.picselect.LubanCompressFileEngine;
import com.hula.myapplication.databinding.ActivityAddNewEventBinding;
import com.hula.myapplication.view.home.vm.AddNewEventVM;
import com.hula.myapplication.view.login.RegisterPicActivity;
import com.hula.myapplication.widget.DelectImageView;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;

import tim.com.libnetwork.base.BaseActivity;

public class AddNewEventActivity extends HBaseActivity {
    private ActivityAddNewEventBinding binding;
    private ImageAdapter imageAdapter;
    private AddNewEventVM viewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(AddNewEventVM.class);
        binding = ActivityAddNewEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        imageAdapter = new ImageAdapter();
        imageAdapter.addData("");
        imageAdapter.addData("");
        imageAdapter.addData("");

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerView.setAdapter(imageAdapter);
    }

    class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ImageAdapter() {
            super(R.layout.item_image_view);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {
            DelectImageView imageView = (DelectImageView) helper.itemView;
            imageView.setOnClickListener(v -> {
                if (TextUtils.isEmpty(item)) {
                    PictureSelector.create(AddNewEventActivity.this)
                            .openGallery(SelectMimeType.ofImage())
                            .setImageEngine(GlideImageEngine.engine)
                            .setCompressEngine(LubanCompressFileEngine.engine)
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
            imageView.setDelectOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewmodel.delectPic(item);
                }
            });
            if (TextUtils.isEmpty(item)) {
                imageView.setImageResource(R.mipmap.icon_add_image);
                imageView.setShowDelect(false);
            } else {
                imageView.setShowDelect(true);
                Glide.with(imageView)
                        .load(item)
                        .into(imageView);
            }
        }
    }
}
