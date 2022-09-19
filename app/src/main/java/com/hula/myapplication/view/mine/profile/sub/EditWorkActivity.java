package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivitySettingWorkBinding;
import com.hula.myapplication.widget.htoast.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.view.switchbuttom.SwitchButton;

public class EditWorkActivity extends BaseActivity {
    private ActivitySettingWorkBinding binding;
    private UserInfoData userInfoData;
    private EditText editName;
    private SwitchButton imageSwitch;

    public static void actionStart(Activity activity, UserInfoData userInfoData) {
        Intent intent = new Intent(activity, EditWorkActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingWorkBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        userInfoData = (UserInfoData) getIntent().getSerializableExtra("userInfoData");
        editName = binding.editName;
        imageSwitch = binding.imageSwitch;
        imageSwitch.setChecked(!userInfoData.isWorkIsPublic());
        editName.setText(userInfoData.getWork());
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更新主页
                if(editName.getText().toString().isEmpty()){
                    ToastUtil.showToast(getResources().getString(R.string.my_occupation_error));
                    return;
                }
                userInfoData.setWork(editName.getText().toString());
                userInfoData.setWorkIsPublic(!imageSwitch.isChecked());
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
}
