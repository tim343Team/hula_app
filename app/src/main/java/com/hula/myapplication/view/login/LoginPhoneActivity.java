package com.hula.myapplication.view.login;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.NotificationUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.hula.myapplication.app.UrlFactory;
import com.hula.myapplication.app.net.GsonWalkDogCallBack;
import com.hula.myapplication.app.service.HService;
import com.hula.myapplication.app.service.PageDataHoldService;
import com.hula.myapplication.app.service.ServiceProfile;
import com.hula.myapplication.base.HBaseActivity;
import com.hula.myapplication.dao.LoginRemoteData;
import com.hula.myapplication.dao.RemoteData;
import com.hula.myapplication.dao.UserInfoData;
import com.hula.myapplication.databinding.ActivityLoginPhoneBinding;
import com.hula.myapplication.sp.SharedPrefsHelper;
import com.hula.myapplication.util.SimTextWatcher;
import com.hula.myapplication.view.HomeActivity;
import com.hula.myapplication.widget.VCEditText;
import com.hula.myapplication.widget.htoast.ToastUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import tim.com.libnetwork.network.okhttp.WonderfulOkhttpUtils;

public class LoginPhoneActivity extends HBaseActivity {
    private ActivityLoginPhoneBinding binding;
    String verificationId;
    PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private  FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth.getInstance().getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityLoginPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.editPhone.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                binding.tvSend.setEnabled(s.length() == 10);
            }
        });
        binding.editVc.addTextChangedListener(new SimTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if (binding.editPhone.getText().length() != 10 || forceResendingToken == null) {
                    return;
                }
                binding.tvConfirm.setEnabled(s.length() == 6);
            }
        });
        Runnable send = new Runnable() {
            @Override
            public void run() {
                ToastUtil.showLoading("");
                sendCode("+1" + binding.editPhone.getText().toString(), new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        ToastUtil.hideLoading();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationId, forceResendingToken);
                        ToastUtil.hideLoading();
                        LoginPhoneActivity.this.verificationId = verificationId;
                        LoginPhoneActivity.this.forceResendingToken = forceResendingToken;
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        ToastUtil.showFailToast("Send code fail");
                        ToastUtil.hideLoading();
                    }
                });
            }
        };
        binding.tvSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.tvPhoneHint.setText("Code sent to +1" + binding.editPhone.getText().toString());
                send.run();
            }
        });
        binding.tvResend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                binding.tvPhoneHint.setText("Code sent to +1" + binding.editPhone.getText().toString());
                send.run();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vcCode = binding.editVc.getText().toString();
                if (vcCode.isEmpty()) {
                    ToastUtil.showToast("Please input code");
                    return;
                }
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, vcCode);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void sendCode(String phoneNumber, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(0L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        ToastUtil.showLoading("");
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        ToastUtil.hideLoading();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            if (user == null) {
                                return;
                            }
                            Map<String, Object> params = new HashMap<>();
                            params.put("phone_number", user.getPhoneNumber());
                            params.put("phone_verified", true);
                            params.put("uid", user.getUid());
                            params.put("id_token", user.getIdToken(false));
                            params.put("linked_accounts", "phone");
                            WonderfulOkhttpUtils.postJson()
                                    .url(UrlFactory.login())
                                    .addHeader("Content-Type", "application/json")
                                    .body(GsonUtils.toJson(params))
                                    .build()
                                    .getCall()
                                    .bindLifecycle(LoginPhoneActivity.this)
                                    .enqueue(new GsonWalkDogCallBack<LoginRemoteData>() {
                                        @Override
                                        protected void onRes(LoginRemoteData data) throws Exception {
                                            SharedPrefsHelper.getInstance()
                                                    .saveToken(data.getToken());
                                            HService.getService(ServiceProfile.class)
                                                    .updateUserInfo(data.getNotNullData());
                                            startNextPage();
                                        }
                                    });
                        } else {
                            ToastUtil.showFailToast("The verification code entered was invalid");
                        }
                    }
                });
    }

    private void startNextPage() {
        if (RegisterNextPageHelp.replenishProfileOnReigster(this,1,true)){
            HomeActivity.start(this);
        }
    }

    @Override
    protected int statusBarStyle() {
        return STATUSBAR_TRANSLUCENT_STYLE | STATUSBAR_DARK;
    }
}
