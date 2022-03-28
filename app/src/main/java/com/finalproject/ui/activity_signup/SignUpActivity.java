package com.finalproject.ui.activity_signup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUpBinding;
import com.finalproject.model.SignUpModel;
import com.finalproject.mvvm.ActivitySignupMvvm;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_login.LoginActivity;
import com.finalproject.ui.owner.activities_owner_home.OwnerHomeActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends BaseActivity {
    private ActivitySignUpBinding binding;
    private SignUpModel signUpModel;
    private String type = "";
    private String gender = "";
    private ActivitySignupMvvm mvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
        mvvm = ViewModelProviders.of(this).get(ActivitySignupMvvm.class);
        binding.setLang(getLang());
        binding.btnCustomer.setOnClickListener(view -> {
            setupButton1();
        });
        binding.btnOwner.setOnClickListener(view -> {
            setupButton2();
        });
        binding.llMale.setOnClickListener(view -> {
            setupMale();
        });
        binding.llFemale.setOnClickListener(view -> {
            setupFemale();
        });
        signUpModel = new SignUpModel();
        binding.setModel(signUpModel);
        signUpModel.setType(type);
        signUpModel.setGender(gender);

        binding.llBack.setOnClickListener(view -> {
            finish();
        });

        mvvm.getOnSignUpSuccess().observe(this, userModel -> {
            setUserModel(userModel);
            if (signUpModel.getType().equals("customer")) {
                navigateToUserHome();
            } else if (signUpModel.getType().equals("owner")) {
                navigateToOwnerHome();
            }
        });
        binding.llSignUp.setOnClickListener(view -> {
            Log.e("data",signUpModel.getType()+" "+ signUpModel.getNational_id()+" "+signUpModel.getName()+" "+signUpModel.getEmail()+" "+signUpModel.getUser_name()+" "+signUpModel.getPassword()+" "+signUpModel.getGender());
            if (signUpModel.isDataValid(this)) {
                mvvm.signupWith(this, signUpModel);
            } else {
                Toast.makeText(this, "Please fill inputs", Toast.LENGTH_SHORT).show();
            }
        });
        binding.txtLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }



    private void setupMale() {
        binding.llMale.setBackgroundResource(R.drawable.small_stroke_color3);
        binding.llFemale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
        gender = "male";
        signUpModel.setGender(gender);
    }

    private void setupFemale() {
        binding.llFemale.setBackgroundResource(R.drawable.small_stroke_color3);
        binding.llMale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
        gender = "female";
        signUpModel.setGender(gender);
    }
    public void setupButton1() {
        type = "customer";
        signUpModel.setType(type);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
        binding.btnOwner.setTextColor(getResources().getColor(R.color.white));

    }

    public void setupButton2() {
        type = "owner";
        signUpModel.setType(type);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));


    }

    private void navigateToOwnerHome() {
        Intent intent = new Intent(this, OwnerHomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToUserHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}