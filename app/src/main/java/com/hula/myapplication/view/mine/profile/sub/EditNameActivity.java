package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hula.myapplication.R;
import com.hula.myapplication.bus_event.UpdateUserInfoEvent;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivityNewBinding;
import com.hula.myapplication.databinding.ActivitySettingNameBinding;
import com.hula.myapplication.view.mine.what_new.NewActivity;
import com.hula.myapplication.widget.htoast.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import tim.com.libnetwork.base.BaseActivity;

public class EditNameActivity extends BaseActivity {
    private ActivitySettingNameBinding binding;
    private UserInfoData userInfoData;
    private EditText editName;

    public static void actionStart(Activity activity, UserInfoData userInfoData) {
        Intent intent = new Intent(activity, EditNameActivity.class);
        intent.putExtra("userInfoData", userInfoData);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySettingNameBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        userInfoData = (UserInfoData) getIntent().getSerializableExtra("userInfoData");
        editName = binding.editName;
        editName.setText(userInfoData.getDisplayName());
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
                    ToastUtil.showToast(getResources().getString(R.string.display_name_error));
                    return;
                }
                userInfoData.setDisplayName(editName.getText().toString());
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
