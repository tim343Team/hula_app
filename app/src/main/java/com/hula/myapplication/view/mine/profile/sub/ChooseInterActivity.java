package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hula.myapplication.adapter.CategoriesDaoAdapter;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivityChooseInterestBinding;
import com.hula.myapplication.view.login.RegisterFavoriteActivity;
import com.hula.myapplication.widget.ColorItemDecoration;
import com.hula.myapplication.widget.HuCallBack1;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class ChooseInterActivity extends BaseActivity {
    private ActivityChooseInterestBinding binding;
    private CategoriesDaoAdapter categoriesDaoAdapter;
    private UserInfoData userInfoData;

    private HuCallBack1<Object> selectCall = new HuCallBack1<Object>() {
        @Override
        public void call(Object o) {
            //选择item后回调
        }
    };

    public static void actionStart(Activity activity, UserInfoData userInfoData) {
        Intent intent = new Intent(activity, ChooseInterActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityChooseInterestBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        userInfoData = (UserInfoData) getIntent().getSerializableExtra("userInfoData");
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新userInfo
                userInfoData.setInterests(categoriesDaoAdapter.getSelect());
                EventBus.getDefault().post(new UpdateUserInfoEvent(userInfoData));
                finish();
            }
        });
        categoriesDaoAdapter = new CategoriesDaoAdapter(selectCall);
        binding.recyclerView.addItemDecoration(new ColorItemDecoration());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(categoriesDaoAdapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        request();
    }

    private void request() {
        WonderfulOkhttpUtils.postJson()
                .body("{}")
                .url(UrlFactory.getSubCategories())
                .build()
                .getCall()
                .bindLifecycle(this)
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<SubCategoriesDao>>>() {
                    @Override
                    protected void onRes(RemoteData<List<SubCategoriesDao>> data) throws Exception {
                        categoriesDaoAdapter.addSelectDao(userInfoData.getInterests());
                        categoriesDaoAdapter.setCategoriesDaoData(data.getNotNullData());
                    }
                });
    }
}
