package com.finalproject.ui.user.activity_verification_code;

import androidx.activity.result.ActivityResultLauncher;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivityVerificationCodeBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class VerificationCodeActivity extends BaseActivity {



    private ActivityVerificationCodeBinding binding;
    private String phone_code = "";
    private String phone = "";
    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_verification_code);

        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        phone_code = intent.getStringExtra("phone_code");
        phone = intent.getStringExtra("phone");
    }

    private void initView()
    {
//        lang = getLang();
//        binding.setLang(lang);
        binding.phone.setText(phone_code+" "+phone);
    }
}