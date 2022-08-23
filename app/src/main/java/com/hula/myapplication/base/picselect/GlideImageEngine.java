package com.hula.myapplication.base.picselect;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
                .load(url).into(imageView);

    }

    @Override
    public void loadAlbumCover(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);

    }

    @Override
    public void loadGridImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(url).into(imageView);

    }

    @Override
    public void pauseRequests(Context context) {

    }

    @Override
    public void resumeRequests(Context context) {

    }
}
