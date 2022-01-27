package com.finalproject.ui.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;


import com.finalproject.R;
import com.finalproject.databinding.ActivityLoginBinding;
import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.activity_sign_up.SignUpActivity;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private boolean passVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        ProgressDialog dialog = new ProgressDialog(this);

        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));

        binding.btnCustomer.setOnClickListener(view -> {
            binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
            binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
            binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
            binding.btnOwner.setTextColor(getResources().getColor(R.color.white));
        });
        binding.btnOwner.setOnClickListener(view -> {
            binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
            binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
            binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
            binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));
        });
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