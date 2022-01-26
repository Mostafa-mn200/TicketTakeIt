package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.finalproject.R;
import com.finalproject.databinding.SliderRowBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private int[] sliders = {R.drawable.venom, R.drawable.spider, R.drawable.theatre};
    private Context context;
    private LayoutInflater inflater;

    public SliderAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return sliders.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SliderRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.slider_row, container, false);
        binding.imgSlider.setImageResource(sliders[position]);
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}