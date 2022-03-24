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
    private static final String Tag="SignUpActivity";
    private SignUpModel signUpModel;


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

        signUpModel=new SignUpModel();
        binding.setModel(signUpModel);


        binding.llBack.setOnClickListener(view -> {
            Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btnNext.setOnClickListener(view -> {
            if (signUpModel.isDataValid1(this)) {
                Intent intent = new Intent(SignUpActivity.this, SignUpActivity2.class);
                //intent.putExtra("data", signUpModel);
                intent.putExtra("national_id",signUpModel.getNational_id());
                intent.putExtra("email",signUpModel.getEmail());
                intent.putExtra("user_name",signUpModel.getUser_name());

                startActivity(intent);
                finish();
            }
        });

//        binding.datePicker.setOnClickListener(view -> {
//            Calendar calendar=Calendar.getInstance();
//            int year=calendar.get(Calendar.YEAR);
//            int month=calendar.get(Calendar.MONTH);
//            int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog dialog=new DatePickerDialog(SignUpActivity.this,
//                    android.R.style.Theme_DeviceDefault_Dialog
//            ,dateSetListener,year,month,day);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
//            dialog.show();
//
//        });
//        dateSetListener= (datePicker, year, month, day) -> {
//            month=month+1;
//            Log.d(Tag,"date: "+ month + "/" + day + "/" + year );
//            String date =month + "/" + day + "/" + year;
//            binding.datePickerTxt.setText(date);
//            binding.datePickerTxt.setTextColor(getResources().getColor(R.color.white));
//        };
}



}