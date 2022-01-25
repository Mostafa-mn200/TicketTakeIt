package com.finalproject.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.databinding.FragmentProfileBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_home.HomeActivity;

import java.util.Locale;


public class FragmentProfile extends Fragment {
    private HomeActivity activity;
    private FragmentProfileBinding binding;

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        //binding.tvPassword.setPaintFlags(binding.tvPassword.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        binding.llEditAccount.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.activity_edit_account));
        binding.llLanguage.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.activity_language);

        });
        binding.langName.setText(Language.getLanguageSelected(requireContext()));
    }
}