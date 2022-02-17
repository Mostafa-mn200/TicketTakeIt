package com.finalproject.ui.activity_splash;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySplashBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;
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
    }
}