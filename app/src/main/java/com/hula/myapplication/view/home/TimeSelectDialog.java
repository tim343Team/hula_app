package com.hula.myapplication.view.home;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.hula.myapplication.R;
import com.hula.myapplication.databinding.DialogTimeSelectBinding;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.util.HUtils;
import com.hula.myapplication.widget.dialog.BaseBottomDialog;

import java.util.Date;

public class TimeSelectDialog extends BaseBottomDialog {
    private DialogTimeSelectBinding binding;
    private Date date1;
    private Long time1;
    private Date date2;
    private Long time2;
    private Date now = new Date();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogTimeSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.actionBar.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.tvDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date1, new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        date1 = date;
                        onSelect();
                    }
                });
            }
        });
        binding.tvDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date1, new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        date2 = date;
                        onSelect();
                    }
                });
            }
        });
        binding.tvTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(null, new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        //1663070100000
                        time1 = date.getTime() % (60000L * 60  * 24);
                        onSelect();
                    }
                });
            }
        });
        binding.tvTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(null, new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        time2 = date.getTime() % (60000L  * 60 * 24);
                        onSelect();
                    }
                });
            }
        });

    }


    private void onSelect() {
        if (date1 != null) {
            binding.tvDate1.setText(TimeUtils.date2String(date1, "MM-dd-yyyy"));
        }
        if (date2 != null) {
            binding.tvDate2.setText(TimeUtils.date2String(date2, "MM-dd-yyyy"));
        }

        if (time1 != null) {
            binding.tvTime1.setText(BusinessUtils.getStringTimeHHmm(time1));
        }

        if (time2!=null){
            binding.tvTime2.setText(BusinessUtils.getStringTimeHHmm(time2));
        }
    }


    private void showDateDialog(Date selectDate, SingleDateAndTimePickerDialog.Listener listener) {
        TimeDialogDelegateActivity.display(requireActivity(), new TimeDialogDelegateActivity.DialogFactory() {
            @Override
            public SingleDateAndTimePickerDialog factory(TimeDialogDelegateActivity activity) {
                SingleDateAndTimePickerDialog.Builder builder = new SingleDateAndTimePickerDialog.Builder(activity)
                        .backgroundColor(Color.WHITE)
                        .title("Select Date")
                        .displayHours(false)
                        .displayDays(false)
                        .displayMinutes(false)
                        .displayDaysOfMonth(true)
                        .displayMonth(true)
                        .displayYears(true);
                if (selectDate != null) {
                    builder.defaultDate(selectDate);
                }
                return builder.minDateRange(now)
                        .bottomSheet()
                        .mainColor(Color.parseColor("#8E73D3"))
                        .titleTextColor(Color.parseColor("#8E73D3"))
                        .build()
                        .setListener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                listener.onDateSelected(date);
                            }
                        });
            }
        });
    }

    private void showTimeDialog(Date time, SingleDateAndTimePickerDialog.Listener listener) {

        TimeDialogDelegateActivity.display(requireActivity(), new TimeDialogDelegateActivity.DialogFactory() {
            @Override
            public SingleDateAndTimePickerDialog factory(TimeDialogDelegateActivity activity) {
                SingleDateAndTimePickerDialog.Builder builder = new SingleDateAndTimePickerDialog.Builder(activity)
                        .backgroundColor(Color.WHITE)
                        .title("Select Time")
                        .displayHours(true)
                        .displayMinutes(true)
                        .displayDays(false)
                        .displayDaysOfMonth(false)
                        .displayMonth(false);
                if (time != null) {
                    builder.defaultDate(time);
                }
                return builder.bottomSheet()
                        .mainColor(Color.parseColor("#8E73D3"))
                        .titleTextColor(Color.parseColor("#8E73D3"))
                        .build()
                        .setListener(new SingleDateAndTimePickerDialog.Listener() {
                            @Override
                            public void onDateSelected(Date date) {
                                listener.onDateSelected(date);
                            }
                        });
            }
        });
    }

}
