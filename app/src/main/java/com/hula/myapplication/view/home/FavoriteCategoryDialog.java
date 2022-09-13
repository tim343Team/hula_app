package com.hula.myapplication.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.databinding.DialogFavoriteCategoryBinding;
import com.hula.myapplication.view.home.vm.AddNewEventVM;
import com.hula.myapplication.widget.dialog.BaseBottomDialog;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCategoryDialog extends BaseBottomDialog {
    private DialogFavoriteCategoryBinding binding;
    private AddNewEventVM addNewEventVM;
    private final FavoriteFragment favoriteFragment = new FavoriteFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNewEventVM = new ViewModelProvider(requireActivity()).get(AddNewEventVM.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFavoriteCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.getRoot().setMinimumHeight(ScreenUtils.getAppScreenHeight());
        binding.getRoot().post(() -> setPeekHeight(binding.getRoot().getHeight()));
        binding.actionBar.setBackClickListener(v -> dismiss());
        binding.actionBar.setMenuClickListener((position, view1) -> {
            dismiss();
            addNewEventVM.subCategoriesDaoMutableLD.setValue(favoriteFragment.select);
        });

        List<SubCategoriesDao> value = addNewEventVM.subCategoriesDaoMutableLD.getValue();
        if (value == null) {
            value = new ArrayList<>();
        }
        if (value.isEmpty()) {
            binding.menu.setVisibility(View.INVISIBLE);
        } else {
            binding.menu.setVisibility(View.VISIBLE);
        }
        favoriteFragment.select = value;
        favoriteFragment.selectCall = o -> {
            if (favoriteFragment.select.isEmpty()) {
                binding.menu.setVisibility(View.INVISIBLE);
            } else {
                binding.menu.setVisibility(View.VISIBLE);
            }
        };
        getChildFragmentManager().beginTransaction()
                .replace(R.id.layout, favoriteFragment)
                .commitNow();
    }

}
