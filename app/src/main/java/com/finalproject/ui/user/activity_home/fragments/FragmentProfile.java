package com.finalproject.ui.user.activity_home.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finalproject.R;
import com.finalproject.databinding.FragmentProfileBinding;
import com.finalproject.language.Language;
import com.finalproject.ui.activity_base.BaseFragment;
import com.finalproject.ui.user.activity_home.HomeActivity;
import com.finalproject.ui.user.activity_language.LanguageActivity;
import com.finalproject.ui.activity_login.LoginActivity;


public class FragmentProfile extends BaseFragment {
    private HomeActivity activity;
    private FragmentProfileBinding binding;
    private ActivityResultLauncher<Intent> launcher;
    private int req;
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req == 1 && result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                String lang = result.getData().getStringExtra("lang");
                activity.refreshActivity(lang);
            }
        });
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
            req = 1;
            Intent intent = new Intent(activity, LanguageActivity.class);
            launcher.launch(intent);
        });
        binding.langName.setText(Language.getLanguageSelected(requireContext()));
        binding.llLogOut.setOnClickListener(view -> {
            Intent intent=new Intent(activity,LoginActivity.class);
            launcher.launch(intent);
        });
    }
}