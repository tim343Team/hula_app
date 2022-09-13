package com.hula.myapplication.view.invite.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.BuddyAdapter;
import com.hula.myapplication.dao.BuddyInvitesDao;
import com.hula.myapplication.dao.SubBuddyInvitesDao;
import com.hula.myapplication.databinding.FragmentBuddyBinding;
import com.hula.myapplication.widget.skeleton.ErrSkeletonElement;
import com.hula.myapplication.widget.skeleton.LinearLayoutSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseLazyFragment;

public class BuddyFragment extends BaseLazyFragment {
    private FragmentBuddyBinding binding;
    private BuddyAdapter adapterData;
    private RecyclerView recyclerView;
    private ViewSkeleton viewSkeleton;
    private List<BuddyInvitesDao> data = new ArrayList<>();

    public static BuddyFragment getInstance() {
        BuddyFragment fragment = new BuddyFragment();
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
        binding = FragmentBuddyBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        recyclerView = binding.recyclerView;
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

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }

    private void initRecyclerView() {
        //TODO 测试数据
        for (int i = 0; i < 3; i++) {
            BuddyInvitesDao dao = new BuddyInvitesDao();
            dao.setType(i + 1);
            List<SubBuddyInvitesDao> subData = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                subData.add(new SubBuddyInvitesDao());
            }
            dao.setData(subData);
            data.add(dao);
        }
        adapterData = new BuddyAdapter(data);
        viewSkeleton = new ViewSkeleton(rootView.findViewById(R.id.holdView), new LinearLayoutSkeletonElement(), ErrSkeletonElement.getInstance(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadEvents();
            }
        }));
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(adapterData);
        adapterData.setEnableLoadMore(false);
    }
}
