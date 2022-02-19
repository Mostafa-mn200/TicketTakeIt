package com.finalproject.ui.user.activity_cinema_users;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.CinemaUsersAdapter;

import com.finalproject.databinding.ActivityCinemasUserBinding;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_booking_seats.BookingSeasActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class CinemasUserActivity extends BaseActivity {
    private String lang;
    CinemaUsersAdapter Cinemaadapter;
    ActivityCinemasUserBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cinemas_user);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());
        Cinemaadapter = new CinemaUsersAdapter(this);
        binding.cinemaUsersRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.cinemaUsersRecycler.setAdapter(Cinemaadapter);
        binding.llBack.setOnClickListener(view -> finish());
    }


    public void navigateToBookingSeasActivity() {
        Intent intent = new Intent(CinemasUserActivity.this, BookingSeasActivity.class);
        startActivity(intent);
    }
}