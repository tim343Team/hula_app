package com.hula.myapplication.view.search.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hula.myapplication.R;
import com.hula.myapplication.dao.SearchItem;
import com.hula.myapplication.dao.SearchSectionsDao;
import com.hula.myapplication.databinding.DialogSearchTopBinding;
import com.hula.myapplication.view.search.vm.SearchViewModel;
import com.hula.myapplication.widget.GrapItemDecoration;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.HulaBaseDialog;
import com.library.flowlayout.FlowLayoutManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchTopDialog extends DialogFragment {
    private DialogSearchTopBinding binding;
    private SearchViewModel searchViewModel;
    private SortAdapter sortAdapter;
    private HistoryAdapter historyAdapter;
    private SearchSectionsAdapter searchSectionsAdapter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    public HuCallBack1<String> huCallBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getParentFragment() != null;
        searchViewModel = new ViewModelProvider(getParentFragment()).get(SearchViewModel.class);
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
        searchViewModel.save();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchViewModel.select.observe(getViewLifecycleOwner(), integer -> {
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
        });

        searchViewModel.sortItemLD.observe(getViewLifecycleOwner(), searchItems -> sortAdapter.setNewData(searchItems));
        searchViewModel.searchSectionsDaoLD.observe(getViewLifecycleOwner(), searchSectionsDaos -> searchSectionsAdapter.setNewData(searchSectionsDaos));

        searchViewModel.searchHistory.observe(getViewLifecycleOwner(), strings -> historyAdapter.setNewData(strings));

        binding.tvFilter.setOnClickListener(v -> {
            if (Objects.equals(searchViewModel.select.getValue(), 0)) {
                searchViewModel.select.setValue(null);
            } else {
                searchViewModel.select.setValue(0);
            }
            binding.layoutContent.requestFocus();
            KeyboardUtils.hideSoftInput(binding.tvSearch);
        });
        binding.tvSort.setOnClickListener(v -> {
            if (Objects.equals(searchViewModel.select.getValue(), 1)) {
                searchViewModel.select.setValue(null);
            } else {
                searchViewModel.select.setValue(1);
            }

            binding.layoutContent.requestFocus();
            KeyboardUtils.hideSoftInput(binding.tvSearch);
        });
        binding.tvCancel.setOnClickListener(v -> dismiss());
        binding.tvSearch.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                searchViewModel.select.setValue(null);
            }
        });
        binding.tvSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (huCallBack != null) {
                    dismiss();
                    huCallBack.call(binding.tvSearch.getText().toString());
                    searchViewModel.add(binding.tvSearch.getText().toString());
                }
                return true;
            }
            return false;
        });
        if (searchViewModel.select.getValue() == null) {
            binding.tvSearch.post(() -> KeyboardUtils.showSoftInput(binding.tvSearch));
        }

        searchSectionsAdapter = new SearchSectionsAdapter();
        sortAdapter = new SortAdapter();
        historyAdapter = new HistoryAdapter();
        binding.recyclerView1.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView1.addItemDecoration(new GrapItemDecoration(8));
        binding.recyclerView1.setAdapter(searchSectionsAdapter);
        binding.recyclerView2.addItemDecoration(new GrapItemDecoration(8));
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView2.setAdapter(sortAdapter);
        binding.recyclerviewHostory.setLayoutManager(new FlowLayoutManager());
        binding.recyclerviewHostory.setAdapter(historyAdapter);


        binding.tvSubmit.setOnClickListener(v -> {
            dismiss();
            if (huCallBack != null) {
                huCallBack.call("");
            }
        });
    }

    @Override
    public void dismiss() {
        KeyboardUtils.hideSoftInput(binding.tvSearch);
        super.dismiss();
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


    class SearchSectionsAdapter extends BaseQuickAdapter<SearchSectionsDao, BaseViewHolder> {

        public Map<String, Integer> map = new HashMap<>();


        public SearchSectionsAdapter() {
            super(R.layout.item_search_sections);
            map.put("DATE", R.mipmap.icon_time_black);
            map.put("CATEGORY", R.mipmap.icon_bookmark_black);
            map.put("NEIGHBORHOOD", R.mipmap.icon_location_black);

        }

        @Override
        protected void convert(BaseViewHolder grouphelper, SearchSectionsDao dao) {

            grouphelper.setText(R.id.tv_title, dao.getTitle());
            Integer integer = map.get(dao.getTitle());
            if (integer != null) {
                TextView view = grouphelper.getView(R.id.tv_title);
                view.setCompoundDrawablesRelativeWithIntrinsicBounds(integer, 0, 0, 0);
            }

            BaseQuickAdapter<SearchSectionsDao.Item, BaseViewHolder> adapter = new BaseQuickAdapter<SearchSectionsDao.Item, BaseViewHolder>(R.layout.item_simple_text_1) {
                @Override
                protected void convert(BaseViewHolder helper, SearchSectionsDao.Item item) {
                    TextView view = helper.getView(R.id.tv);
                    view.setText(item.getTitle());
                    List<Integer> list = searchViewModel.selectIndexs.get(grouphelper.getAbsoluteAdapterPosition());
                    if (list != null) {
                        boolean contains = list.contains(helper.getAbsoluteAdapterPosition());
                        int index = helper.getAbsoluteAdapterPosition();
                        if (contains) {
                            view.setTextColor(Color.parseColor("#835EE2"));
                            view.setBackgroundResource(R.drawable.shape_radius100_strock2_8e73d3);
                        } else {
                            view.setTextColor(Color.parseColor("#B6B6B6"));
                            view.setBackgroundResource(R.drawable.shape_radius100_stroke1_b6b6b6);
                        }
                        view.setOnClickListener(v -> {
                            if (contains) {
                                list.remove(Integer.valueOf(index));
                            } else {
                                if (index > 0) {
                                    list.remove(Integer.valueOf(0));
                                } else {
                                    list.clear();
                                }
                                list.add(index);
                            }
                            if (list.isEmpty()) {
                                list.add(0);
                            }
                            notifyItemRangeChanged(0, dao.getItems().size());
                        });
                    }
                }
            };
            adapter.setNewData(dao.getItems());
            RecyclerView recyclerView = grouphelper.getView(R.id.recyclerView);
            recyclerView.setLayoutManager(new FlowLayoutManager());
            recyclerView.setItemAnimator(null);
            recyclerView.setAdapter(adapter);
        }
    }

    class SortAdapter extends BaseQuickAdapter<SearchItem, BaseViewHolder> {
        private int oldPosition = -1;

        public SortAdapter() {
            super(R.layout.item_simple_select_text);
            setOnItemClickListener((adapter, view, position) -> {
                if (!Objects.equals(searchViewModel.sortSelectPosition.getValue(), position)) {
                    searchViewModel.sortSelectPosition.setValue(position);
                }
            });
            searchViewModel.sortSelectPosition.observe(getViewLifecycleOwner(), integer -> {
                if (oldPosition != -1) {
                    notifyItemChanged(oldPosition);
                }
                notifyItemChanged(integer);
                oldPosition = integer;
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
            helper.getView(R.id.iv_delect).setOnClickListener(v -> searchViewModel.remove(item));
            helper.itemView.setOnClickListener(v -> {
                if (huCallBack != null) {
                    huCallBack.call(item);
                }
            });
        }
    }
}
