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
import com.hula.myapplication.databinding.ActivityProfileBadgeBinding;
import com.hula.myapplication.widget.htoast.ToastUtil;

import tim.com.libnetwork.base.BaseActivity;

public class ProfileBadgeActivity extends BaseActivity {
    private ActivityProfileBadgeBinding binding;
    private EditText editProfile;
    private TextView tvEditLength;
    private TextView tvSave;
    private TextView tvDelete;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ProfileBadgeActivity.class);
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
        editProfile = binding.editProfile;
        tvEditLength = binding.tvEditLength;
        tvSave = binding.tvSave;
        tvDelete = binding.tvDelete;
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
                //TODO 删除tag
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 添加tag
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
