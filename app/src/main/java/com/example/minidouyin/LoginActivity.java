package com.example.minidouyin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.minidouyin.API.Constants;
import com.example.minidouyin.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView username = findViewById(R.id.username);
        final TextView password = findViewById(R.id.password);
        Button button = findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                Constants.studentName = user;
                Constants.stduentID = pass;

                Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

    }
}
