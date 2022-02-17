package com.finalproject.ui.activity_booking_seats;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.finalproject.R;
import com.finalproject.databinding.ActivityBookingSeasBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_cinema_users.CinemasUserActivity;
import com.finalproject.ui.fragment_payment.PaymentMetodFragment;

import java.util.Locale;

import io.paperdb.Paper;

public class BookingSeasActivity extends BaseActivity {
    private String lang;
    ActivityBookingSeasBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking_seas);
        initView();


    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        binding.ChoosePaymentBtnBS.setOnClickListener(view -> {
            PaymentMetodFragment fragment=new PaymentMetodFragment();
            fragment.show(getSupportFragmentManager(),"");
        });

        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(BookingSeasActivity.this, CinemasUserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}