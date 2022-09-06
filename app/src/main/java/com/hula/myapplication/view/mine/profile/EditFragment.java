package com.hula.myapplication.view.mine.profile;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hula.myapplication.R;
import com.hula.myapplication.adapter.CategoriesAdapter;
import com.hula.myapplication.adapter.NewAdapter;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.FragmentMineEditBinding;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseLazyFragment;

public class EditFragment extends BaseLazyFragment {
    private FragmentMineEditBinding binding;
    private View llAboutMe;
    private EditText editAboutMe;
    private TextView tvEditLength;
    private RecyclerView recyclerProfile;
    private RecyclerView recyclerCategorie;
    private CategoriesAdapter categoriesAdapter;
    private List<SubCategoriesDao> subCategoriesDaos=new ArrayList<>();

    public static EditFragment getInstance() {
        EditFragment fragment = new EditFragment();
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
        binding = FragmentMineEditBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        llAboutMe = binding.llAboutMe;
        editAboutMe = binding.editAboutMe;
        tvEditLength = binding.tvEditLength;
        recyclerProfile = binding.recyclerProfile;
        recyclerCategorie = binding.recyclerCategorie;
        llAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAboutMe.setFocusable(true);
                editAboutMe.setFocusableInTouchMode(true);
                editAboutMe.requestFocus();
                //显示软键盘
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        binding.editAboutMe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (content.isEmpty()) {
                    tvEditLength.setText(160 + "");
                    return;
                }
                tvEditLength.setText((160 - content.length()) + "");
            }
        });
        initRecyclerViewProfile();
        initRecyclerViewCategorie();
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

    private void initRecyclerViewProfile() {
//        recyclerProfile.setLayoutManager(new FlowLayoutManager());
//        adapter = new NewAdapter(R.layout.adapter_what_new, data);
//        adapter.bindToRecyclerView(recyclerView);
//        adapter.setEnableLoadMore(false);
    }

    private void initRecyclerViewCategorie() {
        subCategoriesDaos.add(new SubCategoriesDao());
        recyclerCategorie.setLayoutManager(new FlowLayoutManager());
        categoriesAdapter = new CategoriesAdapter(R.layout.adapter_categorie, subCategoriesDaos);
        categoriesAdapter.bindToRecyclerView(recyclerCategorie);
        categoriesAdapter.setEnableLoadMore(false);
    }
}
