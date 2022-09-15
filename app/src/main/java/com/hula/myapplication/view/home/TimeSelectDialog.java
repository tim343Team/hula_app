package com.hula.myapplication.view.home;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.TimeUtils;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.hula.myapplication.databinding.DialogTimeSelectBinding;
import com.hula.myapplication.util.BusinessUtils;
import com.hula.myapplication.view.home.vm.AddNewEventVM;
import com.hula.myapplication.widget.dialog.BaseBottomDialog;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.Calendar;
import java.util.Date;

public class TimeSelectDialog extends BaseBottomDialog {
    private DialogTimeSelectBinding binding;
    private Date date1;
    private Long time1;
    private Date date2;
    private Long time2;
    private final Date now = new Date();
    private AddNewEventVM addNewEventVM;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addNewEventVM = new ViewModelProvider(requireActivity()).get(AddNewEventVM.class);
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
                showDateDialog(date1, now, new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date) {
                        date1 = date;
                        date2 = null;
                        time2 = null;
                        onSelect();
                    }
                });
            }
        });
        binding.tvDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date2, date1, new SingleDateAndTimePickerDialog.Listener() {
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
                        time1 = BusinessUtils.getHHmmTime(date);
                        date2 = null;
                        time2 = null;
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
                        time2 = BusinessUtils.getHHmmTime(date);
                        onSelect();
                    }
                });
            }
        });

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = getTime(date1, time1);
                long endTime = getTime(date2, time2);
                if (endTime <= startTime) {
                    ToastUtil.showFailToast("The end time must be after the start time");
                    return;
                }
                dismiss();
                addNewEventVM.timeLD.setValue(new Long[]{startTime, endTime});
            }
        });


    }

    private long getTime(Date date, long time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int minute = (int) (time / 60_000);
        instance.set(Calendar.HOUR_OF_DAY, minute / 60);
        instance.set(Calendar.MINUTE, minute % 60);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTimeInMillis();
    }


    private void onSelect() {
        if (date1 != null) {
            binding.tvDate1.setText(TimeUtils.date2String(date1, "MM-dd-yyyy"));
        } else {
            binding.tvDate1.setText("");
        }
        if (date2 != null) {
            binding.tvDate2.setText(TimeUtils.date2String(date2, "MM-dd-yyyy"));
        } else {
            binding.tvDate2.setText("");
        }

        if (time1 != null) {
            binding.tvTime1.setText(BusinessUtils.getStringTimeHHmm(time1));
        } else {
            binding.tvTime1.setText("");
        }

        if (time2 != null) {
            binding.tvTime2.setText(BusinessUtils.getStringTimeHHmm(time2));
        } else {
            binding.tvTime2.setText("");
        }
        int visible = View.INVISIBLE;
        if (date1 != null && time1 != null && date2 != null && time2 != null) {
            visible = View.VISIBLE;
        }
        binding.menu.setVisibility(visible);
    }


    private void showDateDialog(Date selectDate, Date mindate, SingleDateAndTimePickerDialog.Listener listener) {
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
                return builder.minDateRange(mindate)
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
