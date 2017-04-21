package com.zw.wdmusicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by ZW on 2017/4/20.
 */

public class MusicPlayService extends Service {
    private static final String TAG = "MusicPlayService";
    MusicPlayBinder mBinder = new MusicPlayBinder();
    MediaPlayer mPlayer;
    Thread mPlayThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: service started");
        mPlayer = new MediaPlayer();
    }

    public void play(String target) throws IOException {
            new PlayTask().execute(target);
        }

    public void play(File file) throws IOException {
        this.play(file.getPath());
    }

    public class MusicPlayBinder extends Binder{
        public MusicPlayService getService(){
            return MusicPlayService.this;
        }
    }

    class PlayTask extends AsyncTask<String,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                mPlayer.reset();
                mPlayer.setDataSource(params[0]);
                mPlayer.prepare();
                mPlayer.start();
                return true;
            }catch (IOException e){
                return false;
            }
        }
    }
}
