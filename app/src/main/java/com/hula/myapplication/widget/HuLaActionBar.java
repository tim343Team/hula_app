package com.hula.myapplication.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hula.myapplication.R;

public class HuLaActionBar extends FrameLayout {

    public interface OnItemClickListener {
        void onClick(int position, View view);
    }

    private ImageView ivBack;
    private TextView tvTitle;
    private LinearLayout layoutMenu;
    private OnClickListener backClickListener;
    private OnItemClickListener menuClickListener;


    public HuLaActionBar(@NonNull Context context) {
        this(context, null);
    }

    public HuLaActionBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HuLaActionBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_action_bar, this);
        setClipChildren(false);
        ivBack = view.findViewById(R.id.iv_back);
        tvTitle = view.findViewById(R.id.tv_title);
        layoutMenu = view.findViewById(R.id.layout_menu);
        int minH = dp2px(context, 60);
        setMinimumHeight(minH);
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backClickListener != null) {
                    backClickListener.onClick(v);
                    return;
                }
                Activity activity = getActivity(getContext());
                if (activity != null) {
                    activity.finish();
                }
            }
        });


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HuLaActionBar);
        boolean b = typedArray.getBoolean(R.styleable.HuLaActionBar_acb_enableBack, true);
        if (b) {
            ivBack.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
        String title = typedArray.getString(R.styleable.HuLaActionBar_acb_title);
        if (title != null) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            ivBack.setVisibility(View.GONE);
        }
        typedArray.recycle();
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (child instanceof HuLaActionBarMenu) {
            addMenu((HuLaActionBarMenu) child, params);
        } else {
            super.addView(child, params);
        }
    }

    private void addMenu(HuLaActionBarMenu child, ViewGroup.LayoutParams params) {
        layoutMenu.addView(child, params);
        child.setOnClickListener(v -> {
            if (menuClickListener != null) {
                int index = layoutMenu.indexOfChild(child);
                if (index != -1) {
                    menuClickListener.onClick(index, layoutMenu.getChildAt(0));
                }
            }
        });
    }

    static int dp2px(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    @Nullable
    static Activity getActivity(Context context) {
        Context _context = context;
        while (_context instanceof ContextWrapper) {
            if (_context instanceof Activity) {
                return (Activity) _context;
            }
            _context = ((ContextWrapper) _context).getBaseContext();
        }
        return null;
    }
}
