package com.hula.myapplication.view.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.PagerAdapter;
import com.hula.myapplication.view.mine.sub_fragment.EditFragment;
import com.hula.myapplication.view.mine.sub_fragment.PreviewFragment;
import com.hula.myapplication.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseTransFragment;

public class MineFragment extends BaseTransFragment {
    public static final String TAG = MineFragment.class.getSimpleName();
    TextView[] tvTabs;
    NoScrollViewPager viewpager;
    private PagerAdapter adapter;
    private PreviewFragment subFragment1;
    private EditFragment subFragment2;
    private ArrayList<String> tabs = new ArrayList<>();
    protected List<Fragment> fragments = new ArrayList<>();

    private TextView tvPreview;
    private TextView tvEdit;


    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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
        viewpager = rootView.findViewById(R.id.viewpager_mine);
        tvPreview = rootView.findViewById(R.id.tv_preview);
        tvEdit = rootView.findViewById(R.id.tv_edit);
        tvTabs = new TextView[]{tvPreview, tvEdit};
        setView();
        showTab(1);
//        llAboutMe = rootView.findViewById(R.id.ll_about_me);
//        editAboutMe = rootView.findViewById(R.id.edit_about_me);
//        tvEditLength = rootView.findViewById(R.id.tv_edit_length);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(0);
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(1);
            }
        });
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

    private void setView() {
        tabs.clear();
        fragments.clear();
        for (TextView textView : tvTabs) {
            tabs.add("");
        }
        fragments.add(subFragment1 = PreviewFragment.getInstance());
        fragments.add(subFragment2 = EditFragment.getInstance());
        if (adapter == null) {
            adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), fragments, tabs);
            viewpager.setAdapter(adapter);
            viewpager.setOffscreenPageLimit(fragments.size() - 1);
            viewpager.setCurrentItem(1);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    void showTab(int position) {
        for (int i = 0; i < tvTabs.length; i++) {
            if (i == position) {
                tvTabs[i].setSelected(true);
            } else {
                tvTabs[i].setSelected(false);
            }
        }
        viewpager.setCurrentItem(position);
    }

}
