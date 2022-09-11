package com.hula.myapplication.widget.skeleton;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;

public class ViewSkeleton {

    private final SkeletonElement loadingElement;
    private final SkeletonElement errElement;
    private SkeletonElement curSkeletonElement = null;
    private final View view;
    private final ViewGroup viewGroup;
    private final int index;


    public ViewSkeleton(View view, @Nullable SkeletonElement loadingElement, @Nullable SkeletonElement errElement) {
        this.view = view;
        this.viewGroup = (ViewGroup) view.getParent();
        index = viewGroup.indexOfChild(view);
        this.loadingElement = loadingElement;
        this.errElement = errElement;
    }

    public void hint() {
        if (curSkeletonElement != null) {
            curSkeletonElement.onRemove();
            curSkeletonElement = null;
        }
        View childView = viewGroup.getChildAt(index);
        if ("SkeletonElement".equals(childView.getTag())) {
            ViewCompat.animate(childView)
                    .alpha(0F)
                    .setDuration(200)
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            super.onAnimationEnd(view);
                            viewGroup.removeView(view);
                            viewGroup.addView(ViewSkeleton.this.view, index);
                        }
                    })
                    .start();
        }
    }


    public void showLoading() {
        if (loadingElement != null) {
            showElement(loadingElement, "");
        }
    }

    private void showElement(SkeletonElement element, Object obj) {
        if (element == curSkeletonElement) {
            return;
        }
        if (curSkeletonElement != null) {
            curSkeletonElement.onRemove();
        }
        curSkeletonElement = element;
        View skeletonView = curSkeletonElement.init(viewGroup);
        curSkeletonElement.onAdd(obj);

        skeletonView.setTag("SkeletonElement");

        skeletonView.setId(view.getId());
        skeletonView.setLayoutParams(view.getLayoutParams());
        viewGroup.removeView(view);
        skeletonView.setAlpha(1F);
        viewGroup.addView(skeletonView, index);


    }

    public void showErr(Throwable throwable) {
        if (errElement != null) {
            showElement(errElement, throwable);
        }

    }


}
