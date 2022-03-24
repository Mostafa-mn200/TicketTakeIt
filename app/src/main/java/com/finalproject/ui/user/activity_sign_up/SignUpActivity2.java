package com.finalproject.ui.user.activity_sign_up;

import androidx.databinding.DataBindingUtil;
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
import com.finalproject.mvvm.ActivitySignupMvvm;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.activity_login.LoginActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class SignUpActivity2 extends BaseActivity {
    private String lang;
    private ActivitySignUp2Binding binding;
    private ActivitySignupMvvm mvvm;
    private SignUpModel signUpModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up2);
        initView();
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        intent.getSerializableExtra("national_id");
    }

    private void initView() {
        Paper.init(this);
        lang = getLang();
        binding.setLang(getLang());
        ProgressDialog dialog = new ProgressDialog(this);

        mvvm= ViewModelProviders.of(this).get(ActivitySignupMvvm.class);
        signUpModel=new SignUpModel();
        binding.setModel(signUpModel);
        mvvm.userModelMutableLiveData.observe(this, userModel -> {
            setUserModel(userModel);
        });




        binding.llPrevious.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });


        binding.llMale.setOnClickListener(view -> {
            binding.llMale.setBackgroundResource(R.drawable.small_stroke_color3);

            binding.llFemale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
//            binding.txtMale.setTextColor(getResources().getColor(R.color.black));
//            binding.txtFemale.setTextColor(getResources().getColor(R.color.white));
//            binding.iconMale.setColorFilter(getResources().getColor(R.color.color2));
//            binding.iconFemale.setColorFilter(getResources().getColor(R.color.white));
        });
        binding.llFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.llFemale.setBackgroundResource(R.drawable.small_stroke_color3);
                binding.llMale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
//                binding.txtFemale.setTextColor(getResources().getColor(R.color.black));
//                binding.txtMale.setTextColor(getResources().getColor(R.color.white));
//                binding.iconFemale.setColorFilter(getResources().getColor(R.color.color2));
//                binding.iconMale.setColorFilter(getResources().getColor(R.color.white));
            }
        });
        binding.llSignUp.setOnClickListener(view -> {
                if (signUpModel.isDataValid2(this)){
                    mvvm.signupWith(this,signUpModel);
                }
        });

        binding.txtLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}