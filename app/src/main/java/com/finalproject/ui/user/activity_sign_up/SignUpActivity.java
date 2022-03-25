package com.finalproject.ui.user.activity_sign_up;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUpBinding;
import com.finalproject.model.SignUpModel;
import com.finalproject.model.UserModel;
import com.finalproject.mvvm.ActivitySignupMvvm;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_login.LoginActivity;

import java.util.Calendar;
import java.util.Locale;

import io.paperdb.Paper;

public class SignUpActivity extends BaseActivity {
    private String lang;
    private ActivitySignUpBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String Tag = "SignUpActivity";
    private SignUpModel signUpModel;
    private UserModel userModel;
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        initView();
    }


    private void initView() {
        Paper.init(this);
        lang = getLang();
        binding.setLang(getLang());


        binding.btnCustomer.setOnClickListener(view -> {
            setupbutton1();
        });
        binding.btnOwner.setOnClickListener(view -> {
            setupbutton2();
        });
        userModel = new UserModel();
        signUpModel = new SignUpModel();
        binding.setModel(signUpModel);
        signUpModel.setType(type);
        Log.e("type",type);
        binding.llBack.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btnNext.setOnClickListener(view -> {
            if (signUpModel.isDataValid1(this)) {
                Intent intent = new Intent(SignUpActivity.this, SignUpActivity2.class);
                intent.putExtra("data", signUpModel);
                startActivity(intent);

            }
        });

    }

    public void setupbutton1() {
        type = "customer";
        signUpModel.setType(type);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
        binding.btnOwner.setTextColor(getResources().getColor(R.color.white));

    }

    public void setupbutton2() {
        type = "owner";
        signUpModel.setType(type);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));


    }

}