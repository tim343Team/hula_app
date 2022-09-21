package com.hula.myapplication.view.mine.profile.sub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hula.myapplication.adapter.SchoolDaoAdapter;
import com.hula.myapplication.app.Injection;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.bus_event.UpdateSchoolEvent;
import com.hula.myapplication.dao.ProfileTagDao;
import com.hula.myapplication.dao.SchoolDao;
import com.hula.myapplication.databinding.ActivitySelectSchoolBinding;
import com.hula.myapplication.databinding.ActivitySettingWorkBinding;
import com.hula.myapplication.request.AddSchoolParameter;
import com.hula.myapplication.request.GetSchoolParameter;
import com.hula.myapplication.widget.ColorItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import tim.com.libnetwork.base.BaseActivity;

public class SelectSchoolActivity extends BaseActivity implements ProfileSettingContract.ProfileSettingView{
    private ActivitySelectSchoolBinding binding;
    private EditText editSchool;
    private RecyclerView recyclerView;
    private SchoolDaoAdapter adapter;
    private List<SchoolDao> data = new ArrayList<>();
    private ProfileSettingContract.ProfileSettingPresenter presenter;
    private int pageNo=0;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, SelectSchoolActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return 0;
    }

    @Override
    protected View getActivityLayoutView() {
        binding = ActivitySelectSchoolBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        presenter = new ProfileSettingPresenter(Injection.provideTasksRepository(getApplicationContext()), this);//初始化presenter
        recyclerView = binding.recyclerView;
        editSchool = binding.editSchool;
        binding.tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editSchool.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //执行对应的操作
                    if(editSchool.getText().toString().isEmpty()){
                        return true;
                    }
                    //添加学校接口
                    ServiceProfile service = HService.getService(ServiceProfile.class);
                    presenter.addSchool(new AddSchoolParameter(editSchool.getText().toString(),service.getUserId()));
                    return true;
                }
                return false;
            }
        });
        adapter = new SchoolDaoAdapter(data);
        recyclerView.addItemDecoration(new ColorItemDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, binding.recyclerView);
        adapter.OnclickListenerItem(new SchoolDaoAdapter.OnclickListenerItem() {
            @Override
            public void click(int position) {
                EventBus.getDefault().post(new UpdateSchoolEvent(data.get(position)));
                finish();
            }
        });
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        ServiceProfile service = HService.getService(ServiceProfile.class);
        presenter.getSchoolList(new GetSchoolParameter(pageNo,service.getUserId()));
    }

    private void refresh() {
        adapter.setEnableLoadMore(true);
        adapter.loadMoreEnd(false);
        pageNo = 0;
        ServiceProfile service = HService.getService(ServiceProfile.class);
        presenter.getSchoolList(new GetSchoolParameter(pageNo,service.getUserId()));
    }

    private void loadMore() {
//        refreshLayout.setEnabled(false);
        pageNo = pageNo + 1;
        ServiceProfile service = HService.getService(ServiceProfile.class);
        presenter.getSchoolList(new GetSchoolParameter(pageNo,service.getUserId()));
    }

    private void loadData(List<SchoolDao> data) {
        adapter.loadMoreComplete();
//        if (refreshLayout == null) {
//            return;
//        }
//        refreshLayout.setEnabled(true);
//        refreshLayout.setRefreshing(false);
        if (data == null || data.size() == 0) {
            if (pageNo == 0) {
                this.data.clear();
                adapter.notifyDataSetChanged();
            }
            return;
        }
        if (pageNo == 0) {
            this.data.clear();
            this.data.addAll(data);
        } else {
            this.data.addAll(data);
        }
        if (data.size() < 20) {
            adapter.loadMoreEnd();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getFail(Integer code, String toastMessage) {

    }

    @Override
    public void getProfileSuccess(List<ProfileTagDao> daos) {

    }

    @Override
    public void getSchoolSuccess(List<SchoolDao> daos) {
        loadData(daos);
    }

    @Override
    public void addSchoolSuccess(SchoolDao daos) {
        EventBus.getDefault().post(new UpdateSchoolEvent(daos));
        finish();
    }

    @Override
    public void setPresenter(ProfileSettingContract.ProfileSettingPresenter presenter) {
        this.presenter=presenter;
    }
}
