package com.hula.myapplication.view.home;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.base.picselect.GlideImageEngine;
import com.hula.myapplication.base.picselect.LubanCompressFileEngine;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.ActivityAddNewEventBinding;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.view.home.vm.AddNewEventVM;
import com.hula.myapplication.view.login.RegisterPicActivity;
import com.hula.myapplication.widget.DelectImageView;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.PermissonDialog;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.util.ArrayList;
import java.util.List;

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
        initView();
        observer();
    }

    private void initView() {
        binding.editTitle.addTextChangedListener(new SimTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.editLocationName.addTextChangedListener(new SimTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteCategoryDialog dialog = new FavoriteCategoryDialog();
                dialog.show(getSupportFragmentManager(),"");
            }
        });
        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelectDialog dialog = new TimeSelectDialog();
                dialog.show(getSupportFragmentManager(),"");
            }
        });
    }

    private void observer() {
        viewmodel.subCategoriesDaoMutableLD.observe(this, subCategoriesDaos -> {
            String categoreiesnames = CollectionUtils.joinToString(subCategoriesDaos, ",", SubCategoriesDao::getName);
            binding.tvCategory.setText(categoreiesnames);
            checkSubmit();
        });

    }


    private boolean checkSubmit(){


        return false;
    }
    //{
    //	"starting": "2022-09-13T13:46:30+0800",
    //	"user_id": 6597,
    //	"category": "2",
    //	"ticket_price": "552",
    //	"event_url": "de",
    //	"creating_to_find_buddy": "True",
    //	"name": "Ui",
    //	"location": "Beijing ",
    //	"ending": "2022-09-13T13:46:33+0800",
    //	"description": "明",
    //	"image_link": "https:\/\/cdn.hula-events.com\/Dudu_6597%2Fmy_events%2FC7A6A84B-6EB9-4868-8F26-DBFF17A6D8D7%2F0.jpg",
    //	"sub_category": "50"
    //}

    class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ImageAdapter() {
            super(R.layout.item_image_view);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {
            DelectImageView imageView =  helper.getView(R.id.iv);
            imageView.setOnClickListener(v -> {
                if (TextUtils.isEmpty(item)) {
                    new PermissonDialog.Builder()
                            .setPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .setTitle("“Hula” Would Like to Access Your Photos")
                            .setSubTitle("So you can add all your related photos to your event board.")
                            .request(getSupportFragmentManager(), new PermissonDialog.Builder.StandardPermissionHand() {
                                @Override
                                public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
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
