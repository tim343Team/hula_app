package com.hula.myapplication.view.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.dao.CategoriesDao;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.ActivityRegisterFavoriteBinding;
import com.hula.myapplication.databinding.ItemFavoriteGroupBinding;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.widget.HuCallBack1;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class RegisterFavoriteActivity extends HBaseActivity {
    private ActivityRegisterFavoriteBinding binding;
    private final CategoriesDaoAdapter categoriesDaoAdapter = new CategoriesDaoAdapter();
    private final HuCallBack1<Object> selectCall = new HuCallBack1<Object>() {
        @Override
        public void call(Object o) {
            binding.tvConfirm.setEnabled(categoriesDaoAdapter.select.size() >= 6);
        }
    };

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterFavoriteActivity.this, RegisterPicActivity.class);
                startActivity(intent);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }

    class CategoriesDaoAdapter extends BaseQuickAdapter<CategoriesDao, BaseViewHolder> {
        private final List<Integer> expand = new ArrayList<>();
        List<SubCategoriesDao> select = new ArrayList<>();
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
            final boolean isSelect = selectCollection.contains(item);
            TextView tv = helper.getView(R.id.tv);
            ImageView iv = helper.getView(R.id.iv);
            View itemView = helper.itemView;
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelect) {
                        selectCollection.remove(item);
                    } else {
                        selectCollection.add(item);
                    }
                    selectCall.call(new Object());
                }
            });
            if (isSelect) {
                selectCollection.add(item);
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3));
                tv.setTextColor(Color.WHITE);
            } else {
                selectCollection.remove(item);
                tv.setTextColor(Color.BLACK);
                itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
            }

            Integer integer = idToDrawableId.get(item.getName());
            if (integer != null) {
                helper.setImageResource(R.id.iv, integer);
            }
        }
    }
}
