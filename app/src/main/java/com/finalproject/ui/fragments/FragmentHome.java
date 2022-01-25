package com.finalproject.ui.fragments;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.finalproject.R;
import com.finalproject.adapter.ComingSoonAdapter;
import com.finalproject.adapter.SliderAdapter;
import com.finalproject.adapter.TopPickedAdapter;
import com.finalproject.adapter.TopShowsAdapter;
import com.finalproject.databinding.FragmentHomeBinding;
import com.finalproject.ui.activity_home.HomeActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FragmentHome extends Fragment {
    private HomeActivity activity;
    private FragmentHomeBinding binding;
    private TopPickedAdapter topPickedAdapter;
    private ComingSoonAdapter comingSoonAdapter;
    private TopShowsAdapter topShowsAdapter;
    private int[] sliders = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
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

        sliderAdapter = new SliderAdapter(activity);


        binding.pager.setClipToPadding(false);
        binding.pager.setPadding(8, 0, 8, 0);
        binding.pager.setPageMargin(20);

        binding.pager.setAdapter(sliderAdapter);
        sliderAdapter.notifyDataSetChanged();
        binding.tab.setupWithViewPager(binding.pager);
        timer = new Timer();

        binding.seeTopPicked.setPaintFlags(binding.seeTopPicked.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.seeComingSoon.setPaintFlags(binding.seeComingSoon.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.seeTopShow.setPaintFlags(binding.seeTopShow.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        topPickedAdapter = new TopPickedAdapter(activity, this);
        binding.recyclerTopPicked.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerTopPicked.setAdapter(topPickedAdapter);

        comingSoonAdapter = new ComingSoonAdapter(activity, this);
        binding.recyclerComingSoon.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerComingSoon.setAdapter(comingSoonAdapter);

        topShowsAdapter = new TopShowsAdapter(activity, this);
        binding.recyclerTopShow.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));
        binding.recyclerTopShow.setAdapter(topShowsAdapter);

        timer.scheduleAtFixedRate(new MyTask(), 3000, 3000);

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