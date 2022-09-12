package com.hula.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.hula.myapplication.R;
import com.hula.myapplication.widget.WHImageView;

import java.util.List;

public class PreviewImagePagerAdapter  extends PagerAdapter{

    private int[] banners = new int[]{R.color.app_main_color, R.color.color_d351a4, R.color.teal_200};
    private Context context;
    private View.OnClickListener onBannerClickListener;
    private static final String TAG = "MyPagerAdapter";

    public PreviewImagePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        //返回int的最大值 可以一直滑动
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view,Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= banners.length;
        WHImageView imageView = new WHImageView(context);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setTopLeftCornerSize(50)
                .setTopRightCornerSize(50)
                .build();
        imageView.setShapeAppearanceModel(shapeAppearanceModel);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setTag(position);
        imageView.setImageResource(banners[position]);
        imageView.setOnClickListener(onClickListener);
        container.addView(imageView);
        return imageView;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBannerClickListener.onClick(v);
        }
    };

    @Override
    public void destroyItem(ViewGroup container, int position,Object object) {
        container.removeView((View) object);
    }

    public void setOnBannerClickListener(View.OnClickListener onBannerClickListener) {
        this.onBannerClickListener = onBannerClickListener;
    }

    public int[] getBanners() {
        return banners;
    }

}
