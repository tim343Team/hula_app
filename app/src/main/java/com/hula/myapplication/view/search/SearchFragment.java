package com.hula.myapplication.view.search;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.FragmentSearchBinding;
import com.hula.myapplication.view.home.HomeFragment;
import com.hula.myapplication.view.search.dialog.SearchTopDialog;
import com.hula.myapplication.view.search.vm.SearchViewModel;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseTransFragment;

public class SearchFragment extends BaseTransFragment {
    public static final String TAG = SearchFragment.class.getSimpleName();
    private FragmentSearchBinding binding;
    private SearchAdapter adapter;

    @Override
    protected String getmTag() {
        return TAG;
    }

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
            searchTopDialog.show(getChildFragmentManager(),"");
        });
        adapter = new SearchAdapter();
        adapter.setNewData(new ArrayList<Object>(){
            {
                add(new Object());
                add(new Object());
                add(new Object());
                add(new Object());
                add(new Object());
                add(new Object());

            }
        });
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

    }

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }

    class SearchAdapter extends BaseQuickAdapter<Object, BaseViewHolder>{

        public SearchAdapter() {
            super(R.layout.item_search_view);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item) {

        }
    }
}
