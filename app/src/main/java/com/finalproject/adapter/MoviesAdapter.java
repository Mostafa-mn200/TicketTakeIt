package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.MovieShowItemRowBinding;
import com.finalproject.ui.fragments.FragmentMovies;


import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;

    public MoviesAdapter(Context context, Fragment fragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieShowItemRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.movie_show_item_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;

        myHolder.binding.image.setImageResource(R.drawable.venom);
        myHolder.binding.cardMovieItem.setOnClickListener(view -> {
            FragmentMovies fragmentMovies = (FragmentMovies) fragment;
            fragmentMovies.navigatetoMovieTrailerActivity();
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 8;
        }
    }

    public void updateList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public MovieShowItemRowBinding binding;

        public MyHolder(MovieShowItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
