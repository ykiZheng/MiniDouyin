package com.example.minidouyin;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.minidouyin.data.Constants;
import com.example.minidouyin.newtwork.model.UploadResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadActivity extends BaseActivity {

    private String mPath;
    private String mImagePath;
    private VideoView mVideoView;

    private Button btn_submit;
    private Button btn_cancel;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_upload;
    }

    @Override
    protected void init() {
        mPath = getIntent().getStringExtra("path");

        mVideoView = findViewById(R.id.videoView);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);


        mVideoView.setVideoPath(mPath);
        mVideoView.start();



        mVideoView.setVideoPath(mPath);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();
        mVideoView.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });


        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


    }


    private void submit(){

        btn_submit.setText("上传中");
        btn_submit.setEnabled(false);


        saveBitmap(bitmap);


        MultipartBody.Part coverImage = getMultipartFromUri("cover_image", mSelectedImage);
        MultipartBody.Part video = getMultipartFromUri("video", videoUri);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.androidcamp.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(IApi.class).submitMessage(Constants.stduentID, Constants.studentName,"", coverImage, video).enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UploadActivity.this, "upload success", Toast.LENGTH_SHORT).show();
                        btn_submit.setText("上传");
                        btn_submit.setEnabled(true);
                    }
                });
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UploadActivity.this, "upload fail", Toast.LENGTH_SHORT).show();
                        btn_submit.setText("上传");
                        btn_submit.setEnabled(true);
                    }
                });
            }
        });

        this.finish();

    }

    private void cancel() {
        File file = new File(mPath);
        if(file.exists() && file.isFile()){
            if(file.delete()){
                MediaScannerConnection.scanFile(this, new String[]{mPath}, null, null);
            }
        }
        finish();
    }



    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private byte[] readDataFromUri(Uri uri) {
        byte[] data = null;
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            data = Util.inputStream2bytes(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static String bitmap2File(Bitmap bitmap, String name) {

        File f = new File(Environment.getExternalStorageDirectory() + name +  ".jpg");
        if  (f.exists()) f.delete();
        FileOutputStream fOut = null;
        try  {
            fOut = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();

        } catch (IOException e) {
            return  null;
        }
        return  f.getAbsolutePath();

    }

    public static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MiniDouyin");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
}
