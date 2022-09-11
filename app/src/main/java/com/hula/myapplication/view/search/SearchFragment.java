package com.hula.myapplication.view.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SearchSectionsDao;
import com.hula.myapplication.dao.home.EventsItem;
import com.hula.myapplication.databinding.FragmentSearchBinding;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.view.home.adapter.PartyAdapter;
import com.hula.myapplication.view.search.dialog.SearchTopDialog;
import com.hula.myapplication.view.search.vm.SearchViewModel;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.skeleton.LinearLayoutSkeletonElement;
import com.hula.myapplication.widget.skeleton.ViewSkeleton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import tim.com.libnetwork.base.BaseTransFragment;
import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;
import tim.com.libnetwork.network.okhttp.get.GetBuilder;

public class SearchFragment extends BaseTransFragment {
    public static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding binding;
    private PartyAdapter adapter;
    private ViewSkeleton viewSkeleton;
    private int offset = 0;
    private int category = 0;
    private int date = 0;
    private int neighborhood = 0;
    private SearchViewModel viewModel;

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
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
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
            searchTopDialog.huCallBack = s -> {
                viewModel.editSearch = s;
                if (!viewModel.editSearch.isEmpty()) {
                    viewModel.selectIndexs.clear();
                }
                offset = 0;
                onload();
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
        GetBuilder builder = WonderfulOkhttpUtils.get()
                .url(UrlFactory.eventSearch())
                .addParams("limit", String.valueOf(10))
                .addParams("offset", String.valueOf(offset))
                .addParams("user_id", HService.getService(ServiceProfile.class).getUserId());
        if (!viewModel.editSearch.isEmpty()) {
            builder.addParams("search", viewModel.editSearch);
        } else {
            builder.addParams("category", getRequestValue("CATEGORY"))
                    .addParams("date", getRequestValue("DATE"))
                    .addParams("neighborhood", String.valueOf(neighborhood));
        }
        builder.build()
                .getCall()
                .bindLifecycle(this)
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<EventsItem>>>() {
                    @Override
                    protected void onRes(RemoteData<List<EventsItem>> data) {
                        if (offset == 0) {
                            viewSkeleton.hint();
                        }
                        if (offset==0){
                            adapter.setNewData(data.getNotNullData());
                        }else {
                            adapter.addData(data.getNotNullData());
                        }
                        adapter.loadMoreComplete();
                        if (data.getNotNullData().size() < 10) {
                            adapter.loadMoreEnd();
                        }
                        offset += 10;
                    }

                    @Override
                    protected void onFail(Exception e) {
                        super.onFail(e);
                        adapter.loadMoreFail();
                    }
                });
    }

    private String getRequestValue(String title) {
        String value = "0";
        List<SearchSectionsDao> searchSectionsDaos = viewModel.searchSectionsDaoLD.getValue();
        if (searchSectionsDaos == null) {
            return value;
        }
        Map<Integer, List<Integer>> selectIndexs = viewModel.selectIndexs;
        if (selectIndexs == null) {
            return value;
        }

        for (int i = 0; i < searchSectionsDaos.size(); i++) {
            SearchSectionsDao searchSectionsDao = searchSectionsDaos.get(i);
            if (searchSectionsDao.getTitle().equals(title)) {
                List<Integer> list = selectIndexs.get(i);
                if (list == null) {
                    return value;
                }
                if (list.contains(0) || list.isEmpty()) {
                    return value;
                }
                value = CollectionUtils.joinToString(list, ",", integer -> String.valueOf(searchSectionsDao.getItems().get(integer).getId()));
                break;
            }
        }
        return value;
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
