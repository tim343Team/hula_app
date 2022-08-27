package com.hula.myapplication.view.search.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.internal.FlowLayout;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SearchItem;
import com.hula.myapplication.databinding.DialogSearchTopBinding;
import com.hula.myapplication.util.CollectionUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.view.search.vm.SearchViewModel;
import com.hula.myapplication.widget.GrapItemDecoration;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.HulaBaseDialog;
import com.library.flowlayout.FlowLayoutManager;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SearchTopDialog extends DialogFragment {
    private DialogSearchTopBinding binding;
    private SearchViewModel searchViewModel;
    private SortAdapter sortAdapter;
    private HistoryAdapter historyAdapter;
    public HuCallBack1.HuCallBack2<String, HuCallBack1<Boolean>> editCall;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable saveSearch = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    public int getTheme() {
        return R.style.LDialogStyle_NoDim;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogSearchTopBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (saveSearch != null) {
            saveSearch.run();
        }
        searchViewModel.save();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel.select.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == null) {
                    binding.recyclerviewHostory.setVisibility(View.VISIBLE);
                    binding.recyclerView1.setVisibility(View.GONE);
                    binding.recyclerView2.setVisibility(View.GONE);
                    binding.viewBottom.setVisibility(View.GONE);
                    binding.tvSubmit.setVisibility(View.GONE);
                    binding.tvSort.setTextColor(Color.parseColor("#AD9ED3"));
                    binding.tvSort.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_strock1_8e73d3));
                    binding.tvFilter.setTextColor(Color.parseColor("#AD9ED3"));
                    binding.tvFilter.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_strock1_8e73d3));
                    searchViewModel.refreshHistory();
                    return;
                }
                binding.recyclerviewHostory.setVisibility(View.GONE);
                if (integer == 0) {
                    onSelect(binding.tvFilter, binding.tvSort);
                    binding.recyclerView1.setVisibility(View.VISIBLE);
                    binding.recyclerView2.setVisibility(View.GONE);
                } else {
                    onSelect(binding.tvSort, binding.tvFilter);
                    binding.recyclerView1.setVisibility(View.GONE);
                    binding.recyclerView2.setVisibility(View.VISIBLE);
                }
                binding.viewBottom.setVisibility(View.VISIBLE);
                binding.tvSubmit.setVisibility(View.VISIBLE);
            }
        });

        searchViewModel.sortItemLD.observe(getViewLifecycleOwner(), new Observer<List<SearchItem>>() {
            @Override
            public void onChanged(List<SearchItem> searchItems) {
                sortAdapter.setNewData(searchItems);
            }
        });

        searchViewModel.searchHistory.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                historyAdapter.setNewData(strings);
            }
        });
        binding.tvSearch.post(new Runnable() {
            @Override
            public void run() {
                binding.tvSearch.requestFocus();
                KeyboardUtils.showSoftInput(binding.tvSearch);
            }
        });
        binding.tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(searchViewModel.select.getValue(), 0)) {
                    searchViewModel.select.setValue(null);
                } else {
                    searchViewModel.select.setValue(0);
                }
            }
        });
        binding.tvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(searchViewModel.select.getValue(), 1)) {
                    searchViewModel.select.setValue(null);
                } else {
                    searchViewModel.select.setValue(1);
                }
            }
        });
        binding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.tvSearch.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if (!TextUtils.isEmpty(s) && editCall != null) {
                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            editCall.call(s.toString().trim(), new HuCallBack1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    saveSearch = new Runnable() {
                                        @Override
                                        public void run() {
                                            searchViewModel.add(s.toString());
                                        }
                                    };
                                }
                            });
                        }
                    }, 100);
                }
            }
        });
        sortAdapter = new SortAdapter();
        historyAdapter = new HistoryAdapter();
        binding.recyclerView2.addItemDecoration(new GrapItemDecoration(8));
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView2.setAdapter(sortAdapter);
        binding.recyclerviewHostory.setLayoutManager(new FlowLayoutManager());
        binding.recyclerviewHostory.setAdapter(historyAdapter);
        searchViewModel.refreshHistory();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        KeyboardUtils.hideSoftInput(binding.tvSearch);
    }

    private void onSelect(TextView select, TextView unselect) {
        select.setTextColor(Color.WHITE);
        unselect.setTextColor(Color.parseColor("#AD9ED3"));
        select.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_8e73d3));
        unselect.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_strock1_8e73d3));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        HulaBaseDialog hulaBaseDialog = new HulaBaseDialog(requireContext(), getTheme());
        hulaBaseDialog.marginHorizontal = 0;
        hulaBaseDialog.mGravity = Gravity.TOP;
        return hulaBaseDialog;
    }

    class SortAdapter extends BaseQuickAdapter<SearchItem, BaseViewHolder> {
        private int oldPosition = -1;

        public SortAdapter() {
            super(R.layout.item_simple_select_text);
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (!Objects.equals(searchViewModel.sortSelectPosition.getValue(), position)) {
                        searchViewModel.sortSelectPosition.setValue(position);
                    }
                }
            });
            searchViewModel.sortSelectPosition.observe(getViewLifecycleOwner(), new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    if (oldPosition != -1) {
                        notifyItemChanged(oldPosition);
                    }
                    notifyItemChanged(integer);
                    oldPosition = integer;
                }
            });
        }

        @Override
        protected void convert(BaseViewHolder helper, SearchItem item) {
            TextView itemView = (TextView) helper.itemView;
            itemView.setText(item.txt);
            Integer position = searchViewModel.sortSelectPosition.getValue();
            if (Objects.equals(helper.getAbsoluteAdapterPosition(), position)) {
                itemView.setTextColor(Color.parseColor("#8E73D3"));
                itemView.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_strock2_8e73d3));
            } else {
                itemView.setTextColor(Color.parseColor("#979797"));
                itemView.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_radius100_stroke1_b6b6b6));
            }
        }
    }

    class HistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public HistoryAdapter() {
            super(R.layout.item_simple_tab);

        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_txt, item);
            helper.getView(R.id.iv_delect).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchViewModel.remove(item);
                }
            });
        }
    }
}
