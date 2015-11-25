package com.ihongqiqu.androidschemedemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SchemeActivity extends AppCompatActivity {

    TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        tv_data = (TextView) findViewById(R.id.tv_data);

        Uri uri = getIntent().getData();
        StringBuilder sb = new StringBuilder();
        sb.append("scheme:");
        sb.append(uri.getScheme());
        sb.append("\n");
        sb.append("host:");
        sb.append(uri.getHost());
        sb.append("\n");
        sb.append("port:");
        sb.append(uri.getPort());
        sb.append("\n");
        sb.append("path:");
        sb.append(uri.getPath());

        sb.append("\n");
        sb.append("name:");
        sb.append(uri.getQueryParameter("name"));
        sb.append("\n");
        sb.append("page:");
        sb.append(uri.getQueryParameter("page"));

        tv_data.setText(sb.toString());
    }
}
