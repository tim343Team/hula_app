package com.hula.myapplication.app.firebase;

import android.net.Uri;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.UploadTask;
import com.hula.myapplication.widget.HuCallBack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class UpdateImageMuti {
    List<UploadTask> uploadTasks = new ArrayList<>();
    List<String> result = new ArrayList<>();
    private HuCallBack1<Exception> mFailureListener;
    private HuCallBack1<List<String>> mSuccessListener;
    private final Handler handler = new Handler();

    public UpdateImageMuti add(UploadTask uploadTask) {
        uploadTasks.add(uploadTask);
        int index = uploadTasks.indexOf(uploadTask);
        result.add("");
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> innerStack) throws Exception {
                if (!innerStack.isSuccessful()) {
                    throw Objects.requireNonNull(innerStack.getException());
                }
                return innerStack.getResult().getStorage().getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    String url = task.getResult().toString();
                    result.set(index, url);
                    if (checkComplete()) {
                        if (mSuccessListener != null) {
                            mSuccessListener.call(new ArrayList<>(result));
                        }
                    }
                } else {
                    handler.post(() -> {
                        if (mFailureListener != null) {
                            mFailureListener.call(task.getException());
                            mFailureListener = null;
                        }
                        uploadTasks.remove(uploadTask);
                        cancelAll();
                    });
                }
            }
        });
        return this;
    }

    public void cancelAll() {
        for (UploadTask uploadTask : uploadTasks) {
            uploadTask.cancel();
        }
    }

    private boolean checkComplete() {
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }


    public UpdateImageMuti setListener(HuCallBack1<List<String>> mSuccessListener, HuCallBack1<Exception> mFailureListener) {
        this.mSuccessListener = mSuccessListener;
        this.mFailureListener = mFailureListener;
        return this;
    }


    public void attach(Lifecycle lifecycle) {
        lifecycle.addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancelAll();
                    lifecycle.removeObserver(this);
                }
            }
        });
    }
}
