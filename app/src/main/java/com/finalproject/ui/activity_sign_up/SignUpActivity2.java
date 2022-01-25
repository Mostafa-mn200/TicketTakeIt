package com.finalproject.ui.activity_sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUp2Binding;
import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.activity_login.LoginActivity;

public class SignUpActivity2 extends AppCompatActivity {
    private ActivitySignUp2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up2);
        initView();
    }

    private void initView() {
        ProgressDialog dialog = new ProgressDialog(this);
        binding.llPrevious.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, SignUpActivity.class);
            startActivity(intent);
        });
        binding.llSignUp.setOnClickListener(view -> {
            dialog.setTitle(getString(R.string.signUp));
            dialog.setMessage(getString(R.string.waitLoading));
            dialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Intent intent = new Intent(SignUpActivity2.this, HomeActivity.class);
                    startActivity(intent);
                }
            }, 650);
        });
        binding.txtLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}