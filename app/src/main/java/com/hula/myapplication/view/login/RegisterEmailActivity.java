package com.hula.myapplication.view.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.dao.PronounDao;
import com.hula.myapplication.databinding.ActivityRegisterEmailBinding;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.widget.HuCallBack1;
import com.hula.myapplication.widget.dialog.BottomSelectDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegisterEmailActivity extends HBaseActivity {

    static class RegisterEmailData {
        public PronounDao dao;
        public String email;
        public String name;
        public Date date;

        public RegisterEmailData(PronounDao dao, String email, String name, Date date) {
            this.dao = dao;
            this.email = email;
            this.name = name;
            this.date = date;
        }
    }


    private ActivityRegisterEmailBinding binding;
    private final List<PronounDao> pronounDaoList = new ArrayList<PronounDao>() {
        {
            add(new PronounDao(0, "He/Him/His"));
            add(new PronounDao(0, "SHe/Her/Hers"));
            add(new PronounDao(0, "They/Them/Theirs"));
            add(new PronounDao(0, "Other"));
        }
    };

    private int selectPronPosition = -1;
    private Date selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.editEmail.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.editName.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                checkSubmit();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterEmailData emailData = new RegisterEmailData(pronounDaoList.get(selectPronPosition), binding.editEmail.getText().toString(), binding.editName.getText().toString(), selectDate);
                HService.getService(PageDataHoldService.class).add("RegisterEmailActivity",emailData);
                Intent intent = new Intent(RegisterEmailActivity.this, RegisterFavoriteActivity.class);
                startActivity(intent);
            }
        });
        binding.tvPron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSelectDialog<PronounDao> bottomSelectDialog = new BottomSelectDialog<>();
                bottomSelectDialog.transfor = pronounDao -> pronounDao.txt;
                bottomSelectDialog.title = "Choose Pronoun";
                bottomSelectDialog.data = pronounDaoList;
                bottomSelectDialog.callBack = new HuCallBack1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        bottomSelectDialog.dismiss();
                        selectPronPosition = integer;
                        onSelectPronPosition();
                    }
                };
                bottomSelectDialog.show(getSupportFragmentManager(), "");
            }
        });

        binding.tvBir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 17);
                SingleDateAndTimePickerDialog.Builder builder = new SingleDateAndTimePickerDialog.Builder(v.getContext())
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
        checkSubmit();
    }

    private void checkSubmit() {
        boolean enable = HUtils.isEmail(binding.editEmail.getText().toString());
        if (enable) {
            enable = !TextUtils.isEmpty(binding.editName.getText().toString());
        }
        if (enable) {
            enable = selectPronPosition != -1;
        }
        if (enable) {
            enable = selectDate != null;
        }
        binding.tvConfirm.setEnabled(enable);
    }

    private void onSelectDate() {
        checkSubmit();
        if (selectDate == null) {
            binding.tvBir.setText("");
            return;
        }
        binding.tvBir.setText(TimeUtils.date2String(selectDate, "MM-dd-yyyy"));

    }

    private void onSelectPronPosition() {
        checkSubmit();
        if (selectPronPosition == -1) {
            binding.tvPron.setText("");
            return;
        }
        binding.tvPron.setText(pronounDaoList.get(selectPronPosition).txt);

    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
