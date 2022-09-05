package com.hula.myapplication.view.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hula.myapplication.R;
import com.hula.myapplication.view.mine.help.HelpActivity;
import com.hula.myapplication.view.mine.preferences.PreferenceActivity;
import com.hula.myapplication.view.mine.privacy.PrivacyActivity;
import com.hula.myapplication.view.mine.profile.ProfileActivity;
import com.hula.myapplication.view.mine.what_new.NewActivity;

import tim.com.libnetwork.base.BaseTransFragment;

public class MineFragment extends BaseTransFragment {
    public static final String TAG = MineFragment.class.getSimpleName();

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

    @Override
    protected void initViews(Bundle savedInstanceState) {
        rootView.findViewById(R.id.ll_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                intent.putExtra(Intent.EXTRA_TEXT,getResources().getString(R.string.invite_share_text));
                startActivity(Intent.createChooser(intent,"Share"));
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
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }


}
