package com.finalproject.ui.user.activity_sign_up;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUp2Binding;
import com.finalproject.model.SignUpModel;
import com.finalproject.model.UserModel;
import com.finalproject.mvvm.ActivitySignupMvvm;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activities_owner_home.OwnerHomeActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.activity_login.LoginActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class SignUpActivity2 extends BaseActivity {
    private String lang;
    private ActivitySignUp2Binding binding;
    private ActivitySignupMvvm mvvm;
    private SignUpModel signUpModel;
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up2);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        signUpModel = (SignUpModel) intent.getSerializableExtra("data");
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

        mvvm = ViewModelProviders.of(this).get(ActivitySignupMvvm.class);
        binding.setModel(signUpModel);
        mvvm.getOnSignUpSuccess().observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                setUserModel(userModel);
                if (signUpModel.getType().equals("customer")){
                    navigateToUserHome();
                }else if (signUpModel.getType().equals("owner")){
                    navigateToOwnerHome();
                }
            }

            private void navigateToOwnerHome() {
                Intent intent=new Intent(SignUpActivity2.this,OwnerHomeActivity.class);
                startActivity(intent);
            }

            private void navigateToUserHome() {
                Intent intent=new Intent(SignUpActivity2.this,HomeActivity.class);
                startActivity(intent);
            }
        });




        binding.llPrevious.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });


        binding.llMale.setOnClickListener(view -> {
            binding.llMale.setBackgroundResource(R.drawable.small_stroke_color3);
            binding.llFemale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
            signUpModel.setGender("male");
//            binding.txtMale.setTextColor(getResources().getColor(R.color.black));
//            binding.txtFemale.setTextColor(getResources().getColor(R.color.white));
//            binding.iconMale.setColorFilter(getResources().getColor(R.color.color2));
//            binding.iconFemale.setColorFilter(getResources().getColor(R.color.white));
        });
        binding.llFemale.setOnClickListener(view -> {
            binding.llFemale.setBackgroundResource(R.drawable.small_stroke_color3);
            binding.llMale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
            signUpModel.setGender("female");
//                binding.txtFemale.setTextColor(getResources().getColor(R.color.black));
//                binding.txtMale.setTextColor(getResources().getColor(R.color.white));
//                binding.iconFemale.setColorFilter(getResources().getColor(R.color.color2));
//                binding.iconMale.setColorFilter(getResources().getColor(R.color.white));
        });
        binding.llSignUp.setOnClickListener(view -> {
            if (signUpModel.isDataValid2(this)) {
                if (type.equals("customer")){
                    mvvm.signupWith(this, signUpModel,"customer");
                }else if (type.equals("owner")){
                    mvvm.signupWith(this, signUpModel,"owner");
                }

            }
        });

        binding.txtLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
    public void setupbutton1() {
        type="customer";
        signUpModel.setType(type);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.black));
        binding.btnOwner.setTextColor(getResources().getColor(R.color.white));

    }

    public void setupbutton2() {
        type="owner";
        signUpModel.setType(type);
        binding.btnOwner.setBackgroundResource(R.drawable.bg_user_btn_clicked);
        binding.btnCustomer.setBackgroundResource(R.drawable.bg_user_btn);
        binding.btnOwner.setTextColor(getResources().getColor(R.color.black));
        binding.btnCustomer.setTextColor(getResources().getColor(R.color.white));


    }
}