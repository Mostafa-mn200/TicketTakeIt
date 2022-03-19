package com.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.recyclerview.widget.RecyclerView;

import com.finalproject.R;
import com.finalproject.databinding.MoviesFilterRowBinding;
import com.finalproject.model.FilterModel;
import com.finalproject.ui.owner.activities_owner_home.activity_movies.OwnerMoviesActivity;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_home.fragments.FragmentMovies;

import java.util.List;


public class MoviesFilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FilterModel> list;
    private Context context;
    private LayoutInflater inflater;
    private AppCompatActivity appCompatActivity;
    private Fragment fragment;
    private int currentPos = 1;
    private int oldPos = currentPos;
    private MyHolder oldHolder;

    public MoviesFilterAdapter(List<FilterModel> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        appCompatActivity = (AppCompatActivity) context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesFilterRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.movies_filter_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        myHolder.binding.setModel(list.get(position));

        if (oldHolder==null){
            oldHolder=myHolder;
        }

        myHolder.itemView.setOnClickListener(view -> {
            if (oldHolder!=null){
                FilterModel oldFilterModel=list.get(oldPos);
                oldFilterModel.setSelected(false);
                list.set(oldPos,oldFilterModel);

                MyHolder oHolder=(MyHolder) oldHolder;
                oHolder.binding.setModel(oldFilterModel);
            }

            currentPos=myHolder.getAdapterPosition();
            FilterModel filterModel=list.get(currentPos);
            filterModel.setSelected(true);
            list.set(currentPos,filterModel);
            myHolder.binding.setModel(filterModel);

            oldHolder=myHolder;
            oldPos=currentPos;

            if (fragment instanceof FragmentMovies){
                FragmentMovies fragmentMovies=(FragmentMovies) fragment;
                fragmentMovies.setItemFilter(filterModel,currentPos);
            }


            if (appCompatActivity instanceof OwnerMoviesActivity){
                OwnerMoviesActivity ownerMoviesActivity=(OwnerMoviesActivity) appCompatActivity;
                ownerMoviesActivity.setItemFilter(filterModel,currentPos);
            }
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

    public void updateList(List<FilterModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public MoviesFilterRowBinding binding;

        public MyHolder(MoviesFilterRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
