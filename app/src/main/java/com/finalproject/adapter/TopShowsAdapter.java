package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.TopShowsItemRowBinding;
import com.finalproject.ui.activity_home.fragments.FragmentHome;

import java.util.List;

public class TopShowsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;

    public TopShowsAdapter(Context context, Fragment fragment) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TopShowsItemRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.top_shows_item_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.image.setImageResource(R.drawable.theatre);
        myHolder.binding.movieName.setText("العيال كبرت");

        myHolder.binding.cardTopMovie.setOnClickListener(view -> {
            FragmentHome fragmentHome = (FragmentHome) fragment;
            fragmentHome.navigateToShowDetilesActivity();
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

    public void updateList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TopShowsItemRowBinding binding;

        public MyHolder(TopShowsItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
