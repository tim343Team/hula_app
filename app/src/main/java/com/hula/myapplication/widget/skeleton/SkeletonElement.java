package com.hula.myapplication.widget.skeleton;

import android.view.View;
import android.view.ViewGroup;

public abstract class SkeletonElement {
    private View view;

    final View init(ViewGroup viewGroup) {
        if (view == null) {
            view = create(viewGroup);
        }
        return view;
    }


    protected abstract View create(ViewGroup viewGroup);


    protected void onAdd(Object argment) {
    }


    protected void onRemove() {
    }

}
