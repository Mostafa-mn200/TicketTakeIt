package com.finalproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.HomeItemRowBinding;
import com.finalproject.ui.activity_trailar_movie.MovieTrailerActivity;

import java.util.List;

public class TopPickedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;

    public TopPickedAdapter(Context context, Fragment fragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeItemRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_item_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.image.setImageResource(R.drawable.venom);

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 5;
        }
    }

    public void updateList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public HomeItemRowBinding binding;

        public MyHolder(HomeItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
