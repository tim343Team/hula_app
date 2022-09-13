package com.hula.myapplication.view.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.databinding.FragmentLikeBinding;
import com.hula.myapplication.view.home.adapter.PartyAdapter;
import com.hula.myapplication.widget.skeleton.LinearLayoutSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.util.List;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class MoreCategoryFragment extends Fragment {
    private FragmentLikeBinding binding;
    private PartyAdapter partyAdapter;
    private int offset = 0;
    private ViewSkeleton viewSkeleton;
    private String category = "";

    public static MoreCategoryFragment newInstance(String category) {

        Bundle args = new Bundle();
        args.putString("category", category);
        MoreCategoryFragment fragment = new MoreCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLikeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            category = arguments.getString("category");
        }
        binding.actionBar.setTitle(category);
        viewSkeleton = new ViewSkeleton(binding.recyclerView, new LinearLayoutSkeletonElement(), null);
        binding.actionBar.setBackClickListener(v -> getParentFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_enter, R.anim.slide_left_out)
                .remove(MoreCategoryFragment.this)
                .commit());
        partyAdapter = new PartyAdapter();
        View emptryView = LayoutInflater.from(requireContext()).inflate(R.layout.view_emptry, null, false);
        TextView tv = emptryView.findViewById(R.id.tv);
        tv.setText("You haven't saved any events yet,Go ahead and explore more events");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        partyAdapter.setEmptyView(emptryView);
        partyAdapter.setOnLoadMoreListener(this::onload, binding.recyclerView);
        binding.recyclerView.setAdapter(partyAdapter);
        onload();
    }

    private void onload() {
        if (offset == 0) {
            viewSkeleton.showLoading();
        }
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.specificEventsList())
                .addParams("events_type", category)
                .addParams("limit", "10")
                .addParams("user_id", HService.getService(ServiceProfile.class).getUserId())
                .addParams("offset", String.valueOf(offset))
                .build()
                .getCall()
                .bindLifecycle(this)
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<EventsItem>>>() {
                    @Override
                    protected void onRes(RemoteData<List<EventsItem>> data) {
                        if (offset == 0) {
                            viewSkeleton.hint();
                        }
                        offset += 10;
                        partyAdapter.addData(data.getNotNullData());
                        partyAdapter.loadMoreComplete();
                        if (data.getNotNullData().size() < 10) {
                            partyAdapter.loadMoreEnd();
                        }
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        partyAdapter.loadMoreFail();
                    }
                });
    }

}
