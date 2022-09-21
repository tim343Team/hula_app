package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.app.Injection;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivityProfileBadgeBinding;
import com.hula.myapplication.request.CreateProfileParameter;
import com.hula.myapplication.widget.htoast.ToastUtil;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class ProfileBadgeActivity extends BaseActivity implements ProfileSettingContract.ProfileSettingView{
    private ActivityProfileBadgeBinding binding;
    private EditText editProfile;
    private TextView tvEditLength;
    private TextView tvSave;
    private TextView tvDelete;
    private ProfileSettingContract.ProfileSettingPresenter presenter;
    private UserInfoData userInfoData;
    private int tagId;

    public static void actionStart(Activity activity, UserInfoData userInfoData) {
        Intent intent = new Intent(activity, ProfileBadgeActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        activity.startActivity(intent);
    }

    public static void actionStart(Activity activity,UserInfoData userInfoData, int tagId,String name) {
        Intent intent = new Intent(activity, ProfileBadgeActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        intent.putExtra("tagId", tagId);
        intent.putExtra("name", name);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityProfileBadgeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new ProfileSettingPresenter(Injection.provideTasksRepository(getApplicationContext()), this);//初始化presenter
        userInfoData = (UserInfoData) getIntent().getSerializableExtra("userInfoData");
        tagId = getIntent().getIntExtra("tagId",0);
        editProfile = binding.editProfile;
        tvEditLength = binding.tvEditLength;
        tvSave = binding.tvSave;
        tvDelete = binding.tvDelete;
        if(tagId==0){
            //添加
            tvSave.setVisibility(View.VISIBLE);
            tvDelete.setVisibility(View.GONE);
        }else {
            //删除
            tvSave.setVisibility(View.GONE);
            tvDelete.setVisibility(View.VISIBLE);
            editProfile.setEnabled(false);
            editProfile.setText(getIntent().getStringExtra("name"));
        }
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile.setFocusable(true);
                editProfile.setFocusableInTouchMode(true);
                editProfile.requestFocus();
                //显示软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        binding.editProfile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (content.isEmpty()) {
                    tvEditLength.setText(20 + "");
                    tvSave.setBackgroundResource(R.drawable.shape_radius100_c4c4c4);
                    tvSave.setEnabled(false);
                    return;
                }else {
                    tvSave.setBackgroundResource(R.drawable.shape_radius100_8e73d3);
                    tvSave.setEnabled(true);
                }
                tvEditLength.setText((20 - content.length()) + "");
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除tag
                if(tagId==0){
                    return;
                }
                ServiceProfile service = HService.getService(ServiceProfile.class);
                ToastUtil.showLoading("");
                presenter.deleteProfileTag(new CreateProfileParameter(service.getUserId(),tagId));
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加tag
                ServiceProfile service = HService.getService(ServiceProfile.class);
                ToastUtil.showLoading("");
                presenter.createProfileTag(new CreateProfileParameter(service.getUserId(),editProfile.getText().toString()));
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

    @Override
    public void getFail(Integer code, String toastMessage) {
        ToastUtil.hideLoading();
    }

    @Override
    public void getProfileSuccess(List<ProfileTagDao> daos) {
        //更新上级页面
        ToastUtil.hideLoading();
        userInfoData.setMy_profile_tags(daos);
        EventBus.getDefault().post(new UpdateUserInfoEvent(userInfoData));
        finish();
    }

    @Override
    public void getSchoolSuccess(List<SchoolDao> daos) {

    }

    @Override
    public void addSchoolSuccess(SchoolDao daos) {

    }


    @Override
    public void setPresenter(ProfileSettingContract.ProfileSettingPresenter presenter) {
        this.presenter=presenter;
    }
}
