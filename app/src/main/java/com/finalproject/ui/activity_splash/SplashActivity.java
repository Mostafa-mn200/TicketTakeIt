package com.finalproject.ui.activity_splash;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySplashBinding;
import com.finalproject.language.Language;
import com.finalproject.preferences.Preferences;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_login.LoginActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.paperdb.Paper;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Preferences preferences;
    private String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        preferences = Preferences.getInstance();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        navigateToHomeActivity();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void navigateToHomeActivity() {
        Intent intent;
        if (preferences.getAppSetting(this) == null ) {
            intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {


            intent = new Intent(this, HomeActivity.class);

            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}