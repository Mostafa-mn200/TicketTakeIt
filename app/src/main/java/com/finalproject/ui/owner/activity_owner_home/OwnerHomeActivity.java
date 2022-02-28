package com.finalproject.ui.owner.activity_owner_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.finalproject.R;
import com.finalproject.databinding.ActivityOwnerHomeBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_cinema_owner.CinemaOwnerActivity;
import com.finalproject.ui.owner.activity_theater_owner.TheaterOwnerActivity;
import com.finalproject.ui.user.activity_language.LanguageActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class OwnerHomeActivity extends BaseActivity {
    ActivityOwnerHomeBinding binding;
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_owner_home);
        initView();
    }


    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(getLang());

        binding.cardMoviesOwner.setOnClickListener(view -> {
            Intent i=new Intent(OwnerHomeActivity.this, CinemaOwnerActivity.class);
            startActivity(i);
        });

        binding.cardShowsOwner.setOnClickListener(view -> {
            Intent i=new Intent(OwnerHomeActivity.this, TheaterOwnerActivity.class);
            startActivity(i);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.owner_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.item1){
            startActivity(new Intent(OwnerHomeActivity.this, LanguageActivity.class));
        }
        return true;
    }

}