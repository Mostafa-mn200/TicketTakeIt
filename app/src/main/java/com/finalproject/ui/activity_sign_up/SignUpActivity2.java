package com.finalproject.ui.activity_sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUp2Binding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.activity_login.LoginActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class SignUpActivity2 extends AppCompatActivity {
    private String lang;
    private ActivitySignUp2Binding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up2);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        ProgressDialog dialog = new ProgressDialog(this);
        binding.llPrevious.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        binding.llMale.setOnClickListener(view -> {
            binding.llMale.setBackgroundResource(R.drawable.small_stroke_color2);
            binding.llFemale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
//            binding.txtMale.setTextColor(getResources().getColor(R.color.black));
//            binding.txtFemale.setTextColor(getResources().getColor(R.color.white));
//            binding.iconMale.setColorFilter(getResources().getColor(R.color.color2));
//            binding.iconFemale.setColorFilter(getResources().getColor(R.color.white));
        });
        binding.llFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.llFemale.setBackgroundResource(R.drawable.small_stroke_color2);
                binding.llMale.setBackgroundResource(R.drawable.bg_user_btn_not_clicked);
//                binding.txtFemale.setTextColor(getResources().getColor(R.color.black));
//                binding.txtMale.setTextColor(getResources().getColor(R.color.white));
//                binding.iconFemale.setColorFilter(getResources().getColor(R.color.color2));
//                binding.iconMale.setColorFilter(getResources().getColor(R.color.white));
            }
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
                    finish();
                }
            }, 650);

        });
        binding.txtLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}