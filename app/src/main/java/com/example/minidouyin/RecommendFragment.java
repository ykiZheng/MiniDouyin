package com.example.minidouyin;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.VideoView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minidouyin.API.Constants;
import com.example.minidouyin.API.Message;
import com.example.minidouyin.API.MessageListResponse;
import com.example.minidouyin.Utils.PauseVideoEvent;
import com.example.minidouyin.Utils.RxBus;
import com.example.minidouyin.base.BaseFragment;
import com.example.minidouyin.core.recycler.adapter.VideoAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import rx.functions.Action1;

import static android.os.Looper.getMainLooper;
import static com.example.minidouyin.API.Constants.BASE_URL;

public class RecommendFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private VideoView videoView;
    private VideoAdapter adapter;
    private MessageListResponse result = null;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);

        videoView = new VideoView(getActivity());

        adapter = new VideoAdapter(this.getActivity(), datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        loadData();


        //监听播放或暂停事件
        RxBus.getDefault().toObservable(PauseVideoEvent.class)
                .subscribe((Action1<PauseVideoEvent>) event -> {
                    if (event.isPlayOrPause()) {
                        videoView.start();
                    } else {
                        videoView.pause();
                    }
                });
    }


    @Override
    public void onResume() {
        super.onResume();

        videoView.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }

    private void loadData() {
        // 新线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // baseGetReposFromRemote
                String urlStr = String.format(BASE_URL + "video");
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setConnectTimeout(6000);
                    connect.setRequestMethod("GET");        // 默认
                    // 设置header
                    connect.setRequestProperty("accept", "application/json");
                    if (connect.getResponseCode() == 200) {   // success
                        InputStream in = connect.getInputStream();
                        // input是获取输出，即将获得数据输入指定变量
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        // 从InputStream读取数据
                        // Gson解析
                        result = new Gson().fromJson(reader, new TypeToken<MessageListResponse>() {
                        }.getType());
                        reader.close();
                        in.close();
                    } else {
                        // 错误处理
                    }
                    connect.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 会报错，子线程需要手动创建Looper？
                    // Toast.makeText(MainActivity.this, "网络异常 " + e.toString(), Toast.LENGTH_SHORT).show();
                }
                // return result 作为baseGetReposFromRemote的结果
                if (result != null) {
                    // getMainLooper返回主线程中的Looper，以进行UI更新
                    new Handler(getMainLooper()).post(() -> {
                        for (int i = 0; i < result.feeds.size(); i++) {
                            String thisUri = result.feeds.get(i).video_url;
                            datas.add(thisUri);

                        }

                    });
                }
            }
        }).start();
    }

    private boolean checkIfUrlExists(final String URLName) {
        boolean URL_exists = true;
        try {
//设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
            HttpURLConnection.setFollowRedirects(false);
//到 URL 所引用的远程对象的连接
            HttpURLConnection con = (HttpURLConnection) new URL(URLName)
                    .openConnection();
            /* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/
            con.setRequestMethod("HEAD");
//从 HTTP 响应消息获取状态码
// LogUtil.e("ryan","head "+con.getResponseCode());
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK)
                URL_exists = true;
            else
                URL_exists = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return URL_exists;
    }
}
