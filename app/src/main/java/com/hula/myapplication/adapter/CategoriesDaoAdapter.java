package com.hula.myapplication.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.CategoriesDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.view.login.RegisterFavoriteActivity;
import com.hula.myapplication.widget.HuCallBack1;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CategoriesDaoAdapter extends BaseQuickAdapter<CategoriesDao, BaseViewHolder> {
    private final List<Integer> expand = new ArrayList<>();
    List<SubCategoriesDao> select = new ArrayList<>();
    HashMap<CategoriesDao, List<SubCategoriesDao>> categoriesDaomap = new HashMap<>();
    private HuCallBack1<Object> selectCall;

    public CategoriesDaoAdapter(HuCallBack1<Object> selectCall) {
        super(R.layout.item_favorite_group);
        this.selectCall = selectCall;
    }

    public void addSelectDao(List<SubCategoriesDao> daos){
        select.addAll(daos);
    }

    public List<SubCategoriesDao> getSelect() {
        return select;
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
        //TODO 图片
//        Integer integer = idToDrawableId.get(item.getName());
//        if (integer != null) {
//            helper.setImageResource(R.id.iv, integer);
//        }
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
