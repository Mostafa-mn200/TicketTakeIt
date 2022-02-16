package com.finalproject.ui.activity_cinema_users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.CinemaUsersAdapter;

import com.finalproject.databinding.ActivityCinemasUserBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_booking_seats.BookingSeasActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class CinemasUserActivity extends AppCompatActivity {
    private String lang;
    CinemaUsersAdapter Cinemaadapter;
    ActivityCinemasUserBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cinemas_user);
        initView();
    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        Cinemaadapter = new CinemaUsersAdapter(this);
        binding.cinemaUsersRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.cinemaUsersRecycler.setAdapter(Cinemaadapter);
    }


    public void navigateToBookingSeasActivity() {
        Intent intent = new Intent(CinemasUserActivity.this, BookingSeasActivity.class);
        startActivity(intent);
    }
}