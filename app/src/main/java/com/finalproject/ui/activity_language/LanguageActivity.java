package com.finalproject.ui.activity_language;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.databinding.ActivityLanguageBinding;
import com.finalproject.language.Language;
import com.finalproject.preferences.Preferences;
import com.finalproject.ui.activity_home.HomeActivity;
import com.finalproject.ui.activity_splash.SplashActivity;

import io.paperdb.Paper;

public class LanguageActivity extends AppCompatActivity {
    private ActivityLanguageBinding binding;
    private String lang = "";
    private String selectedLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language);
        intView();
    }

    @SuppressLint("ResourceAsColor")
    private void intView() {
        ProgressDialog dialog = new ProgressDialog(this);
        lang = Language.getLanguageSelected(this);//where u get the lang saved in shared pref
        //decorate the saved language
        if (lang.equals("en")) {
            binding.flEn.setBackgroundResource(R.drawable.small_stroke_primary);
            binding.enTxt.setTextColor(R.color.black);
            binding.arTxt.setTextColor(R.color.white);
            binding.flAr.setBackgroundResource(0);
        } else if (lang.equals("ar")) {
            binding.flAr.setBackgroundResource(R.drawable.small_stroke_primary);
            binding.arTxt.setTextColor(R.color.black);
            binding.enTxt.setTextColor(R.color.white);
            binding.flEn.setBackgroundResource(0);
        } else {
            Toast.makeText(this, "no selected language", Toast.LENGTH_SHORT).show();
        }

        binding.cardAr.setOnClickListener(view -> {
            selectedLang = "ar";
            if (!selectedLang.equals(lang)) {
                binding.llNext.setVisibility(View.VISIBLE);
            } else {
                binding.llNext.setVisibility(View.INVISIBLE);
            }

            binding.flAr.setBackgroundResource(R.drawable.small_stroke_primary);
            binding.arTxt.setTextColor(R.color.black);
            binding.enTxt.setTextColor(R.color.white);
            binding.flEn.setBackgroundResource(0);
        });
        binding.cardEn.setOnClickListener(view -> {
            selectedLang = "en";
            if (!selectedLang.equals(lang)) {
                binding.llNext.setVisibility(View.VISIBLE);
            } else {
                binding.llNext.setVisibility(View.INVISIBLE);
            }
            binding.flEn.setBackgroundResource(R.drawable.small_stroke_primary);
            binding.enTxt.setTextColor(R.color.black);
            binding.arTxt.setTextColor(R.color.white);
            binding.flAr.setBackgroundResource(0);
        });
        binding.llCancel.setOnClickListener(view -> finish());
        binding.llNext.setOnClickListener(view -> {
            dialog.setMessage(getString(R.string.waitLoading));
            dialog.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Language.setNewLocale(LanguageActivity.this, selectedLang);

                    Intent intent = new Intent(LanguageActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }, 500);
            finish();

        });

    }
}
