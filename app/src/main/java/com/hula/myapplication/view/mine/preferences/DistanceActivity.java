package com.hula.myapplication.view.mine.preferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hula.myapplication.R;
import com.hula.myapplication.databinding.ActivityAgeBinding;
import com.hula.myapplication.databinding.ActivityDistanceBinding;
import com.hula.myapplication.widget.SeekBarView;

import tim.com.libnetwork.base.BaseActivity;

public class DistanceActivity extends BaseActivity {
    private ActivityDistanceBinding binding;
    private SeekBarView seekBarView;
    private TextView tvDistanceRange;
    private int distance = 100;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, DistanceActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityDistanceBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        seekBarView = binding.seekBar;
        tvDistanceRange = binding.tvDistanceRange;
        tvDistanceRange.setText(getString(R.string.distance_range, distance + ""));
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
                distance= (int) Math.ceil(progressHigh);
                tvDistanceRange.setText(getString(R.string.distance_range, distance + ""));
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
