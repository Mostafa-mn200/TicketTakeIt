package com.finalproject.ui.user.activity_contact_us;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.finalproject.R;
import com.finalproject.databinding.ActivityContactUsBinding;
import com.finalproject.model.ContactUsModel;
import com.finalproject.model.LoginModel;
import com.finalproject.model.StatusResponse;
import com.finalproject.mvvm.ActivityContactUsMvvm;
import com.finalproject.mvvm.ActivityLoginMvvm;
import com.finalproject.ui.activity_base.BaseActivity;

public class ContactUsActivity extends BaseActivity {
    private ActivityContactUsBinding binding;
    private ActivityContactUsMvvm mvvm;
    private ContactUsModel contactUsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        initView();
    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.contactUs), R.color.color2, R.color.white);
        mvvm= ViewModelProviders.of(this).get(ActivityContactUsMvvm.class);
        contactUsModel = new ContactUsModel();
        binding.setModel(contactUsModel);
        mvvm.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        binding.btnSend.setOnClickListener(view -> {

            if (contactUsModel.isDataValid(this)) {
                mvvm.contactWithUs(this, contactUsModel);
            }else {
                Toast.makeText(this, "Please fill inputs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}