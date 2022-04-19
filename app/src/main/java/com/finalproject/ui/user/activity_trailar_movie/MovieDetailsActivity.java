package com.finalproject.ui.user.activity_trailar_movie;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.finalproject.R;

import com.finalproject.adapter.CastAdapter;
import com.finalproject.databinding.ActivityMovieDetailsBinding;
import com.finalproject.model.HeroModel;
import com.finalproject.model.MovieModel;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.user.activity_cinema_users.CinemasUserActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class MovieDetailsActivity extends BaseActivity {
    private CastAdapter castadapter;
    private ActivityMovieDetailsBinding binding;
    private MovieModel model;
    private ExoPlayer player;
    private List<HeroModel> heroList;
    private boolean isInFullScreen = false;
    private DataSource.Factory dataSourceFactory;
    private DefaultTrackSelector trackSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        getDataFromIntent();
        initView();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        model = (MovieModel) intent.getSerializableExtra("model1");
    }

    private void initView() {
        binding.setLang(getLang());
        binding.setModel(model);

        if (model!=null){
            if (model.getVideo()!=null){
                getVideoImage();
                setupPlayer();
            }
            heroList=model.getHeroes();
        }
        setUpToolbar(binding.toolbar, getString(R.string.movie_details), R.color.color2, R.color.white);
        binding.toolbar.llBack.setOnClickListener(view -> finish());

        binding.btnChooseCinema.setOnClickListener(view -> {
            Intent i = new Intent(MovieDetailsActivity.this, CinemasUserActivity.class);
            startActivity(i);
        });
        castadapter = new CastAdapter(heroList,this);
        binding.recViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recViewCast.setAdapter(castadapter);

        binding.flVideo.setOnClickListener(v -> {
            isInFullScreen = true;
//            binding.motionLayout.transitionToEnd();
            if (player != null) {
                player.setPlayWhenReady(true);
            }


        });


    }
    private void getVideoImage() {

        int microSecond = 6000000;// 6th second as an example
        Uri uri = Uri.parse(model.getVideo());
        RequestOptions options = new RequestOptions().frame(microSecond).override(binding.imageVideo.getWidth(), binding.imageVideo.getHeight());
        Glide.with(this).asBitmap()
                .load(uri)
                .centerCrop()
                .apply(options)
                .into(binding.imageVideo);
    }
    @SuppressLint("ClickableViewAccessibility")
    private void setupPlayer() {

        if (model.getVideo() != null) {
            trackSelector = new DefaultTrackSelector(this);
            dataSourceFactory = new DefaultDataSource.Factory(this);
            MediaSourceFactory mediaSourceFactory = new DefaultMediaSourceFactory(dataSourceFactory);
            MediaItem mediaItem = MediaItem.fromUri(Uri.parse(model.getVideo()));

            player = new ExoPlayer.Builder(this)
                    .setTrackSelector(trackSelector)
                    .setMediaSourceFactory(mediaSourceFactory)
                    .build();

            player.setMediaItem(mediaItem);
            player.setPlayWhenReady(false);
            player.setRepeatMode(ExoPlayer.REPEAT_MODE_ONE);
            binding.exoPlayer.setPlayer(player);
            player.prepare();

            binding.exoPlayer.setOnTouchListener((v, event) -> {
                if (player != null && player.isPlaying()) {
                    player.setPlayWhenReady(false);
                } else if (player != null && !player.isPlaying()) {

                    player.setPlayWhenReady(true);

                }
                return false;
            });


        }


    }

    public boolean isFullScreen() {
        return isInFullScreen;
    }

    public void setToNormalScreen() {

        isInFullScreen = false;
//        binding.motionLayout.transitionToStart();
        if (player != null) {
            player.setPlayWhenReady(false);
        }


    }
    @Override
    public void onResume() {
        if ((Util.SDK_INT <= 23 || player == null) && model != null) {
            setupPlayer();
        }
        super.onResume();


    }

    @Override
    public void onStart() {
        if (Util.SDK_INT > 23) {
            if (player == null && model != null) {
                setupPlayer();
                binding.exoPlayer.onResume();
            }


        }
        super.onStart();


    }

    @Override
    public void onPause() {
        if (Util.SDK_INT <= 23) {
            if (player != null) {
                player.setPlayWhenReady(false);
            }
        }
        super.onPause();


    }


}