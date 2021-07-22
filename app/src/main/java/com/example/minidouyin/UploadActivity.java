package com.example.minidouyin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.minidouyin.data.Constants;
import com.example.minidouyin.newtwork.model.UploadResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.minidouyin.Util.SYSTEM_TYPE_IMAGE;
import static com.example.minidouyin.Util.getOutputMediaFile;

public class UploadActivity extends BaseActivity {

    private VideoView mVideoView;

    private Button btn_submit;
    private Button btn_cancel;
    private ImageView mview;
    private Uri videoUri;

    private Uri mSelectedImage;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        videoUri = Uri.parse(intent.getStringExtra("videoUri"));
        mview = findViewById(R.id.imageView);
        loadCover(mview, intent.getStringExtra("videoUri"), this);


        mVideoView = findViewById(R.id.videoView);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();
        mVideoView.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });

        findViewById(R.id.btn_submit).setOnClickListener(v -> submit());
        findViewById(R.id.btn_cancel).setOnClickListener(v -> cancel());

    }


    private void submit(){

        btn_submit.setText("上传中");
        btn_submit.setEnabled(false);

        mview.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mview.getDrawingCache());
        mview.setDrawingCacheEnabled(false);


        saveBitmap(bitmap);
        MultipartBody.Part coverImage = getMultipartFromUri("cover_image", mSelectedImage);
        MultipartBody.Part video = getMultipartFromUri("video", videoUri);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(IApi.class).submitMessage(Constants.stduentID, Constants.studentName,"", coverImage, video,Constants.token).enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                runOnUiThread(() -> {
                    Toast.makeText(UploadActivity.this, "upload success", Toast.LENGTH_SHORT).show();
                    btn_submit.setText("上传");
                    btn_submit.setEnabled(true);
                });
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                runOnUiThread(() -> {
                    Toast.makeText(UploadActivity.this, "upload fail", Toast.LENGTH_SHORT).show();
                    btn_submit.setText("上传");
                    btn_submit.setEnabled(true);
                });
            }
        });
        this.finish();

    }

    private void cancel() {
//        File file = new File(mPath);
//        if(file.exists() && file.isFile()){
//            if(file.delete()){
//                MediaScannerConnection.scanFile(this, new String[]{mPath}, null, null);
//            }
//        }
        finish();
    }

    public void saveBitmap(Bitmap bm) {
        File f = getOutputMediaFile(SYSTEM_TYPE_IMAGE);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        mSelectedImage = Uri.fromFile(f);
    }

    private MultipartBody.Part getMultipartFromUri(String name, Uri uri) {
        File f = new File(ResourceUtils.getRealPath(UploadActivity.this, uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
        return MultipartBody.Part.createFormData(name, f.getName(), requestFile);
    }

    public static void loadCover(ImageView imageView, String url, Context context) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop()
                                .error(R.mipmap.ic_launcher)
                                .placeholder(R.mipmap.ic_launcher)
                )
                .load(url)
                .into(imageView);
    }

}
