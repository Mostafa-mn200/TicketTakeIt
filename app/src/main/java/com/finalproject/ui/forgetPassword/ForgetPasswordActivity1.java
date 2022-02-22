package com.finalproject.ui.forgetPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivityForgetPassword1Binding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_login.LoginActivity;
import com.finalproject.ui.user.activity_sign_up.SignUpActivity;
import com.finalproject.ui.user.activity_sign_up.SignUpActivity2;

import java.util.Locale;

import io.paperdb.Paper;

public class ForgetPasswordActivity1 extends BaseActivity {
    ActivityForgetPassword1Binding binding;
    private String lang;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String Tag="ForgetPasswordActivity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password1);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        binding.llBack.setOnClickListener(view -> {
            Intent intent=new Intent(ForgetPasswordActivity1.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnNext.setOnClickListener(view -> {
            Intent intent=new Intent(ForgetPasswordActivity1.this, ForgetPassActivity2.class);
            startActivity(intent);
            finish();
        });
    }
}