package com.hula.myapplication.view.mine.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.app.Injection;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.PreferenceDao;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.ActivityPreferenceBinding;
import com.hula.myapplication.view.mine.profile.ProfilePresenter;

import java.util.List;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class PreferenceActivity extends BaseActivity implements PreferenceContract.PreferenceView {
    private ActivityPreferenceBinding binding;
    private PreferenceContract.PreferencePresenter presenter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, PreferenceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityPreferenceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new PreferencePresenter(Injection.provideTasksRepository(this), this);//初始化presenter
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgeActivity.actionStart(PreferenceActivity.this);
            }
        });
        binding.llPronouns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PronounsActivity.actionStart(PreferenceActivity.this);
            }
        });
        binding.llDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DistanceActivity.actionStart(PreferenceActivity.this);
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
        ServiceProfile service = HService.getService(ServiceProfile.class);
        String userId = service.getUserId();
        presenter.getUserPreference(userId);
    }

    @Override
    public void getFail(Integer code, String toastMessage) {

    }

    @Override
    public void getUserPreferenceSuccess(PreferenceDao preferenceDao) {
        binding.tvDistance.setText(preferenceDao.getDistance()+"");
        binding.tvAge.setText(preferenceDao.getAge_range_start()+" - "+preferenceDao.getAge_range_end());
        binding.tvPronouns.setText("");
    }

    @Override
    public void setPresenter(PreferenceContract.PreferencePresenter presenter) {
        this.presenter = presenter;
    }
}
