package com.hula.myapplication.base.picselect;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hula.myapplication.R;
import com.luck.picture.lib.engine.ImageEngine;

public class GlideImageEngine implements ImageEngine {
    public static GlideImageEngine engine = new GlideImageEngine();

    private GlideImageEngine() {
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    public void loadImage(Context context, ImageView imageView, String url, int maxWidth, int maxHeight) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.overrideOf(maxWidth,maxHeight))
                .into(imageView);

    }

    @Override
    public void loadAlbumCover(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(RequestOptions.overrideOf(180,180))
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.placeholderOf(R.drawable.ps_image_placeholder))
                .into(imageView);
    }

    @Override
    public void loadGridImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.overrideOf(180,180))
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.placeholderOf(R.drawable.ps_image_placeholder))
                .into(imageView);

    }

    @Override
    public void pauseRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    @Override
    public void resumeRequests(Context context) {
        Glide.with(context).resumeRequests();
    }
}
