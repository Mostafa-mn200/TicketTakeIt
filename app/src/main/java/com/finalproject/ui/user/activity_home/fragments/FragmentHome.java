package com.finalproject.ui.user.activity_home.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.finalproject.R;
import com.finalproject.adapter.ComingSoonAdapter;
import com.finalproject.adapter.SliderAdapter;
import com.finalproject.adapter.TopMoviesAdapter;
import com.finalproject.adapter.TopShowsAdapter;

import com.finalproject.databinding.FragmentHomeBinding;
import com.finalproject.model.ComingSoonModel;
import com.finalproject.model.HomeDataModel;
import com.finalproject.model.MovieModel;
import com.finalproject.model.ShowModel;
import com.finalproject.model.SliderModel;
import com.finalproject.mvvm.FragmentHomeMVVM;
import com.finalproject.ui.activity_base.BaseFragment;
import com.finalproject.ui.user.activity_coming_soon.ComingSoonActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_show_detiles.ShowDetilesActivity;
import com.finalproject.ui.user.activity_trailar_movie.MovieDetailsActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends BaseFragment {
    private HomeActivity activity;
    private FragmentHomeBinding binding;
    private TopMoviesAdapter topMoviesAdapter;
    private ComingSoonAdapter comingSoonAdapter;
    private TopShowsAdapter topShowsAdapter;
    private FragmentHomeMVVM mvvm;
    private HomeDataModel homeDataModel;

    private Timer timer;

    private SliderAdapter sliderAdapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mvvm = ViewModelProviders.of(this).get(FragmentHomeMVVM.class);

        mvvm.getIsLoading().observe(activity, isLoading -> {
            if (isLoading) {
                binding.progBarSlider.setVisibility(View.VISIBLE);
                binding.progBarTopPicked.setVisibility(View.VISIBLE);
                binding.progBarTopShow.setVisibility(View.VISIBLE);
                binding.progBarComingSoon.setVisibility(View.VISIBLE);

            }
        });

        mvvm.getSliderDataModelMutableLiveData().observe(activity, new Observer<List<SliderModel>>() {
            @Override
            public void onChanged(List<SliderModel> sliderModels) {
                if (sliderModels.size()>0){
                    binding.progBarSlider.setVisibility(View.GONE);
                    sliderModels.clear();
                    sliderModels.addAll(homeDataModel.getSlider());
                    sliderAdapter.notifyDataSetChanged();
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new MyTask(), 3000, 3000);
                }
            }
        });

        mvvm.getMovies().observe(activity, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels.size()>0){
                    binding.progBarTopPicked.setVisibility(View.GONE);

                    topMoviesAdapter.updateList(movieModels);
                    //binding.tvNoCategory.setVisibility(View.GONE);
                }
                else {
                    binding.progBarTopPicked.setVisibility(View.VISIBLE);
                }
            }
        });
        mvvm.getShows().observe(activity, new Observer<List<ShowModel>>() {
            @Override
            public void onChanged(List<ShowModel> showModels) {
                if (showModels.size()>0){
                    binding.progBarTopShow.setVisibility(View.GONE);

                    topShowsAdapter.updateList(showModels);
                }else {
                    binding.progBarTopShow.setVisibility(View.VISIBLE);
                }
            }
        });
        mvvm.getComingSoon().observe(activity, new Observer<List<ComingSoonModel>>() {
            @Override
            public void onChanged(List<ComingSoonModel> comingSoonModels) {
                if (comingSoonModels.size()>0){
                    binding.progBarComingSoon.setVisibility(View.GONE);

                    comingSoonAdapter.updateList(comingSoonModels);
                }else {
                    binding.progBarComingSoon.setVisibility(View.VISIBLE);
                }
            }
        });
      
        sliderAdapter = new SliderAdapter(activity);
        binding.pager.setClipToPadding(false);
        binding.pager.setPadding(8, 0, 8, 0);
        binding.pager.setPageMargin(20);

        binding.pager.setAdapter(sliderAdapter);
        sliderAdapter.notifyDataSetChanged();
        binding.tab.setupWithViewPager(binding.pager);
        timer = new Timer();

        binding.seeComingSoon.setPaintFlags(binding.seeComingSoon.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        binding.seeComingSoon.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), ComingSoonActivity.class);
            startActivity(i);
        });


        topMoviesAdapter = new TopMoviesAdapter(activity, this);
        binding.recyclerTopPicked.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerTopPicked.setAdapter(topMoviesAdapter);

        comingSoonAdapter = new ComingSoonAdapter(activity, this);
        binding.recyclerComingSoon.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerComingSoon.setAdapter(comingSoonAdapter);

        topShowsAdapter = new TopShowsAdapter(activity, this);
        binding.recyclerTopShow.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerTopShow.setAdapter(topShowsAdapter);

        timer.scheduleAtFixedRate(new MyTask(), 3000, 3000);

        mvvm.getHomeData2(activity);

    }

    public void navigateToMovieTrailerActivity() {
        Intent i = new Intent(getContext(), MovieDetailsActivity.class);
        startActivity(i);
    }

    public void navigateToShowDetilesActivity() {
        Intent i = new Intent(getContext(), ShowDetilesActivity.class);
        startActivity(i);
    }

    public class MyTask extends TimerTask {
        @Override
        public void run() {
            activity.runOnUiThread(() -> {
                int current_page = binding.pager.getCurrentItem();
                if (current_page < sliderAdapter.getCount() - 1) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
                } else {
                    binding.pager.setCurrentItem(0);

                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        timer.cancel();
        timer.purge();
        timer = null;
        super.onDestroyView();

    }
}