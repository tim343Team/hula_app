package com.hula.myapplication.view.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hula.myapplication.R;

import tim.com.libnetwork.base.BaseTransFragment;

public class MineFragment extends BaseTransFragment {
    public static final String TAG = MineFragment.class.getSimpleName();
    private TextView tvPreview;
    private TextView tvEdit;
    private View llAboutMe;
    private EditText editAboutMe;
    private TextView tvEditLength;
    private int currentPage;

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        tvPreview = rootView.findViewById(R.id.tv_preview);
        tvEdit = rootView.findViewById(R.id.tv_edit);
        llAboutMe = rootView.findViewById(R.id.ll_about_me);
        editAboutMe = rootView.findViewById(R.id.edit_about_me);
        tvEditLength = rootView.findViewById(R.id.tv_edit_length);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 0);
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 1);
            }
        });
        llAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAboutMe.setFocusable(true);
                editAboutMe.setFocusableInTouchMode(true);
                editAboutMe.requestFocus();
                //显示软键盘
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        editAboutMe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if(content.isEmpty()){
                    tvEditLength.setText(160+"");
                    return;
                }
                tvEditLength.setText((160-content.length())+"");
            }
        });
        selecte(tvPreview, 0);
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

    public void selecte(View v, int page) {
        currentPage = page;
        tvPreview.setSelected(false);
        tvEdit.setSelected(false);
        v.setSelected(true);
    }
}
