package com.zw.wdmusicplayer.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zw.wdmusicplayer.R;
import com.zw.wdmusicplayer.Values;
import com.zw.wdmusicplayer.controler.MainControler;
import com.zw.wdmusicplayer.model.JsonBean;
import com.zw.wdmusicplayer.view.adapter.MainRecyclerAdapter;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";

    Context mContext = this;

    private ImageView mPlay;
    private ImageView mPrevious;
    private ImageView mNext;
    private ImageView mAlbum;
    private LinearLayout mCurrentSongInfo;
    private Toolbar mToolBar;
    private RecyclerView mSongList;
    private MainRecyclerAdapter mMainRecyclerAdapter;
    private MainControler mMainControler;

    public void refreshList(JsonBean list){
        mMainRecyclerAdapter.setData(list);
        mSongList.smoothScrollToPosition(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainControler.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerListener();
        mMainControler = new MainControler(this);
        mMainControler.requestList(Values.LIST_HOT);//刷新HotList
        Log.i(TAG, "onCreate: finished");
    }

    private void initView(){
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle(Values.TOOLBAR_TITLE_MAIN);
        mToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainControler.refresh();
                mSongList.smoothScrollToPosition(0);
            }
        });
        setSupportActionBar(mToolBar);

        mSongList = (RecyclerView) findViewById(R.id.recycler_songlist_main);
        mMainRecyclerAdapter = new MainRecyclerAdapter(this);
        mSongList.setLayoutManager(new LinearLayoutManager(this));
        mSongList.setAdapter(mMainRecyclerAdapter);

        mAlbum = (ImageView) findViewById(R.id.image_album_main);
        mCurrentSongInfo = (LinearLayout) findViewById(R.id.layout_song_info_main);
        mPlay = (ImageView) findViewById(R.id.image_play_main);
        mPrevious = (ImageView) findViewById(R.id.image_previous_main);
        mNext = (ImageView) findViewById(R.id.image_next_main);
    }

    private void registerListener(){
        //TODO:change logic below
        mCurrentSongInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"F@@K this funtion",Toast.LENGTH_SHORT).show();
            }
        });
        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"previous",Toast.LENGTH_SHORT).show();
            }
        });
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"play",Toast.LENGTH_SHORT).show();
            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"next",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MainControler getControler() {
        return mMainControler;
    }
}
