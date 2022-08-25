package com.hula.myapplication.widget.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.PermissionUtils;
import com.hula.myapplication.databinding.DialogPermissionBinding;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermissonDialog extends BaseBottomDialog {
    private DialogPermissionBinding binding;

    private PermissonDialog.Builder builder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogPermissionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (builder == null) {
            dismiss();
            return;
        }
        if (builder.drawableId != 0) {
            binding.imageView.setImageResource(builder.drawableId);
        }

        binding.tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                builder.permissionHand.onResult(false, new ArrayList<>(), Arrays.asList(builder.permissions));
            }
        });
        binding.tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (builder.permissionHand == null) {
                    return;
                }
                builder.permissionHand.request(requireActivity(), builder.permissions);
            }
        });
        if (!TextUtils.isEmpty(builder.subTitle)) {
            binding.tvSubTitle.setText(builder.subTitle);
        }
        if (!TextUtils.isEmpty(builder.title)) {
            binding.tvTitle.setText(builder.title);
        }
    }


    public static class Builder {

        private String[] permissions = new String[0];
        private int drawableId;
        private String title;
        private String subTitle;
        private PermissionHand permissionHand = null;


        public Builder setPermissions(String... permissions) {
            this.permissions = permissions;
            return this;
        }

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


        public void request(FragmentManager manager, PermissionHand permissionHand) {
            this.permissionHand = permissionHand;
            if (permissionHand == null) {
                return;
            }
            boolean granted = permissionHand.isGranted(permissions);
            if (granted) {
                permissionHand.onResult(true, new ArrayList<>(), Arrays.asList(permissions));
                return;
            }
            PermissonDialog permissonDialog = new PermissonDialog();
            permissonDialog.builder = this;
            permissonDialog.show(manager, "");
        }
        //标准的权限请求请用这个
        public abstract static class StandardPermissionHand implements PermissionHand {

            @Override
            public final boolean isGranted(String[] permissions) {
                return PermissionUtils.isGranted(permissions);
            }

            @Override
            public final void request(FragmentActivity activity, String[] permissions) {
                PermissionX.init(activity)
                        .permissions(permissions)
                        .request(this);
            }
        }

    }

    public interface PermissionHand extends RequestCallback {
        boolean isGranted(String[] permissions);

        void request(FragmentActivity activity, String[] permissions);

    }

}
