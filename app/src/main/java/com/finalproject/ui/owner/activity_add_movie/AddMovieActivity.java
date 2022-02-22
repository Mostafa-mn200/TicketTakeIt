package com.finalproject.ui.owner.activity_add_movie;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.finalproject.databinding.ActivityAddMovieBinding;
import com.finalproject.databinding.AddMovieHeroRowBinding;
import com.finalproject.model.UserModel;
import com.finalproject.preferences.Preferences;
import com.finalproject.share.Common;
import com.finalproject.ui.activity_base.BaseActivity;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddMovieActivity extends BaseActivity {
    private ActivityAddMovieBinding binding;
    private UserModel userModel;
    private Preferences preferences;
    private ActivityResultLauncher<Intent> launcher;
    private final String READ_PERM = Manifest.permission.READ_EXTERNAL_STORAGE;
    private final String write_permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final String camera_permission = Manifest.permission.CAMERA;
    private final int READ_REQ = 1, CAMERA_REQ = 2, VIDEO_REQ = 3;
    private String type;
    private int selectedReq = 0;
    private Uri uri = null, videouri;
    private SimpleExoPlayer player;
    private int currentWindow = 0;
    private long currentPosition = 0;
    private boolean playWhenReady = true;
    private AddMovieHeroRowBinding heroRowBinding;
    private List<AddMovieHeroRowBinding> addHeroBindingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_movie);
        initView();
    }

    private void initView() {
        binding.setLang(getLang());




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
                } else if (selectedReq == VIDEO_REQ) {
                    Uri uri = result.getData().getData();
                    new VideoTask().execute(uri);
                }
            }
        });
        binding.llBack.setOnClickListener(view -> finish());

        binding.llAddHero.setOnClickListener(view -> {
//            type="secondImage";
            addHeroItem();
        });

        binding.image.setOnClickListener(view -> {
            type = "mainImage";
            openSheet();
        });
        binding.llAddVideo.setOnClickListener(view -> {
            checkVideoPermission();
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
    }

    //    private void checkPermission() {
//        if (ActivityCompat.checkSelfPermission(this, BaseActivity.fineLocPerm) != PackageManager.PERMISSION_GRANTED) {
//            permissionLauncher.launch(BaseActivity.fineLocPerm);
//        } else {
//
//            activityAddServiceMvvm.initGoogleApi();
//        }
//    }
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

    private Uri getUriFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        return Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, "", ""));
    }

    public void checkVideoPermission() {
        closeSheet();
        if (ActivityCompat.checkSelfPermission(this, READ_PERM) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{READ_PERM}, VIDEO_REQ);
        } else {
            displayVideoIntent(VIDEO_REQ);
        }
    }

    private void displayVideoIntent(int video_req) {
        selectedReq = video_req;

        Intent intent = new Intent();

        if (video_req == VIDEO_REQ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            } else {
                intent.setAction(Intent.ACTION_GET_CONTENT);

            }

            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("video/*");
            launcher.launch(intent);

        }
    }

    private void addHeroItem() {
//        AddAdditionalItemModel model=new AddAdditionalItemModel();
        AddMovieHeroRowBinding  heroRow1Binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.add_movie_hero_row, null, false);
//        heroRowBinding.setModel(model);

        addHeroBindingList = new ArrayList<>();
        addHeroBindingList.add(heroRow1Binding);
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

    @SuppressLint("StaticFieldLeak")
    public class VideoTask extends AsyncTask<Uri, Void, Long> {
        MediaMetadataRetriever retriever;
        private Uri uri;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            retriever = new MediaMetadataRetriever();
        }

        @Override
        protected Long doInBackground(Uri... uris) {
            uri = uris[0];
            retriever.setDataSource(AddMovieActivity.this, uris[0]);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long duration = Long.parseLong(time) / 1000;
            retriever.release();
            return duration;
        }

        @Override
        protected void onPostExecute(Long duration) {
            super.onPostExecute(duration);
            Log.e("duration", duration + "__");
            videouri = uri;
            Log.e("uri", uri + "");
//                addServiceModel.setVideoUri(uri.toString());
            initPlayer(videouri);

//            if (duration <= 90) {
//
//           } else {
//               Toast.makeText(AddMovieActivity.this, R.string.vid_du_less, Toast.LENGTH_SHORT).show();
//           }

        }

    }
    private void initPlayer(Uri uri) {


        if (uri != null) {

            DataSource.Factory factory = new DefaultDataSourceFactory(this, "finalProject");


            if (player == null) {
                player = new SimpleExoPlayer.Builder(this).build();
                binding.player.setPlayer(player);
                MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(uri);
                player.prepare(mediaSource);

                player.seekTo(currentWindow, currentPosition);
                player.setPlayWhenReady(playWhenReady);
                player.prepare(mediaSource);
            } else {

                MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(uri);

                player.seekTo(currentWindow, currentPosition);
                player.setPlayWhenReady(playWhenReady);
                player.prepare(mediaSource);
            }

        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            release();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            release();
        }
    }


    private void release() {
        if (player != null) {
            currentWindow = player.getCurrentWindowIndex();
            currentPosition = player.getCurrentPosition();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPlayer(videouri);
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
//
//            activityAddServiceMvvm.startLocationUpdate();
//
//        }
//    }
}