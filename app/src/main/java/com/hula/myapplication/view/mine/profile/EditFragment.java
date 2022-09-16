package com.hula.myapplication.view.mine.profile;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.TimeUtils;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.hula.myapplication.R;
import com.hula.myapplication.adapter.CategoriesSettingAdapter;
import com.hula.myapplication.adapter.NewAdapter;
import com.hula.myapplication.adapter.ProfileSettingAdapter;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.dao.PronounDao;
import com.hula.myapplication.dao.SubCategoriesDao;
import com.hula.myapplication.dao.SubProfileDao;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.FragmentMineEditBinding;
import com.hula.myapplication.util.SafeGet;
import com.hula.myapplication.view.mine.profile.sub.ChooseInterActivity;
import com.hula.myapplication.view.mine.profile.sub.EditDrinkActivity;
import com.hula.myapplication.view.mine.profile.sub.EditNameActivity;
import com.hula.myapplication.view.mine.profile.sub.EditSchoolActivity;
import com.hula.myapplication.view.mine.profile.sub.EditWorkActivity;
import com.hula.myapplication.view.mine.profile.sub.ProfileBadgeActivity;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.BottomSelectDialog;
import com.library.flowlayout.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tim.com.libnetwork.base.BaseLazyFragment;
import tim.com.libnetwork.utils.DateTimeUtil;
import tim.com.libnetwork.utils.WonderfulStringUtils;

public class EditFragment extends BaseLazyFragment {
    private FragmentMineEditBinding binding;
    private View llAboutMe;
    private EditText editAboutMe;
    private TextView tvEditLength;
    private TextView tvAge;
    private RecyclerView recyclerProfile;
    private RecyclerView recyclerCategorie;
    private CategoriesSettingAdapter categoriesAdapter;
    private ProfileSettingAdapter profileSettingAdapter;
    private List<SubCategoriesDao> subCategoriesDaos = new ArrayList<>();
    private List<SubProfileDao> subProfileDaos = new ArrayList<>();
    private Date selectDate;

    public static EditFragment getInstance() {
        EditFragment fragment = new EditFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected View getLayoutView() {
        binding = FragmentMineEditBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        llAboutMe = binding.llAboutMe;
        editAboutMe = binding.editAboutMe;
        tvEditLength = binding.tvEditLength;
        tvAge = binding.tvAge;
        recyclerProfile = binding.recyclerProfile;
        recyclerCategorie = binding.recyclerCategorie;
        View llEvent1 = binding.llEvent1;
        View llEvent2 = binding.llEvent2;
        View llEvent3 = binding.llEvent3;
        llAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAboutMe.setFocusable(true);
                editAboutMe.setFocusableInTouchMode(true);
                editAboutMe.requestFocus();
                //显示软键盘
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        binding.editAboutMe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String content = editable.toString();
                if (content.isEmpty()) {
                    tvEditLength.setText(160 + "");
                    return;
                }
                tvEditLength.setText((160 - content.length()) + "");
            }
        });
        binding.editEvent1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    llEvent1.setBackgroundResource(R.drawable.shape_radius15_stroke_b7b7b7);
                } else {
                    llEvent1.setBackgroundResource(R.drawable.shape_radius15_stroke1);
                }
            }
        });
        binding.editEvent2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    llEvent2.setBackgroundResource(R.drawable.shape_radius15_stroke_b7b7b7);
                } else {
                    llEvent2.setBackgroundResource(R.drawable.shape_radius15_stroke1);
                }
            }
        });
        binding.editEvent3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    llEvent3.setBackgroundResource(R.drawable.shape_radius15_stroke_b7b7b7);
                } else {
                    llEvent3.setBackgroundResource(R.drawable.shape_radius15_stroke1);
                }
            }
        });
        binding.llName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditNameActivity.actionStart(getmActivity());
            }
        });
        binding.llAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 17);
                SingleDateAndTimePickerDialog.Builder builder = new SingleDateAndTimePickerDialog.Builder(view.getContext())
                        .backgroundColor(Color.WHITE)
                        .title("Select DOB")
                        .displayHours(false)
                        .displayDays(false)
                        .displayMinutes(false)
                        .displayDaysOfMonth(true)
                        .displayYears(true)
                        .displayMonth(true);
                if (selectDate != null) {
                    builder.defaultDate(selectDate);
                } else {
                    builder.defaultDate(calendar.getTime());
                }
                builder.maxDateRange(calendar.getTime())
                        .bottomSheet()
                        .mainColor(Color.parseColor("#8E73D3"))
                        .titleTextColor(Color.parseColor("#8E73D3"))
                        .build()
                        .setListener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                selectDate = date;
                                onSelectDate();
                            }
                        })
                        .display();
            }
        });
        binding.llSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditSchoolActivity.actionStart(getmActivity());
            }
        });
        binding.llJobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditWorkActivity.actionStart(getmActivity());
            }
        });
        binding.llDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDrinkActivity.actionStart(getmActivity());
            }
        });
        initRecyclerViewProfile();
        initRecyclerViewCategorie();
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        HService.getService(ServiceProfile.class).asyncGetUserInfo().onGet(new SafeGet.SafeCall<UserInfoData>() {
            @Override
            public void call(UserInfoData userInfoData) {
                //TODO

            }
        });

//        ServiceProfile service = HService.getService(ServiceProfile.class);
//        service.refresh();
//        service.addRefreshListener(this, new HuCallBack1<UserInfoData>() {
//            @Override
//            public void call(UserInfoData userInfoData) {
//                handler.post(startNext);
//            }
//        });
    }

    @Override
    protected void RefreshloadData() {

    }

    @Override
    protected void initDestroy() {

    }

    private void onSelectDate() {
        if (selectDate == null) {
            binding.tvAge.setText("");
            return;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(selectDate);
        binding.tvAge.setText(DateTimeUtil.getAge(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)) + "");
    }

    private void initRecyclerViewProfile() {
        subProfileDaos.add(new SubProfileDao());
        recyclerProfile.setLayoutManager(new FlowLayoutManager());
        profileSettingAdapter = new ProfileSettingAdapter(R.layout.adapter_setting_profile, subProfileDaos,true);
        profileSettingAdapter.bindToRecyclerView(recyclerProfile);
        profileSettingAdapter.setEnableLoadMore(false);
        profileSettingAdapter.AddListenerItem(new ProfileSettingAdapter.AddListenerItem() {
            @Override
            public void click() {
                ProfileBadgeActivity.actionStart(getmActivity());
            }
        });
    }

    private void initRecyclerViewCategorie() {
        subCategoriesDaos.add(new SubCategoriesDao());
        recyclerCategorie.setLayoutManager(new FlowLayoutManager());
        categoriesAdapter = new CategoriesSettingAdapter(R.layout.adapter_setting_categorie, subCategoriesDaos);
        categoriesAdapter.bindToRecyclerView(recyclerCategorie);
        categoriesAdapter.setEnableLoadMore(false);
        categoriesAdapter.AddListenerItem(new CategoriesSettingAdapter.AddListenerItem() {
            @Override
            public void click() {
                ChooseInterActivity.actionStart(getmActivity());
            }
        });
    }
}
