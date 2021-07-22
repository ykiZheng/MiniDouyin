package com.example.minidouyin;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
        Bitmap bitmapSelectedImage = getVideoThumb(mPath);
        mImagePath = bitmap2File(bitmapSelectedImage,"image");

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


        Uri mImageUri = Uri.parse(mImagePath);
        byte[] coverImageData = readDataFromUri(mImageUri);
        if (coverImageData == null || coverImageData.length == 0) {
            Toast.makeText(this, "封面不存在", Toast.LENGTH_SHORT).show();
            return;
        }

//        MultipartBody.Part titlePart = MultipartBody.Part.createFormData("toitle, to);
//        MultipartBody.Part contentPart = MultipartBody.Part.createFormData("content", content);
        MultipartBody.Part coverPart = MultipartBody.Part.createFormData(
                "image",
                "cover.png",
                RequestBody.create(MediaType.parse("multipart/form-data"),
                        coverImageData)
        );

        MultipartBody.Part videoPart = MultipartBody.Part.createFormData(
                "video",  "video.mp4",
                RequestBody.create(MediaType.parse("multipart/form-data") ,
                        mPath)
        );



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

    private static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        File file=new File(path);
        media.setDataSource(file.getAbsolutePath());
        Bitmap firstFrame = media.getFrameAtTime();

        return  firstFrame;
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
