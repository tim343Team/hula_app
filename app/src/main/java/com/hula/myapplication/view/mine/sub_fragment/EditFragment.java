package com.hula.myapplication.view.mine.sub_fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityRegisterIntroBinding;
import com.hula.myapplication.databinding.FragmentMineEditBinding;

import tim.com.libnetwork.base.BaseLazyFragment;

public class EditFragment extends BaseLazyFragment {
    private FragmentMineEditBinding binding;
    private View llAboutMe;
    private EditText editAboutMe;
    private TextView tvEditLength;

    public static EditFragment getInstance() {
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected View getLayoutView() {
        binding = FragmentMineEditBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        llAboutMe = binding.llAboutMe;
        editAboutMe = binding.editAboutMe;
        tvEditLength = binding.tvEditLength;
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
        binding.editAboutMe.addTextChangedListener(new TextWatcher() {
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
                    tvEditLength.setText(160 + "");
                    return;
                }
                tvEditLength.setText((160 - content.length()) + "");
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
