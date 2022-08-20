package com.hula.myapplication.widget.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.hula.myapplication.R;
import com.hula.myapplication.widget.HuCallBack1;


public class HulaConfirmDialog extends DialogFragment {
    private HuCallBack1.HuCallBack2<Integer, DialogFragment> callBack;
    private Builder builder;

    public HulaConfirmDialog setCallBack(HuCallBack1.HuCallBack2<Integer, DialogFragment> callBack)  {
        this.callBack = callBack;
        return this;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_confirm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (builder == null) {
            return;
        }
        view.findViewById(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ImageView iv_header = view.findViewById(R.id.iv_header);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_subTitle = view.findViewById(R.id.tv_subTitle);
        TextView btn_1 = view.findViewById(R.id.btn_1);
        TextView btn_0 = view.findViewById(R.id.btn_0);


        if (builder.drawableId != 0) {
            iv_header.setVisibility(View.VISIBLE);
            iv_header.setImageResource(builder.drawableId);
        } else {
            iv_header.setVisibility(View.GONE);
        }

        setText(builder.title, tv_title);
        setText(builder.subTitle, tv_subTitle);

        setText(builder.action0Txt, btn_0);
        setText(builder.action1Txt, btn_1);


        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.call(0, HulaConfirmDialog.this);
                }
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.call(1, HulaConfirmDialog.this);
                }
            }
        });

    }


    private void setText(String text, TextView view) {
        if (text != null) {
            view.setVisibility(View.VISIBLE);
            view.setText(text);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void setGone(boolean gone, TextView view) {
        if (gone) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new HulaBaseDialog(requireContext(), getTheme());
    }

    public static class Builder {


        private int drawableId;

        private String title;

        private String subTitle;

        private String action0Txt;

        private String action1Txt;

        public Builder setDrawableId(int drawableId) {
            this.drawableId = drawableId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            this.subTitle = subTitle;
            return this;

        }

        public Builder setAction0Txt(String action0Txt) {
            this.action0Txt = action0Txt;
            return this;

        }


        public Builder setAction1Txt(String action1Txt) {
            this.action1Txt = action1Txt;
            return this;

        }


        public HulaConfirmDialog Build() {
            HulaConfirmDialog hulaBaseDialog = new HulaConfirmDialog();
            hulaBaseDialog.builder = this;
            return hulaBaseDialog;
        }
    }

}
