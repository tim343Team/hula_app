package com.hula.myapplication.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.home.Anthing;
import com.hula.myapplication.dao.home.DataItemDao;
import com.hula.myapplication.view.home.adapter.DiffMutiAdapter;
import com.hula.myapplication.view.home.adapter.HomeViewDataAdapterData;
import com.hula.myapplication.view.home.vm.HomeVm;
import com.hula.myapplication.view.login.RegisterActivity;
import com.hula.myapplication.widget.HuLaActionBar;
import com.hula.myapplication.widget.skeleton.ErrSkeletonElement;
import com.hula.myapplication.widget.skeleton.ListLoadingSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.util.List;

import tim.com.libnetwork.base.BaseTransFragment;

public class HomeFragment extends BaseTransFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private DiffMutiAdapter homeAdapter;
    private HomeVm homeVm;
    private HomeViewDataAdapterData adapterData;
    private ViewSkeleton viewSkeleton;

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void init() {
        homeVm = new ViewModelProvider(this).get(HomeVm.class);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        HuLaActionBar actionBar = rootView.findViewById(R.id.action_bar);
        actionBar.setMenuClickListener(new HuLaActionBar.OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                Intent intent = new Intent(requireActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        homeAdapter = new DiffMutiAdapter();
        adapterData = new HomeViewDataAdapterData(homeAdapter);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        viewSkeleton = new ViewSkeleton(rootView.findViewById(R.id.holdView), new ListLoadingSkeletonElement(), ErrSkeletonElement.getInstance(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadEvents();
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(homeAdapter);
        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadEvents();
            }
        }, recyclerView);

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        homeVm.page = 0;
        viewSkeleton.showLoading();
        loadEvents();
        homeVm.allEventLD.observe(this, new Observer<List<Anthing>>() {
            @Override
            public void onChanged(List<Anthing> dataItemDaos) {
                adapterData.setDataItemDaos(dataItemDaos);
                viewSkeleton.hint();
            }
        });
    }

    private void loadEvents() {
        homeVm.loadEvents().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                homeAdapter.loadMoreEnd();
                homeAdapter.setEnableLoadMore(aBoolean);
            }
        });
    }

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }
}
