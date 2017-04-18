package com.zw.wdmusicplayer.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.zw.wdmusicplayer.Values;
import com.zw.wdmusicplayer.utils.download.DownloadTask;
import com.zw.wdmusicplayer.utils.download.DownloadListener;
import com.zw.wdmusicplayer.view.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZW on 2017/4/14.
 */

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";
    private MainActivity mContext;
    private String mName;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadService(String name, Context context) {
        super(name);
        this.mContext = (MainActivity) context;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        intent.getStringExtra(Values.KEY_URL_TAG);
        DownloadTask download = new DownloadTask(mContext,new DownloadServiceListener(),mName);
    }

    class DownloadServiceListener implements DownloadListener{

        @Override
        public void onSuccess() {
             mContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }

        @Override
        public void onFailed(IOException e) {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onCancel() {

        }
    }
}
