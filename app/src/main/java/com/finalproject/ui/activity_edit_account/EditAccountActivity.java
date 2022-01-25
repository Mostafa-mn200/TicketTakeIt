package com.finalproject.ui.activity_edit_account;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.Rotate;
import com.finalproject.R;
import com.finalproject.databinding.ActivityEditAccountBinding;
import com.finalproject.language.Language;
import com.finalproject.preferences.Preferences;
import com.finalproject.ui.activity_verification_code.VerificationCodeActivity;

public class EditAccountActivity extends AppCompatActivity {
    private ActivityEditAccountBinding binding;
    private Preferences preferences;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account);

        initView();
    }

    private void initView() {
        ProgressDialog dialog = new ProgressDialog(this);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        });

        binding.llBack.setOnClickListener(view -> finish());
        binding.saveChanges.setOnClickListener(view -> {
            dialog.setMessage(getString(R.string.savingChanges));
            dialog.show();

            new Handler().postDelayed(() -> {
                dialog.dismiss();
                Toast.makeText(EditAccountActivity.this, R.string.dataUpdated, Toast.LENGTH_SHORT).show();
            }, 600);
        });
//        binding.verifyPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                navigateToVerificationCodeActivity();
//
//            }
//        });
    }
//    private void navigateToVerificationCodeActivity() {
//        Intent intent = new Intent(this, VerificationCodeActivity.class);
//        intent.putExtra("phone_code", "+2");
//        intent.putExtra("phone",binding.phNum.getText().toString());
//        launcher.launch(intent);
//    }
}