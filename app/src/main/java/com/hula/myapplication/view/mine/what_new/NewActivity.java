package com.hula.myapplication.view.mine.what_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.NewAdapter;
import com.hula.myapplication.dao.NewInfoDao;
import com.hula.myapplication.databinding.ActivityNewBinding;
import com.hula.myapplication.databinding.ActivityProfileBinding;
import com.hula.myapplication.view.mine.profile.ProfileActivity;
import com.hula.myapplication.widget.skeleton.LinearLayoutSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class NewActivity extends BaseActivity {
    private ActivityNewBinding binding;
    private RecyclerView recyclerView;
    private NewAdapter adapter;
    private List<NewInfoDao> data=new ArrayList<>();
    private ViewSkeleton viewSkeleton;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, NewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivityNewBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerView = binding.recycler;
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRecyclerView();
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

    private void initRecyclerView() {
        viewSkeleton = new ViewSkeleton(recyclerView,new LinearLayoutSkeletonElement(), null);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new NewAdapter(R.layout.adapter_what_new, data);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEnableLoadMore(false);
        viewSkeleton.showLoading();
    }
}
