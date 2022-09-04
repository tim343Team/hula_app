package com.hula.myapplication.view.mine.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hula.myapplication.databinding.ActivityAgeBinding;
import com.hula.myapplication.databinding.ActivityPronounsBinding;

import tim.com.libnetwork.base.BaseActivity;

public class PronounsActivity extends BaseActivity {
    private ActivityPronounsBinding binding;
    private TextView tvHe;
    private TextView tvShe;
    private TextView tvThey;
    private TextView tvAll;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PronounsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityPronounsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvHe = binding.tvHe;
        tvShe = binding.tvShe;
        tvThey = binding.tvThey;
        tvAll = binding.tvAll;
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvHe.setSelected(!tvHe.isSelected());
                if(tvHe.isSelected()){
                    tvAll.setSelected(false);
                }
            }
        });
        binding.tvShe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvShe.setSelected(!tvShe.isSelected());
                if(tvShe.isSelected()){
                    tvAll.setSelected(false);
                }
            }
        });
        binding.tvThey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvThey.setSelected(!tvThey.isSelected());
                if(tvThey.isSelected()){
                    tvAll.setSelected(false);
                }
            }
        });
        binding.tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAll.setSelected(!tvAll.isSelected());
                if (tvAll.isSelected()) {
                    tvThey.setSelected(false);
                    tvShe.setSelected(false);
                    tvHe.setSelected(false);
                }
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
