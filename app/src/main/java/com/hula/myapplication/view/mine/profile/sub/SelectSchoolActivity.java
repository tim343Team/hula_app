package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hula.myapplication.adapter.SchoolDaoAdapter;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.databinding.ActivitySelectSchoolBinding;
import com.hula.myapplication.databinding.ActivitySettingWorkBinding;
import com.hula.myapplication.widget.ColorItemDecoration;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class SelectSchoolActivity extends BaseActivity {
    private ActivitySelectSchoolBinding binding;
    private RecyclerView recyclerView;
    private SchoolDaoAdapter adapter;
    private List<SchoolDao> data=new ArrayList<>();

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, SelectSchoolActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySelectSchoolBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerView=binding.recyclerView;
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //TODO 测试数据
        for (int i=0;i<6;i++){
            data.add(new SchoolDao());
        }
        adapter=new SchoolDaoAdapter(data);
        recyclerView.addItemDecoration(new ColorItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, binding.recyclerView);
        adapter.setEnableLoadMore(false);
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

    private void refresh() {
        adapter.setEnableLoadMore(true);
        adapter.loadMoreEnd(false);
//        pageNo = 1;
//        presenter.getDogList(new MailRequest(priceSort, nftCatagoryId), pageNo);
    }

    private void loadMore() {
//        refreshLayout.setEnabled(false);
//        pageNo = pageNo + 1;
    }

    private void loadData(List<SchoolDao> data){
        adapter.loadMoreComplete();
//        if (refreshLayout == null) {
//            return;
//        }
//        refreshLayout.setEnabled(true);
//        refreshLayout.setRefreshing(false);
//        if (data == null || data.size() == 0) {
//            if (pageNo == 1) {
//                this.data.clear();
//                adapter.notifyDataSetChanged();
//            }
//            return;
//        }
//        if (pageNo == 1) {
//            this.data.clear();
//            this.data.addAll(data);
//        } else {
//            this.data.addAll(data);
//        }
//        if (data.size() < 20) {
//            adapter.loadMoreEnd();
//        }
        adapter.notifyDataSetChanged();
    }
}
