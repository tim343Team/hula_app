package com.hula.myapplication.view.mine.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hula.myapplication.adapter.PagerAdapter;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.bus_event.RefreshUserInfo;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivityProfileBinding;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.NoScrollViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class ProfileActivity extends BaseActivity {
    private ActivityProfileBinding binding;
    TextView[] tvTabs;
    NoScrollViewPager viewpager;
    private PagerAdapter adapter;
    private PreviewFragment subFragment1;
    private EditFragment subFragment2;
    private ArrayList<String> tabs = new ArrayList<>();
    protected List<Fragment> fragments = new ArrayList<>();
    private int selectPosition;
    private TextView tvPreview;
    private TextView tvEdit;
    private TextView tvDone;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        viewpager = binding.viewpagerMine;
        tvPreview = binding.tvPreview;
        tvEdit = binding.tvEdit;
        tvDone = binding.tvDone;
        tvTabs = new TextView[]{tvPreview, tvEdit};
        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (subFragment2 == null) {
                    return;
                }
                subFragment2.updateProfile();
            }
        });
        setView();
        showTab(1);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void fillWidget() {
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(0);
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(1);
            }
        });
    }

    @Override
    protected void loadData() {
        refreshUserInfo();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(RefreshUserInfo event) {
        refreshUserInfo();
    }

    private void refreshUserInfo(){
        ServiceProfile service = HService.getService(ServiceProfile.class);
        service.refresh();
        service.addRefreshListener(this, new HuCallBack1<UserInfoData>() {
            @Override
            public void call(UserInfoData userInfoData) {
                if (subFragment2 != null) {
                    subFragment2.updateUserInfo(userInfoData);
                }
                if (subFragment1 != null) {
                    subFragment1.updateUserInfo(userInfoData);
                }
            }
        });
    }

    private void setView() {
        tabs.clear();
        fragments.clear();
        for (TextView textView : tvTabs) {
            tabs.add("");
        }
        fragments.add(subFragment1 = PreviewFragment.getInstance());
        fragments.add(subFragment2 = EditFragment.getInstance());
        if (adapter == null) {
            adapter = new PagerAdapter(getSupportFragmentManager(), fragments, tabs);
            viewpager.setAdapter(adapter);
            viewpager.setOffscreenPageLimit(fragments.size() - 1);
            viewpager.setCurrentItem(1);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    void showTab(int position) {
        selectPosition = position;
        for (int i = 0; i < tvTabs.length; i++) {
            if (i == position) {
                tvTabs[i].setSelected(true);
            } else {
                tvTabs[i].setSelected(false);
            }
        }
        if (position == 1) {
            tvDone.setVisibility(View.VISIBLE);
        } else {
            tvDone.setVisibility(View.INVISIBLE);
        }
        viewpager.setCurrentItem(position);
    }
}
