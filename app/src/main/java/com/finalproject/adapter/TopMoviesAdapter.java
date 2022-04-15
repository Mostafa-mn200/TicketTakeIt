package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.TopMoviesItemRowBinding;
import com.finalproject.model.MovieModel;
import com.finalproject.ui.user.activity_trailar_movie.MovieDetailsActivity;
import com.finalproject.ui.user.activity_home.fragments.FragmentHome;

import java.util.List;

public class TopMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieModel> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;
    MovieDetailsActivity movieDetailsActivity;

    public TopMoviesAdapter(Context context, Fragment fragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TopMoviesItemRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.top_movies_item_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.setModel(list.get(position));
        myHolder.binding.image.setImageResource(R.drawable.venom);

        myHolder.binding.cardTopMovie.setOnClickListener(view -> {
            FragmentHome fragmentHome = (FragmentHome) fragment;
            fragmentHome.navigateToMovieTrailerActivity();
        });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 5;
        }
    }

    public void updateList(List<MovieModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TopMoviesItemRowBinding binding;

        public MyHolder(TopMoviesItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
