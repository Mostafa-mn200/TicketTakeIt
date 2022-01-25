package com.finalproject.ui.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

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
        binding.btnLogin.setOnClickListener(view -> {
            dialog.setTitle(getString(R.string.login));
            dialog.setMessage(getString(R.string.waitLoading));
            dialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            },500);

        });
        binding.txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
    }

}