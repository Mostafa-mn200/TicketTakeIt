package com.finalproject.ui.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import com.finalproject.R;
import com.finalproject.databinding.ActivityLoginBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.activity_sign_up.SignUpActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private String lang;
    private ActivityLoginBinding binding;
    private boolean passVisible;


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
        binding.setLang(lang);


        BtnsOnClick();
    }

    private void BtnsOnClick() {
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));

        binding.btnCustomer.setOnClickListener(view -> {
            binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
            binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
            binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
            binding.btnOwner.setTextColor(getResources().getColor(R.color.white));
            binding.ownerIDLayout.setVisibility(View.GONE);
            binding.UserNameLayout.setVisibility(View.VISIBLE);
        });
        binding.btnOwner.setOnClickListener(view -> {
            binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
            binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn);
            binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
            binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));
            binding.ownerIDLayout.setVisibility(View.VISIBLE);
            binding.UserNameLayout.setVisibility(View.GONE);
        });

        ProgressDialog dialog = new ProgressDialog(this);
        binding.btnLogin.setOnClickListener(view -> {
            dialog.setTitle(getString(R.string.login));
            dialog.setMessage(getString(R.string.waitLoading));
            dialog.show();
            new Handler().postDelayed(() -> {
                dialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }, 500);

        });
        binding.txtCreate.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }
}