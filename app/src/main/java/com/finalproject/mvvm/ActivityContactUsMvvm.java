package com.finalproject.mvvm;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.finalproject.R;
import com.finalproject.model.ContactUsModel;
import com.finalproject.model.LoginModel;
import com.finalproject.model.StatusResponse;
import com.finalproject.model.UserModel;
import com.finalproject.remote.Api;
import com.finalproject.share.Common;
import com.finalproject.tags.Tags;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ActivityContactUsMvvm extends AndroidViewModel {
    private Context context;

    public MutableLiveData<StatusResponse> contactUsSuccess = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ActivityContactUsMvvm(@NonNull @NotNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoading==null){
            isLoading=new MutableLiveData<>();
        }
        return isLoading;
    }

    public void contactWithUs(Context context, ContactUsModel contactUsModel) {
        isLoading.setValue(true);
        ProgressDialog dialog = Common.createProgressDialog(context, context.getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();
        Log.e("data", contactUsModel.getName() + "_" + contactUsModel.getPhone() + "_" + contactUsModel.getMessage() + "");
        Api.getService(Tags.base_url).ContactUs(contactUsModel.getName(),contactUsModel.getPhone(), contactUsModel.getMessage())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<StatusResponse>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NotNull Response<StatusResponse> response) {
                        dialog.dismiss();
                        Log.e("ssss", response.code() + "_");
                        isLoading.setValue(false);
                        if (response.isSuccessful() && response.body() != null) {

                            if (response.body().getStatus() == 200) {
                                contactUsSuccess.setValue(response.body());
                            }
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }
                });
    }
}
