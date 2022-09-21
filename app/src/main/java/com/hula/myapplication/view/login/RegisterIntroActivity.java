package com.hula.myapplication.view.login;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityRegisterIntroBinding;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.widget.ColorItemDecoration;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class RegisterIntroActivity extends BaseActivity {
    private ActivityRegisterIntroBinding binding;
    private final List<String> anyThings = new ArrayList<String>() {
        {
            add("Ideal roadtrip is...");
            add("Dream concert lineup would be...");
            add("Three drinks that describe me are...");
            add("Best event I've attended was...");
            add("If I had three wishes I'd wish for...");
            add("My passions are...");
            add("Three things that make a friendship great are...");
        }
    };

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
        binding = ActivityRegisterIntroBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new ColorItemDecoration());
        BaseQuickAdapter<String, BaseViewHolder> adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_simple_text) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                TextView itemView = helper.getView(R.id.tv);
                itemView.setText(item);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.edit.setText(item);
                        binding.edit.setSelection(item.length());
                    }
                });
            }
        };
        View moreview = LayoutInflater.from(this).inflate(R.layout.intro_more, binding.recyclerView, false);
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.addFooterView(moreview);
        moreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeFooterView(moreview);
                adapter.setNewData(anyThings);
            }
        });
        binding.recyclerView.setAdapter(adapter);
        adapter.setNewData(anyThings.subList(0, 3));

        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (!RegisterNextPageHelp.canFinish(4)) {
            binding.tvBack.setVisibility(View.INVISIBLE);
        }
    }

    private void updateProfile() {

        if (RegisterNextPageHelp.replenishProfileOnReigster(RegisterIntroActivity.this, 5, false)) {
            if (RegisterNextPageHelp.getminPage() == 1) {
                //可以返回到填写邮箱页的话，就是注册，这个时候应该填写邀请码
                Intent intent = new Intent(RegisterIntroActivity.this,RegisterInviteCodeActivity.class);
                startActivity(intent);
                return;
            }
            if (!NotificationUtils.areNotificationsEnabled()) {
                Intent intent = new Intent(RegisterIntroActivity.this, RegisterNotificationActivity.class);
                RegisterIntroActivity.this.startActivity(intent);
                return;
            }
            if (!PermissionUtils.isGranted(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Intent intent = new Intent(RegisterIntroActivity.this, RegisterLocationActivity.class);
                RegisterIntroActivity.this.startActivity(intent);
                return;
            }
            HomeActivity.start(RegisterIntroActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        if (RegisterNextPageHelp.canFinish(4)) {
            super.onBackPressed();
        }
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
