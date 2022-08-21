package com.hula.myapplication.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hula.myapplication.R;
import com.hula.myapplication.view.home.HomeFragment;
import com.hula.myapplication.view.invite.InviteFragment;
import com.hula.myapplication.view.message.MessageFragment;
import com.hula.myapplication.view.mine.MineFragment;
import com.hula.myapplication.view.search.SearchFragment;

import butterknife.BindView;
import tim.com.libnetwork.base.BaseTransFragmentActivity;

public class HomeActivity extends BaseTransFragmentActivity {
    public static HomeActivity instance = null;
    @BindView(R.id.flContainer)
    FrameLayout flContainer;
    @BindView(R.id.llOne)
    LinearLayout llOne;
    @BindView(R.id.llTwo)
    LinearLayout llTwo;
    @BindView(R.id.llThree)
    LinearLayout llThree;
    @BindView(R.id.llFour)
    LinearLayout llFour;
    @BindView(R.id.llFive)
    LinearLayout llFive;

    /*相机，相册，sdcard读写权限请求*/
    private final static int PERMISSION_REQUEST_CODE = 1234;
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };
    private LinearLayout[] lls;
    private int currentPage;
    private HomeFragment oneFragment;
    private SearchFragment twoFragment;
    private MessageFragment threeFragment;
    private InviteFragment fourFragment;
    private MineFragment fiveFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void recoverFragment() {
        oneFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        twoFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(SearchFragment.TAG);
        threeFragment = (MessageFragment) getSupportFragmentManager().findFragmentByTag(MessageFragment.TAG);
        fourFragment = (InviteFragment) getSupportFragmentManager().findFragmentByTag(InviteFragment.TAG);
        fiveFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MineFragment.TAG);

        if (oneFragment == null) {
            fragments.add(oneFragment = new HomeFragment());
        } else {
            fragments.add(oneFragment);
        }
        if (twoFragment == null) {
            fragments.add(twoFragment = new SearchFragment());
        } else {
            fragments.add(twoFragment);
        }
        if (threeFragment == null) {
            fragments.add(threeFragment = new MessageFragment());
        } else {
            fragments.add(threeFragment);
        }
        if (fourFragment == null) {
            fragments.add(fourFragment = new InviteFragment());
        } else {
            fragments.add(fourFragment);
        }
        if (fiveFragment == null) {
            fragments.add(fiveFragment = new MineFragment());
        } else {
            fragments.add(fiveFragment);
        }
    }

    @Override
    protected void initFragments() {
        if (oneFragment == null) {
            fragments.add(oneFragment = new HomeFragment());
        }
        if (twoFragment == null) {
            fragments.add(twoFragment = new SearchFragment());
        }
        if (threeFragment == null) {
            fragments.add(threeFragment = new MessageFragment());
        }
        if (fourFragment == null) {
            fragments.add(fourFragment = new InviteFragment());
        }
        if (fiveFragment == null) {
            fragments.add(fiveFragment = new MineFragment());
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        int page = savedInstanceState.getInt("page");
        selecte(lls[page], page);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activiy_home;
    }

    @Override
    protected View getActivityLayoutView() {
        return null;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        instance = this;
        lls = new LinearLayout[]{llOne, llTwo, llThree, llFour,llFive};
        llOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 0);
            }
        });
        llTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 1);
            }
        });
        llThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 2);
            }
        });
        llFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 3);
            }
        });
        llFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecte(v, 4);
            }
        });
        if (fragments.size() == 0) {
            recoverFragment();
        }
        if (savedInstanceState == null) {
            hideFragment(oneFragment);
            selecte(llOne, 0);
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(permissions[1]) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(permissions[2]) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(permissions[3]) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    protected void loadData() {

    }

    //权限反馈
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                /*PackageManager.PERMISSION_GRANTED  权限被许可*/
                /*PackageManager.PERMISSION_DENIED  没有权限；拒绝访问*/
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HomeActivity.this, R.string.permissions_sdcard, Toast.LENGTH_SHORT).show();
                } else if (grantResults.length > 0 && grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HomeActivity.this, R.string.permissions_sdcard, Toast.LENGTH_SHORT).show();
                } else if (grantResults.length > 0 && grantResults[2] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HomeActivity.this, R.string.permissions_camera, Toast.LENGTH_SHORT).show();
                } else if (grantResults.length > 0 && grantResults[3] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(HomeActivity.this, R.string.permissions_audio, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void selecte(View v, int page) {
        currentPage = page;
        llOne.setSelected(false);
        llTwo.setSelected(false);
        llThree.setSelected(false);
        llFour.setSelected(false);
        llFive.setSelected(false);
        v.setSelected(true);

        for (int i = 0; i < fragments.size(); i++) {
            fragments.get(i).onHiddenChanged(true);
        }
        fragments.get(page).onHiddenChanged(false);
        showFragment(fragments.get(page));
    }
}
