package com.hula.myapplication.view.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.dao.CategoriesDao;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.FragmentRegisterFavoriteBinding;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.widget.ColorItemDecoration;
import com.hula.myapplication.widget.HuCallBack1;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class FavoriteFragment extends Fragment {
    private FragmentRegisterFavoriteBinding binding;
    private final CategoriesDaoAdapter categoriesDaoAdapter = new CategoriesDaoAdapter();
    public HuCallBack1<Object> selectCall;
    public List<SubCategoriesDao> select = new ArrayList<>();
    private final HashMap<String, Integer> idToDrawableId = new HashMap<String, Integer>() {
        {
            put("Food", R.mipmap.icon_ca_food);
            put("Gaming", R.mipmap.icon_ca_games);
            put("Music", R.mipmap.icon_ca_music);
            put("Night Out", R.mipmap.icon_ca_night);
            put("Outdoor", R.mipmap.icon_ca_outing);
            put("Cultural", R.mipmap.icon_ca_cultural);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.addItemDecoration(new ColorItemDecoration());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(categoriesDaoAdapter);
        request();
    }


    private void request() {
        WonderfulOkhttpUtils.postJson()
                .body("{}")
                .url(UrlFactory.getSubCategories())
                .build()
                .getCall()
                .bindLifecycle(this)
                .enqueue(new GsonWalkDogCallBack<RemoteData<List<SubCategoriesDao>>>() {
                    @Override
                    protected void onRes(RemoteData<List<SubCategoriesDao>> data) throws Exception {
                        categoriesDaoAdapter.setCategoriesDaoData(data.getNotNullData());
                    }
                });
    }


    class CategoriesDaoAdapter extends BaseQuickAdapter<CategoriesDao, BaseViewHolder> {
        private final List<Integer> expand = new ArrayList<>();

        HashMap<CategoriesDao, List<SubCategoriesDao>> categoriesDaomap = new HashMap<>();


        public CategoriesDaoAdapter() {
            super(R.layout.item_favorite_group);
        }


        public void setCategoriesDaoData(List<SubCategoriesDao> subCategoriesDao) {
            for (int i = 0; i < subCategoriesDao.size(); i++) {
                SubCategoriesDao subCategorie = subCategoriesDao.get(i);
                CategoriesDao category = subCategorie.getCategory();
                List<SubCategoriesDao> subCategoriesDaos;
                if (!categoriesDaomap.containsKey(category)) {
                    subCategoriesDaos = new ArrayList<>();
                    categoriesDaomap.put(category, subCategoriesDaos);
                } else {
                    subCategoriesDaos = categoriesDaomap.get(category);
                }
                Objects.requireNonNull(subCategoriesDaos).add(subCategorie);
            }

            Set<CategoriesDao> categoriesDaos = categoriesDaomap.keySet();
            setNewData(CollectionUtils.map(categoriesDaos, categoriesDao -> categoriesDao));
        }


        @Override
        protected void convert(BaseViewHolder helper, CategoriesDao item) {

            boolean isExpand = expand.contains(helper.getAbsoluteAdapterPosition());
            helper.setText(R.id.tv_name, item.getName());
            Integer integer = idToDrawableId.get(item.getName());
            if (integer != null) {
                helper.setImageResource(R.id.iv, integer);
            }
            helper.getView(R.id.layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isExpand) {
                        expand.remove(Integer.valueOf(helper.getAbsoluteAdapterPosition()));
                    } else {
                        expand.add(helper.getAbsoluteAdapterPosition());
                    }
                    notifyItemChanged(helper.getAbsoluteAdapterPosition());
                }
            });
            RecyclerView recyclerView = helper.getView(R.id.recyclerView);
            if (isExpand) {
                helper.getView(R.id.iv_down).setRotation(0);
                recyclerView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.GONE);
                helper.getView(R.id.iv_down).setRotation(180);
            }
            if (recyclerView.getLayoutManager() == null) {
                recyclerView.setLayoutManager(new FlowLayoutManager());
            }
            boolean needRefresh = false;
            SubCategoriesDaoAdapter adapter = (SubCategoriesDaoAdapter) recyclerView.getAdapter();
            if (adapter == null) {
                needRefresh = true;
            }
            if (adapter != null && !adapter.getData().isEmpty()) {
                SubCategoriesDao data = adapter.getData().get(0);
                if (data.getCategory().getId() != item.getId()) {
                    needRefresh = true;
                }
            }
            if (needRefresh) {
                adapter = new SubCategoriesDaoAdapter(select, selectCall);
                recyclerView.setAdapter(adapter);
                List<SubCategoriesDao> subCategoriesDaos = categoriesDaomap.get(item);
                if (subCategoriesDaos != null) {
                    adapter.addData(subCategoriesDaos);
                }
            }

        }
    }

    class SubCategoriesDaoAdapter extends BaseQuickAdapter<SubCategoriesDao, BaseViewHolder> {

        private final List<SubCategoriesDao> selectCollection;
        private final HuCallBack1<Object> selectCall;

        public SubCategoriesDaoAdapter(List<SubCategoriesDao> select, HuCallBack1<Object> selectCall) {
            super(R.layout.item_favorite_sub);
            this.selectCall = selectCall;
            this.selectCollection = select;
        }

        @Override
        protected void convert(BaseViewHolder helper, SubCategoriesDao item) {
            final boolean[] isSelect = {selectCollection.contains(item)};
            TextView tv = helper.getView(R.id.tv);
            View itemView = helper.getView(R.id.layout);
            tv.setText(item.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSelect[0] = !isSelect[0];
                    if (isSelect[0]) {
                        selectCollection.add(item);
                        itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3));
                        tv.setTextColor(Color.WHITE);
                    } else {
                        selectCollection.remove(item);
                        tv.setTextColor(Color.BLACK);
                        itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
                    }
                    selectCall.call(new Object());

                }
            });
            if (isSelect[0]) {
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3));
                tv.setTextColor(Color.WHITE);
            } else {
                tv.setTextColor(Color.BLACK);
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
            }

            Integer integer = idToDrawableId.get(item.getCategory().getName());
            if (integer != null) {
                helper.setImageResource(R.id.iv, integer);
            }
        }
    }
}
