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
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.dao.SubProfileDao;

import java.util.List;

public class ProfileSettingAdapter extends BaseQuickAdapter<SubProfileDao, BaseViewHolder> {

    public ProfileSettingAdapter(int layoutResId, @Nullable List<SubProfileDao> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubProfileDao item) {
        TextView tv = helper.getView(R.id.tv);
        ImageView iv = helper.getView(R.id.iv);
        View itemView = helper.getView(R.id.layout);
        tv.setTextColor(Color.BLACK);
        itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.shape_radius100_strock1_8e73d3));
        if(getItemCount()-1==helper.getLayoutPosition()){
            //如果是最后一个
            iv.setBackgroundResource(R.mipmap.icon_add_more);
            tv.setText(R.string.custom);
        }else {
            tv.setText(item.getName());
        }
    }
}
