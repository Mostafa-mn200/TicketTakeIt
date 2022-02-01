package com.finalproject.ui.activity_sign_up;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.finalproject.R;
import com.finalproject.databinding.ActivitySignUpBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_login.LoginActivity;

import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

import io.paperdb.Paper;

public class SignUpActivity extends AppCompatActivity {
    private String lang;
    private ActivitySignUpBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String Tag="SignUpActivity";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);

        binding.llBack.setOnClickListener(view -> {
            Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.btnNext.setOnClickListener(view -> {
            Intent intent=new Intent(SignUpActivity.this,SignUpActivity2.class);
            startActivity(intent);
            finish();
        });

        binding.datePicker.setOnClickListener(view -> {
            Calendar calendar=Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog=new DatePickerDialog(SignUpActivity.this,
                    android.R.style.Theme_DeviceDefault_Dialog
            ,dateSetListener,year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            dialog.show();

        });
        dateSetListener= (datePicker, year, month, day) -> {
            month=month+1;
            Log.d(Tag,"date: "+ month + "/" + day + "/" + year );
            String date =month + "/" + day + "/" + year;
            binding.datePickerTxt.setText(date);
            binding.datePickerTxt.setTextColor(getResources().getColor(R.color.white));
        };
}

}