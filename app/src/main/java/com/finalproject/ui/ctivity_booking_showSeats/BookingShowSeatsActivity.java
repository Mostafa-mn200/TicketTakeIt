package com.finalproject.ui.ctivity_booking_showSeats;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.databinding.ActivityBookingShowSeatsBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.activity_show_detiles.ShowDetilesActivity;
import com.finalproject.ui.fragment_payment.PaymentMetodFragment;

import java.util.Locale;

import io.paperdb.Paper;

public class BookingShowSeatsActivity extends BaseActivity {
    ActivityBookingShowSeatsBinding binding;
    String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_show_seats);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_booking_show_seats);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        binding.ChoosePaymentBtnBSh.setOnClickListener(view -> {
            PaymentMetodFragment fragment=new PaymentMetodFragment();
            fragment.show(getSupportFragmentManager(),"");
        });

        binding.llBack.setOnClickListener(view -> {
            Intent i=new Intent(BookingShowSeatsActivity.this, ShowDetilesActivity.class);
            startActivity(i);
            finish();
        });
    }
}