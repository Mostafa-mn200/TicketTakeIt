package com.finalproject.ui.user.activity_show_detiles;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.CastShowAdapter;
import com.finalproject.databinding.ActivityShowDetilesBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_booking_seats.BookingSeatsActivity;
import com.finalproject.ui.user.activity_cinema_users.CinemasUserActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class ShowDetilesActivity extends BaseActivity {
    ActivityShowDetilesBinding binding;
    CastShowAdapter castShowAdapter;
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
        binding.setLang(getLang());

        //binding.llBack
        binding.toolbar.llBack.setOnClickListener(view -> finish());
        castShowAdapter = new CastShowAdapter(this);
        binding.castRecHoriSD.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.castRecHoriSD.setAdapter(castShowAdapter);

        binding.btnChooseSeatDS.setOnClickListener(view -> {
            Intent i = new Intent(ShowDetilesActivity.this, BookingSeatsActivity.class);
            startActivity(i);
        });
    }
}