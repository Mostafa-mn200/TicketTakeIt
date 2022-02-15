package com.finalproject.ui.activity_coming_soon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.finalproject.R;
import com.finalproject.adapter.AllComingSoonAdapter;
import com.finalproject.adapter.MoviesFilterAdapter;
import com.finalproject.databinding.ActivityComingSoonBinding;
import com.finalproject.language.Language;

import java.util.Locale;

import io.paperdb.Paper;

public class ComingSoonActivity extends AppCompatActivity {
    private String lang;
    private ActivityComingSoonBinding binding;
    private AllComingSoonAdapter mallComingSoonAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, "en"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_coming_soon);
        initView();
    }


    private void initView() {
        Paper.init(this);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);

        mallComingSoonAdapter = new AllComingSoonAdapter(this);
        binding.comingRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.comingRecycler.setAdapter(mallComingSoonAdapter);
    }
}