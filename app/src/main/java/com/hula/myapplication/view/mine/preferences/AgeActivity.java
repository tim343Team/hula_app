package com.hula.myapplication.view.mine.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityAgeBinding;
import com.hula.myapplication.databinding.ActivityPreferenceBinding;
import com.hula.myapplication.widget.SeekBarView;

import tim.com.libnetwork.base.BaseActivity;

public class AgeActivity extends BaseActivity {
    private ActivityAgeBinding binding;
    private SeekBarView seekBarView;
    private TextView tvAgeRange;
    private double minAge = 16;
    private double maxAge = 60;
    private int lowAge = 16;
    private int heightAge = 60;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AgeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityAgeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        seekBarView = binding.seekBar;
        tvAgeRange = binding.tvAgeRange;
        tvAgeRange.setText(getString(R.string.age_range, minAge + "", maxAge + ""));
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        seekBarView.setOnSeekBarChangeListener(new SeekBarView.OnSeekBarChangeListener() {
            @Override
            public void onProgressBefore() {

            }

            @Override
            public void onProgressChanged(SeekBarView seekBar, double progressLow, double progressHigh) {
                lowAge = (int) (minAge + Math.ceil((maxAge - minAge) / 100 * progressLow));
                heightAge =  (int) (minAge +Math.ceil((maxAge - minAge) / 100 * progressHigh));
                tvAgeRange.setText(getString(R.string.age_range, lowAge + "", heightAge + ""));
            }

            @Override
            public void onProgressAfter() {

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
