package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.bus_event.UpdateSchoolEvent;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivitySettingNameBinding;
import com.hula.myapplication.databinding.ActivitySettingSchoolBinding;
import com.hula.myapplication.widget.htoast.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.view.switchbuttom.SwitchButton;

public class EditSchoolActivity extends BaseActivity {
    private ActivitySettingSchoolBinding binding;
    private UserInfoData userInfoData;
    private TextView editName;
    private SwitchButton imageSwitch;
    private SchoolDao schoolDao;

    public static void actionStart(Activity activity, UserInfoData userInfoData) {
        Intent intent = new Intent(activity, EditSchoolActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingSchoolBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        userInfoData = (UserInfoData) getIntent().getSerializableExtra("userInfoData");
        editName = binding.tvName;
        imageSwitch = binding.imageSwitch;
        imageSwitch.setChecked(!userInfoData.isSchool_is_public());
        schoolDao = userInfoData.getMy_schools().size() > 0 ? userInfoData.getMy_schools().get(0) : null;
        editName.setText(userInfoData.getMy_schools().size() > 0 ? userInfoData.getMy_schools().get(0).getName() : "");
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectSchoolActivity.actionStart(EditSchoolActivity.this);
            }
        });
        binding.tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新主页
                if (schoolDao == null) {
                    ToastUtil.showToast(getResources().getString(R.string.my_school_name_error));
                    return;
                }
                userInfoData.getMy_schools().add(schoolDao);
                userInfoData.setSchool(editName.getText().toString());
                userInfoData.setSchool_is_public(!imageSwitch.isChecked());
                EventBus.getDefault().post(new UpdateUserInfoEvent(userInfoData));
                finish();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(UpdateSchoolEvent event) {
        editName.setText(event.getSchool().getName());
        schoolDao = event.getSchool();
    }
}
