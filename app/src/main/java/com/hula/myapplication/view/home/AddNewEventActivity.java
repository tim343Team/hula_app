package com.hula.myapplication.view.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.dao.CityDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.ActivityAddNewEventBinding;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.view.home.vm.AddNewEventVM;
import com.hula.myapplication.widget.DelectImageView;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.adapter.AbsMultiItemViewData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewEventActivity extends HBaseActivity {
    private ActivityAddNewEventBinding binding;
    private ImageAdapter imageAdapter;
    private View footView;
    private AddNewEventVM viewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewmodel = new ViewModelProvider(this).get(AddNewEventVM.class);
        binding = ActivityAddNewEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        observer();
    }

    private void initView() {
        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.editTitle.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.editLocationName.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectCityBottomDialog dialog = new SelectCityBottomDialog();
                dialog.huCallBack1 = new HuCallBack1<CityDao>() {
                    @Override
                    public void call(CityDao cityDao) {
                        viewmodel.cityLD.setValue(cityDao);
                    }
                };
                dialog.show(getSupportFragmentManager(), "");
            }
        });
        binding.tvCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteCategoryDialog dialog = new FavoriteCategoryDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });
        binding.tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSelectDialog dialog = new TimeSelectDialog();
                dialog.show(getSupportFragmentManager(), "");
            }
        });
        binding.tvDes.addTextChangedListener(new SimTextWatcher() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                binding.tvDesLimit.setText(s.length() + "/160");
            }
        });
        imageAdapter = new ImageAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        footView = LayoutInflater.from(this).inflate(R.layout.item_image_view, binding.recyclerView, false);
        footView.setOnClickListener(v -> {
            HUtils.selectPic(AddNewEventActivity.this, 10 - imageAdapter.getData().size(), new HuCallBack1<List<String>>() {
                @Override
                public void call(List<String> strings) {
                    imageAdapter.addData(strings);
                    onImageSizeChange();
                }
            });

        });
        imageAdapter.addFooterView(footView);

        binding.recyclerView.setAdapter(imageAdapter);


        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    @Override
    public void finish() {
        MutiItemBottomDialog dialog = new MutiItemBottomDialog();
        List<AbsMultiItemViewData> list = new ArrayList<>();
        list.add(new MutiItemBottomDialog.SimTextMultiItemViewData("Save event as a draft?", Color.parseColor("#B7B7B7"), 13, false, null));
        list.add(new MutiItemBottomDialog.SimTextMultiItemViewData("Save Draft", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setDataToViewModel();
                viewmodel.save();
                AddNewEventActivity.super.finish();
            }
        }));
        list.add(new MutiItemBottomDialog.SimTextMultiItemViewData("Discard", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                viewmodel.remove();
                AddNewEventActivity.super.finish();
            }
        }));
        list.add(new MutiItemBottomDialog.SimTextMultiItemViewData("Continue Editing", Color.parseColor("#0047FF"), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        }));
        dialog.absMultiItemViewData = list;
        dialog.show(getSupportFragmentManager(), "");
    }

    private void setDataToViewModel() {
        viewmodel.name.setValue(binding.editTitle.getText().toString().trim());
        viewmodel.location.setValue(binding.editLocationName.getText().toString().trim());
        viewmodel.des.setValue(binding.tvDes.getText().toString().trim());
        viewmodel.link.setValue(binding.editLink.getText().toString().trim());
        viewmodel.price.setValue(binding.editPrice.getText().toString().trim());
        int checkedRadioButtonId = binding.groupRadio.getCheckedRadioButtonId();
        if (checkedRadioButtonId != -1) {
            viewmodel.createType.setValue(checkedRadioButtonId == binding.rbtn0.getId() ? 0 : 1);
            viewmodel.createType.setValue(binding.groupRadio.getCheckedRadioButtonId());
        }
    }

    private void submit() {
        setDataToViewModel();
        viewmodel.submit();
    }

    @SuppressLint("SetTextI18n")
    private void onImageSizeChange() {
        if (imageAdapter.getData().size() == 10) {
            imageAdapter.removeFooterView(footView);
        } else if (footView.getParent() != imageAdapter.getFooterLayout()) {
            imageAdapter.addFooterView(footView);
        }
        binding.tvImageSize.setText("Photos: " + imageAdapter.getData().size() + "/10");
        checkSubmit();
    }

    private void observer() {
        viewmodel.subCategoriesDaoMutableLD.observe(this, subCategoriesDaos -> {
            String categoreiesnames = CollectionUtils.joinToString(subCategoriesDaos, ",", SubCategoriesDao::getName);
            binding.tvCategory.setText(categoreiesnames);
            checkSubmit();
        });


        viewmodel.timeLD.observe(this, time -> {
            String dateStr = BusinessUtils.getEnTime(new Date(time[0])) + "-" + BusinessUtils.getEnTime(new Date(time[1]));
            binding.tvTime.setText(dateStr);
            checkSubmit();
        });
        viewmodel.cityLD.observe(this, cityDao -> {
            binding.tvCity.setText(cityDao.getName());
            checkSubmit();
        });
        viewmodel.pics.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (imageAdapter.getData().isEmpty()) {
                    imageAdapter.addData(strings);
                }
            }
        });
        viewmodel.name.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                if (binding.editTitle.getText().length() == 0) {
                    binding.editTitle.setText(s);
                }
            }
        });

        viewmodel.price.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                if (binding.editPrice.getText().length() == 0) {
                    binding.editPrice.setText(s);
                }
            }
        });
        viewmodel.des.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                if (binding.tvDes.getText().length() == 0) {
                    binding.tvDes.setText(s);
                }
            }
        });
        viewmodel.link.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                if (binding.editLink.getText().length() == 0) {
                    binding.editLink.setText(s);
                }
            }
        });
        viewmodel.location.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (TextUtils.isEmpty(s)) {
                    return;
                }
                if (binding.editLocationName.getText().length() == 0) {
                    binding.editLocationName.setText(s);
                }
            }
        });
        viewmodel.createType.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (binding.groupRadio.getCheckedRadioButtonId() == -1) {
                    if (integer == 0) {
                        binding.rbtn0.setChecked(true);
                    } else {
                        binding.rbtn1.setChecked(true);
                    }

                }
            }
        });

    }


    private boolean checkSubmit() {


        return false;
    }


    class ImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ImageAdapter() {
            super(R.layout.item_image_view);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {
            DelectImageView imageView = helper.getView(R.id.iv);
            imageView.setDelectOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    remove(helper.getAbsoluteAdapterPosition());
                    onImageSizeChange();
                }
            });
            imageView.setShowDelect(true);
            Glide.with(imageView)
                    .load(item)
                    .into(imageView);
        }
    }
}
