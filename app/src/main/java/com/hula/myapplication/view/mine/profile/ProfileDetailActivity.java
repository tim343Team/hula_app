package com.hula.myapplication.view.mine.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.BothLikeAdapter;
import com.hula.myapplication.adapter.EventAttendAdapter;
import com.hula.myapplication.adapter.PreviewImagePagerAdapter;
import com.hula.myapplication.adapter.ProfileSettingAdapter;
import com.hula.myapplication.dao.BothLikeDao;
import com.hula.myapplication.dao.EventAttendDao;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.databinding.ActivityProfileDetailBinding;
import com.hula.myapplication.databinding.FragmentMinePreviewBinding;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class ProfileDetailActivity extends BaseActivity {
    private ActivityProfileDetailBinding binding;
    private ViewPager viewPager;
    private LinearLayout viewGroup;
    private RecyclerView recyclerInterest;
    private RecyclerView recyclerEvent;
    private RecyclerView recyclerBothLike;
    private PreviewImagePagerAdapter myPagerAdapter;
    private ProfileSettingAdapter interestAdapter;
    private EventAttendAdapter eventAdapter;
    private BothLikeAdapter bothLikeAdapter;
    private List<ProfileTagDao> interestDaos = new ArrayList<>();
    private List<EventAttendDao> eventDaos = new ArrayList<>();
    private List<BothLikeDao> bothLikeDaos = new ArrayList<>();
    private int id;

    public static void actionStart(Activity activity, int id) {
        Intent intent = new Intent(activity, ProfileDetailActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityProfileDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        id = getIntent().getIntExtra("id",0);
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager = binding.vpImage;
        viewGroup = binding.viewGroup;
        recyclerInterest = binding.recyclerInterest;
        recyclerEvent = binding.recyclerEvent;
        recyclerBothLike = binding.recyclerBothLike;
        myPagerAdapter = new PreviewImagePagerAdapter(ProfileDetailActivity.this);
        myPagerAdapter.setOnBannerClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //设置缓存页数
        viewPager.setOffscreenPageLimit(myPagerAdapter.getBanners().length - 1);
        viewPager.setAdapter(myPagerAdapter);
        //添加页面更改监听器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position %= myPagerAdapter.getBanners().length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initDot();
        initRecyclerInterest();
        initRecyclerEvent();
        initRecyclerBothLike();
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

    private void initDot() {
        for (int i = 0; i < myPagerAdapter.getBanners().length; i++) {
            ImageView imageView = new ImageView(ProfileDetailActivity.this);
            //设置图片的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(8, 8));
            if (i == 0) {
                //第一个为默认选中状态
                imageView.setImageResource(R.drawable.selector_dot_d351a4);
            } else {
                imageView.setImageResource(R.drawable.selector_dot_white);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 8;
            params.rightMargin = 8;
            viewGroup.addView(imageView, params);
        }
    }


    private void setImageBackground(int selectItem) {
        for (int i = 0; i < myPagerAdapter.getBanners().length; i++) {
            ImageView imageView = (ImageView) viewGroup.getChildAt(i);
            imageView.setBackground(null);
            if (i == selectItem) {
                imageView.setImageResource(R.drawable.selector_dot_d351a4);
            } else {
                imageView.setImageResource(R.drawable.selector_dot_white);
            }
        }
    }


    private void initRecyclerInterest() {
        //TODO 测试数据
        for (int i = 0; i < 30; i++) {
            interestDaos.add(new ProfileTagDao());
        }
        recyclerInterest.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        interestAdapter = new ProfileSettingAdapter(R.layout.adapter_setting_profile, interestDaos, false);
        interestAdapter.bindToRecyclerView(recyclerInterest);
        interestAdapter.setEnableLoadMore(false);
    }

    private void initRecyclerEvent() {
        //TODO 测试数据
        for (int i = 0; i < 30; i++) {
            eventDaos.add(new EventAttendDao());
        }
        recyclerEvent.setLayoutManager(new LinearLayoutManager(ProfileDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        eventAdapter = new EventAttendAdapter(R.layout.adapter_event_attend, eventDaos);
        eventAdapter.bindToRecyclerView(recyclerEvent);
        eventAdapter.setEnableLoadMore(false);
    }

    private void initRecyclerBothLike() {
        //TODO 测试数据
        for (int i = 0; i < 3; i++) {
            bothLikeDaos.add(new BothLikeDao());
        }
        recyclerBothLike.setLayoutManager(new LinearLayoutManager(ProfileDetailActivity.this, LinearLayoutManager.VERTICAL, false));
        bothLikeAdapter = new BothLikeAdapter(R.layout.adapter_both_like, bothLikeDaos);
        bothLikeAdapter.bindToRecyclerView(recyclerBothLike);
        bothLikeAdapter.setEnableLoadMore(false);
    }
}
