package com.hula.myapplication.view.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.databinding.FragmentSearchBinding;
import com.hula.myapplication.view.home.adapter.PartyAdapter;
import com.hula.myapplication.view.search.dialog.SearchTopDialog;
import com.hula.myapplication.widget.skeleton.LinearLayoutSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.util.List;

import tim.com.libnetwork.base.BaseTransFragment;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class SearchFragment extends BaseTransFragment {
    public static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding binding;
    private PartyAdapter adapter;
    private ViewSkeleton viewSkeleton;
    private int offset = 0;
    private int category = 0;
    private int date = 0;
    private int neighborhood = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected View getLayoutView() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding = FragmentSearchBinding.bind(rootView);
        binding.tvSearch.setOnClickListener(v -> {
            SearchTopDialog searchTopDialog = new SearchTopDialog();
            searchTopDialog.editCall = (s, booleanHuCallBack1) -> {
                //request success
                booleanHuCallBack1.call(true);
            };
            searchTopDialog.show(getChildFragmentManager(), "searchTopDialog");
        });
        viewSkeleton = new ViewSkeleton(binding.recyclerView, new LinearLayoutSkeletonElement(), null);
        adapter = new PartyAdapter();
        View emptryView = LayoutInflater.from(requireContext()).inflate(R.layout.view_emptry, null, false);
        TextView tv = emptryView.findViewById(R.id.tv);
        tv.setText("We are working to source you the best events,check back soon!");
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setEmptyView(emptryView);
        adapter.setOnLoadMoreListener(this::onload, binding.recyclerView);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        onload();
    }

    private void onload() {
        if (offset == 0) {
            viewSkeleton.showLoading();
        }
        WonderfulOkhttpUtils.get()
                .url(UrlFactory.eventSearch())
                .addParams("limit", String.valueOf(10))
                .addParams("offset", String.valueOf(offset))
                .addParams("category", String.valueOf(category))
                .addParams("date", String.valueOf(date))
                .addParams("user_id", HService.getService(ServiceProfile.class).getUserId())
                .addParams("neighborhood", String.valueOf(neighborhood))
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
                        adapter.addData(data.getNotNullData());
                        adapter.loadMoreComplete();
                        if (data.getNotNullData().size() < 10) {
                            adapter.loadMoreEnd();
                        }
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        adapter.loadMoreFail();
                    }
                });
    }

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }


    @Override
    protected String getmTag() {
        return null;
    }
}
