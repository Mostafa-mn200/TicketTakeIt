package com.finalproject.ui.activity_login;

import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


import com.finalproject.R;
import com.finalproject.databinding.ActivityLoginBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.forgetPassword.ForgetPasswordActivity1;
import com.finalproject.ui.owner.activity_owner_home.OwnerHomeActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_sign_up.SignUpActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class LoginActivity extends BaseActivity {
    private String lang;
    private ActivityLoginBinding binding;
    private boolean passVisible;
    private String type="";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());
//        setupbutton1();
        binding.btnCustomer.setOnClickListener(view -> {
            setupbutton1();
        });
        binding.btnOwner.setOnClickListener(view -> {
            setupbutton2();
        });
        binding.btnLogin.setOnClickListener(view -> {
                if (type.equals("customer")) {

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else if (type.equals("owner")) {
                    Intent intent = new Intent(LoginActivity.this, OwnerHomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "Please choose the user (Customer or Owner)", Toast.LENGTH_SHORT).show();
                }


        });
        binding.txtCreate.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        binding.forgetPass.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity1.class);
            startActivity(intent);
            finish();
        });

    }

    public void setupbutton1() {
        type="customer";
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
        binding.btnOwner.setTextColor(getResources().getColor(R.color.white));

    }

    public void setupbutton2() {
        type="owner";
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));


    }

}