package com.finalproject.mvvm;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.finalproject.R;
import com.finalproject.model.SignUpModel;
import com.finalproject.model.UserModel;
import com.finalproject.remote.Api;
import com.finalproject.share.Common;
import com.finalproject.tags.Tags;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ActivitySignupMvvm extends AndroidViewModel {
    private Context context;
    public MutableLiveData<UserModel> userModelMutableLiveData = new MutableLiveData<>();

    private CompositeDisposable disposable = new CompositeDisposable();

    public ActivitySignupMvvm(@NonNull @NotNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void signupWith(Context context, SignUpModel model) {
        ProgressDialog dialog = Common.createProgressDialog(context, context.getResources().getString(R.string.wait));
        dialog.setCancelable(false);
        dialog.show();

        Api.getService(Tags.base_url).signUp(model.getName(),
                model.getUser_name(), model.getPassword(), model.getNational_id(),
                model.getEmail(), model.getGender(), model.getType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<UserModel>>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NotNull Response<UserModel> userModelResponse) {
                        if (userModelResponse.isSuccessful()) {
                            if (userModelResponse.body().getStatus() == 200) {

                                userModelMutableLiveData.postValue(userModelResponse.body());
                            } else if (userModelResponse.body().getStatus() == 400) {
                                Toast.makeText(context, context.getResources().getString(R.string.user_found), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {

                    }
                });
    }
}
