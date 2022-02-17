package com.finalproject.ui.activity_show_detiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.finalproject.R;
import com.finalproject.adapter.CastAdapter;
import com.finalproject.adapter.CastShowAdapter;
import com.finalproject.databinding.ActivityShowDetilesBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_booking_seats.BookingSeasActivity;
import com.finalproject.ui.ctivity_booking_showSeats.BookingShowSeatsActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class ShowDetilesActivity extends AppCompatActivity {
    ActivityShowDetilesBinding binding;
    CastShowAdapter mcastShowAdapter;
    String lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detiles);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);

        binding.llBack.setOnClickListener(view -> finish());
        mcastShowAdapter = new CastShowAdapter(this);
        binding.castRecHoriSD.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.castRecHoriSD.setAdapter(mcastShowAdapter);

        binding.ChooseSeatBtnSD.setOnClickListener(view -> {
            Intent i = new Intent(ShowDetilesActivity.this, BookingSeasActivity.class);
            startActivity(i);
        });
    }
}