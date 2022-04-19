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
import com.finalproject.model.HeroModel;
import com.finalproject.model.MovieModel;
import com.finalproject.model.ShowModel;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_booking_seats.BookingSeatsActivity;
import com.finalproject.ui.user.activity_cinema_users.CinemasUserActivity;

import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class ShowDetilesActivity extends BaseActivity {
    private ActivityShowDetilesBinding binding;
    private CastShowAdapter castShowAdapter;
    private List<HeroModel> heroList;
    private ShowModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detiles);
        getDataFromIntent();
        initView();
    }
    private void getDataFromIntent() {
        Intent intent = getIntent();
        model = (ShowModel) intent.getSerializableExtra("model2");
    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.show_details), R.color.color2, R.color.white);
        binding.toolbar.llBack.setOnClickListener(view -> finish());

        binding.setLang(getLang());
        binding.setModel(model);

        if (model!=null){
            heroList=model.getHeroes();
        }

        castShowAdapter = new CastShowAdapter(heroList,this);
        binding.recViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recViewCast.setAdapter(castShowAdapter);

        binding.btnChooseSeat.setOnClickListener(view -> {
            Intent i = new Intent(ShowDetilesActivity.this, BookingSeatsActivity.class);
            startActivity(i);
        });
    }
}