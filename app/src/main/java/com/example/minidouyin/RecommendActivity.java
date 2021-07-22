package com.example.minidouyin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.minidouyin.API.Message;
import com.example.minidouyin.API.MessageListResponse;
import com.example.minidouyin.Recommend.FeedAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static com.example.minidouyin.API.Constants.BASE_URL;

public class RecommendActivity extends AppCompatActivity implements FeedAdapter.IOnItemClickListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedAdapter adapter = new FeedAdapter();

    public String Username;
    public String ID;
    public boolean WhetherRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        mRecyclerView = findViewById(R.id.recyclerview);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        WhetherRecommend = intent.getBooleanExtra("WhetherRecommend", true);
        Username = intent.getStringExtra("Username");
        ID = intent.getStringExtra("ID");

        if (WhetherRecommend == true) {
            getData(null);
        } else getData(ID);
    }

    @Override
    public void onItemCLick(int position, Message message) {
        Toast.makeText(RecommendActivity.this,"点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        // mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
        Intent intent = new Intent(RecommendActivity.this, VideoActivity.class);
        intent.putExtra("news", position);
        startActivity(intent);
    }

    @Override
    public void onItemLongCLick(int position, Message message) {
        Toast.makeText(RecommendActivity.this,"长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        // mAdapter.addData(position + 1, new TestData("新增头条", "0w"));
        Intent intent = new Intent(RecommendActivity.this, VideoActivity.class);
        intent.putExtra("news", position);
        startActivity(intent);
    }

    private MessageListResponse result = null;

    private void getData(final String studentId) {
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
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (studentId == null) adapter.setData(result.feeds);
                            else {
                                List<Message> tem = new LinkedList<>();
                                int i;
                                for (i = 0; i < result.feeds.size(); i++) {
                                    if (result.feeds.get(i).getStudentId().equals(studentId))
                                        tem.add(result.feeds.get(i));
                                }
                                adapter.setData(tem);
                            }
                        }
                    });
                }
            }
        }).start();
    }

}
