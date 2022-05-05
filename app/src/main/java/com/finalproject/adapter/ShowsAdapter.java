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
import com.finalproject.model.ShowModel;
import com.finalproject.ui.owner.activity_home.fragments.FragmentOwnerShows;
import com.finalproject.ui.user.activity_home.fragments.FragmentShows;

import java.util.List;

public class ShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ShowModel> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;

    public ShowsAdapter(Context context, Fragment fragment) {
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
        myHolder.binding.setModel(list.get(position));
        if (fragment instanceof FragmentShows){
            FragmentShows fragmentShows=(FragmentShows) fragment;
        }
        if (fragment instanceof FragmentOwnerShows){
            FragmentOwnerShows fragmentOwnerShows=(FragmentOwnerShows) fragment;
        }
        myHolder.binding.cardMovieItem.setOnClickListener(view -> {
            FragmentShows fragmentShows=(FragmentShows) fragment;
            fragmentShows.navigatetoShowDetilesActivity();
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();

        } else {
            return 0;
        }
    }

    public void updateList(List<ShowModel> list) {
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
