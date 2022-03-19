package com.finalproject.ui.owner.activity_add_show;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.finalproject.R;
import com.finalproject.databinding.ActivityAddShowBinding;
import com.finalproject.databinding.AddMovieHeroRowBinding;
import com.finalproject.databinding.AddShowHeroRowBinding;
import com.finalproject.model.UserModel;
import com.finalproject.preferences.Preferences;
import com.finalproject.share.Common;
import com.finalproject.ui.activity_base.BaseActivity;
import com.finalproject.ui.owner.activity_add_movie.AddMovieActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddShowActivity extends BaseActivity {
    private ActivityAddShowBinding binding;
    private UserModel userModel;
    private Preferences preferences;
    private ActivityResultLauncher<Intent> launcher;
    private final String READ_PERM = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final String write_permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final String camera_permission = Manifest.permission.CAMERA;
    private final int READ_REQ = 1, CAMERA_REQ = 2;
    private int selectedReq = 0;
    private Uri uri = null;
    private String type;
    private List<AddShowHeroRowBinding> addShowHeroBindingList;
    private AddShowHeroRowBinding heroRowBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_show);
        initView();
    }

    private void initView() {
        setUpToolbar(binding.toolbar, getString(R.string.add_show), R.color.color2, R.color.white);
        addShowHeroBindingList=new ArrayList<>();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                if (selectedReq == READ_REQ) {

                    uri = result.getData().getData();
                    File file = new File(Common.getImagePath(this, uri));
                    if (type.equals("mainImage")) {
                        binding.icon.setVisibility(View.GONE);

                        Picasso.get().load(file).fit().into(binding.image);
//                        addServiceModel.setMainImage(uri.toString());
//                        binding.setModel(addServiceModel);
                    }else if (type.equals("secondImage")){
                        heroRowBinding.lIcon.setVisibility(View.GONE);
                        Picasso.get().load(file).fit().into(heroRowBinding.image1);
                    }
                } else if (selectedReq == CAMERA_REQ) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    uri = getUriFromBitmap(bitmap);
                    if (uri != null) {
                        String path = Common.getImagePath(this, uri);

                        if (path != null) {
                            if (type.equals("mainImage")) {
                                binding.icon.setVisibility(View.GONE);
                                Picasso.get().load(new File(path)).fit().into(binding.image);
//                                addServiceModel.setMainImage(uri.toString());
//                                binding.setModel(addServiceModel);
                            }else if (type.equals("secondImage")){
                                heroRowBinding.lIcon.setVisibility(View.GONE);
                                Picasso.get().load(new File(path)).fit().into(heroRowBinding.image1);
                            }
                        } else {
                            if (type.equals("mainImage")) {
                                binding.icon.setVisibility(View.GONE);
                                Picasso.get().load(uri).fit().into(binding.image);
//                                addServiceModel.setMainImage(uri.toString());
//                                binding.setModel(addServiceModel);
                            }else if (type.equals("secondImage")){
                                heroRowBinding.lIcon.setVisibility(View.GONE);
                                Picasso.get().load(uri).fit().into(heroRowBinding.image1);
                            }

                        }
                    }
                }
            }
        });

        binding.flGallery.setOnClickListener(view -> {
            closeSheet();
            checkReadPermission();
        });
        binding.flCamera.setOnClickListener(view -> {
            closeSheet();
            checkCameraPermission();
        });

        binding.btnCancel.setOnClickListener(view -> closeSheet());

        binding.image.setOnClickListener(view -> {
            type="mainImage";
            openSheet();
        });


        binding.toolbar.llBack.setOnClickListener(view -> finish());

        binding.llAddHero.setOnClickListener(view -> {
            addHeroItem();
        });
    }

    public void checkCameraPermission() {

        closeSheet();

        if (ContextCompat.checkSelfPermission(this, write_permission) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, camera_permission) == PackageManager.PERMISSION_GRANTED
        ) {
            SelectImage(CAMERA_REQ);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{camera_permission, write_permission}, CAMERA_REQ);
        }
    }

    private void SelectImage(int req) {
        selectedReq = req;
        Intent intent = new Intent();

        if (req == READ_REQ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            } else {
                intent.setAction(Intent.ACTION_GET_CONTENT);

            }

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/*");

            launcher.launch(intent);

        } else if (req == CAMERA_REQ) {
            try {
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            } catch (SecurityException e) {
                Toast.makeText(this, R.string.perm_image_denied, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, R.string.perm_image_denied, Toast.LENGTH_SHORT).show();

            }


        }
    }

    public void openSheet() {
        binding.expandLayout.setExpanded(true, true);
    }

    public void closeSheet() {
        binding.expandLayout.collapse(true);

    }

    public void checkReadPermission() {
        closeSheet();
        if (ActivityCompat.checkSelfPermission(this, READ_PERM) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_PERM}, READ_REQ);
        } else {
            SelectImage(READ_REQ);
        }
    }
    private Uri getUriFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        return Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "", ""));
    }

    private void addHeroItem() {
//        AddAdditionalItemModel model=new AddAdditionalItemModel();
        AddShowHeroRowBinding  heroRow1Binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.add_show_hero_row, null, false);
//        heroRowBinding.setModel(model);

        addShowHeroBindingList = new ArrayList<>();
        addShowHeroBindingList.add(heroRow1Binding);
        binding.llHeroItem.addView(heroRow1Binding.getRoot());
        heroRow1Binding.image1.setOnClickListener(view -> {
            type = "secondImage";
            this.heroRowBinding=heroRow1Binding;
            openSheet();
        });
        heroRow1Binding.imDelete.setOnClickListener(v -> {
            binding.llHeroItem.removeView(heroRow1Binding.getRoot());

        });

//        addServiceModel.setMainItemList(addHeroBindingList);
//        binding.setModel(addServiceModel);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

//            activityAddServiceMvvm.startLocationUpdate();

        }
    }
}