package com.hula.myapplication.view.mine.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityHelpBinding;
import com.hula.myapplication.databinding.ActivityNewBinding;
import com.hula.myapplication.view.mine.what_new.NewActivity;
import com.hula.myapplication.widget.htoast.ToastUtil;

import tim.com.libnetwork.base.BaseActivity;
import tim.com.libnetwork.utils.WonderfulStringUtils;

public class HelpActivity extends BaseActivity {
    private ActivityHelpBinding binding;
    private View llEdit;
    private EditText editHelpCenter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, HelpActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        llEdit = binding.llEdit;
        editHelpCenter = binding.editHelpCenter;
        llEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editHelpCenter.setFocusable(true);
                editHelpCenter.setFocusableInTouchMode(true);
                editHelpCenter.requestFocus();
                //显示软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editHelpCenter.getText().toString().isEmpty()){
                    ToastUtil.showToast(getResources().getString(R.string.help_notice_4));
                    return;
                }
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
