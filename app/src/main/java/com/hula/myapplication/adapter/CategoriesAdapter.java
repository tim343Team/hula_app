package com.hula.myapplication.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.NewInfoDao;
import com.hula.myapplication.dao.SubCategoriesDao;

import java.util.List;

public class CategoriesAdapter extends BaseQuickAdapter<SubCategoriesDao, BaseViewHolder> {

    public CategoriesAdapter(int layoutResId, @Nullable List<SubCategoriesDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoriesDao item) {
        TextView tv = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        View itemView = helper.getView(R.id.layout);
        if(getItemCount()-1==helper.getLayoutPosition()){
            //如果是最后一个
            iv.setBackgroundResource(R.mipmap.icon_add_more);
            tv.setText(R.string.add_more);
            tv.setTextColor(Color.BLACK);
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
        }else {
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_8e73d3));
            tv.setTextColor(Color.WHITE);
        }
    }
}
