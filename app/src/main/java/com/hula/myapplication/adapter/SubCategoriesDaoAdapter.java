package com.hula.myapplication.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.List;

public class SubCategoriesDaoAdapter extends BaseQuickAdapter<SubCategoriesDao, BaseViewHolder> {
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
        //图片
//        Integer integer = idToDrawableId.get(item.getCategory().getName());
//        if (integer != null) {
//            helper.setImageResource(R.id.iv, integer);
//        }
    }
}
