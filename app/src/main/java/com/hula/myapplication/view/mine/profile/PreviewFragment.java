package com.hula.myapplication.view.mine.profile;

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
import com.hula.myapplication.adapter.CategoriesSettingAdapter;
import com.hula.myapplication.adapter.EventAttendAdapter;
import com.hula.myapplication.adapter.PreviewImagePagerAdapter;
import com.hula.myapplication.adapter.ProfileSettingAdapter;
import com.hula.myapplication.app.Injection;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.EventAttendDao;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.FragmentMinePreviewBinding;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseLazyFragment;

public class PreviewFragment extends BaseLazyFragment {
    private FragmentMinePreviewBinding binding;
    private ViewPager viewPager;
    private LinearLayout viewGroup;
    private RecyclerView recyclerInterest;
    private RecyclerView recyclerEvent;
    private PreviewImagePagerAdapter myPagerAdapter;
    private CategoriesSettingAdapter interestAdapter;
    private EventAttendAdapter eventAdapter;
    private List<SubCategoriesDao> interestDaos = new ArrayList<>();
    private List<EventAttendDao> eventDaos = new ArrayList<>();
    private UserInfoData userInfoData;

    public static PreviewFragment getInstance() {
        PreviewFragment fragment = new PreviewFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected View getLayoutView() {
        binding = FragmentMinePreviewBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        viewPager = binding.vpImage;
        viewGroup = binding.viewGroup;
        recyclerInterest = binding.recyclerInterest;
        recyclerEvent = binding.recyclerEvent;
        myPagerAdapter = new PreviewImagePagerAdapter(getContext());
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

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ServiceProfile service = HService.getService(ServiceProfile.class);
        userInfoData = service.getUserInfo();
        updateView();
    }

    private void updateView() {
        if (userInfoData == null) {
            return;
        }
        binding.tvGender.setText(userInfoData.getPronoun());
        binding.tvBirthday.setText(userInfoData.getAge() + "");
        binding.tvAddress.setText(userInfoData.getLocation());
        binding.tvUniversity.setText(userInfoData.getMy_schools().size() > 0 ? userInfoData.getMy_schools().get(0).getName() : "");
        binding.tvJob.setText(userInfoData.getWork());
        binding.tvWine.setText(userInfoData.getDrink());
        binding.tvInfo.setText(userInfoData.getAbout());
        for (int i = 0; i < userInfoData.getWish_list().size(); i++) {
            if (i == 0) {
                binding.editEvent1.setText(userInfoData.getWish_list().get(i).getWish());
            } else if (i == 1) {
                binding.editEvent2.setText(userInfoData.getWish_list().get(i).getWish());
            } else if (i == 2) {
                binding.editEvent3.setText(userInfoData.getWish_list().get(i).getWish());
            } else {
                break;
            }
        }
        interestDaos.clear();
        interestDaos.addAll(userInfoData.getInterests());
        interestAdapter.notifyDataSetChanged();
    }

    private void initDot() {
        for (int i = 0; i < myPagerAdapter.getBanners().length; i++) {
            ImageView imageView = new ImageView(getContext());
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
        recyclerInterest.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        interestAdapter = new CategoriesSettingAdapter(R.layout.adapter_setting_categorie, interestDaos, false);
        interestAdapter.bindToRecyclerView(recyclerInterest);
        interestAdapter.setEnableLoadMore(false);
    }

    private void initRecyclerEvent() {
        //TODO 测试数据
        for (int i = 0; i < 30; i++) {
            eventDaos.add(new EventAttendDao());
        }
        recyclerEvent.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        eventAdapter = new EventAttendAdapter(R.layout.adapter_event_attend, eventDaos);
        eventAdapter.bindToRecyclerView(recyclerEvent);
        eventAdapter.setEnableLoadMore(false);
    }

    private void setViewList() {

    }

    public void updateUserInfo(UserInfoData userInfoData) {
        this.userInfoData = userInfoData;
        updateView();
    }
}
