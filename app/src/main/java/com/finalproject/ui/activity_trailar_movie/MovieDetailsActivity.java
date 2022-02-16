package com.finalproject.ui.activity_trailar_movie;

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
import com.finalproject.databinding.ActivityMovieDetailsBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_cinema_users.CinemasUserActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class MovieDetailsActivity extends AppCompatActivity {
    CastAdapter mCastadapter;
    private ActivityMovieDetailsBinding binding;
    private String lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        initView();

        binding.BuyTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MovieDetailsActivity.this, CinemasUserActivity.class);
                startActivity(i);
            }
        });

    }

    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);

        mCastadapter = new CastAdapter(this);
        binding.castRecHori.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.castRecHori.setAdapter(mCastadapter);
    }


}