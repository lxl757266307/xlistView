package com.example.administrator.xlistview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    @BindView(R.id.xlistview)
    XListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Handler handle;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        handle = new Handler();
        listView.setXListViewListener(this);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(true);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("i=" + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, list);

        listView.setAdapter(arrayAdapter);


    }

    @Override
    public void onRefresh() {
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setRefreshTime(getTime());
                listView.stopRefresh();
                listView.stopLoadMore();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setRefreshTime(getTime());
                listView.stopRefresh();
                listView.stopLoadMore();
            }
        }, 2000);
    }

    public String getTime() {
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = data.format(new Date(System.currentTimeMillis()));
        return format;
    }
}
