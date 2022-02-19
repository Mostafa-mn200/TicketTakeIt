package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.CoMovieItemBinding;
import com.finalproject.databinding.TheaterOwnerItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TheaterOwnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;

    public TheaterOwnerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        TheaterOwnerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.theater_owner_item, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.image.setImageResource(R.drawable.theatre);
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
        public TheaterOwnerItemBinding binding;

        public MyHolder(TheaterOwnerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
