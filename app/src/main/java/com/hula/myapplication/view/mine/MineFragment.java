package com.hula.myapplication.view.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.hula.myapplication.R;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.bus_event.RefreshUserInfo;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.view.login.StartActivity;
import com.hula.myapplication.view.mine.help.HelpActivity;
import com.hula.myapplication.view.mine.preferences.PreferenceActivity;
import com.hula.myapplication.view.mine.privacy.PrivacyActivity;
import com.hula.myapplication.view.mine.profile.ProfileActivity;
import com.hula.myapplication.view.mine.what_new.NewActivity;
import com.hula.myapplication.widget.HuCallBack1;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tim.com.libnetwork.base.BaseTransFragment;

public class MineFragment extends BaseTransFragment {
    public static final String TAG = MineFragment.class.getSimpleName();
    private TextView tvName;
    private TextView tvMember;
    private ShapeableImageView avatar;
    private UserInfoData userInfoData;

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(RefreshUserInfo event) {
        refreshUserInfo();
    }

    private void refreshUserInfo() {
        ServiceProfile service = HService.getService(ServiceProfile.class);
        service.refresh();
        service.addRefreshListener(this, new HuCallBack1<UserInfoData>() {
            @Override
            public void call(UserInfoData userInfo) {
                userInfoData = userInfo;
                tvName.setText(userInfo.getDisplay_name());
            }
        });
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvName = rootView.findViewById(R.id.tv_name);
        tvMember = rootView.findViewById(R.id.tv_member);
        avatar = rootView.findViewById(R.id.avatar);
        rootView.findViewById(R.id.ll_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userInfoData == null) {
                    return;
                }
                ProfileActivity.actionStart(getmActivity());
            }
        });
        rootView.findViewById(R.id.ll_preference).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceActivity.actionStart(getmActivity());
            }
        });
        rootView.findViewById(R.id.ll_invite_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.invite_share_text));
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });
        rootView.findViewById(R.id.ll_account_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrivacyActivity.actionStart(getmActivity());
            }
        });
        rootView.findViewById(R.id.ll_what_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewActivity.actionStart(getmActivity());
            }
        });
        rootView.findViewById(R.id.ll_help_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpActivity.actionStart(getmActivity());
            }
        });
        rootView.findViewById(R.id.ll_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity.start(requireActivity());
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
        refreshUserInfo();
    }

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }


}
