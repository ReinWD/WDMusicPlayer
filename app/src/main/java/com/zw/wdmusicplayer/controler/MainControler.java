package com.zw.wdmusicplayer.controler;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zw.wdmusicplayer.Values;
import com.zw.wdmusicplayer.model.JsonBean;
import com.zw.wdmusicplayer.service.MusicPlayService;
import com.zw.wdmusicplayer.utils.http.Http;
import com.zw.wdmusicplayer.utils.http.OnRequestFinishedListener;
import com.zw.wdmusicplayer.view.MainActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZW on 2017/4/20.
 */

public class MainControler extends BaseControler<MainActivity> {
    private static final String TAG = "MainControler";

    private Http mHttpClient;
    private int currentList;
    private MusicPlayService mPlayservice;
    private JsonBean mList;
    private Connector mConnector;

    public MainControler(MainActivity activity) {
        super(activity);
        connectService();
    }

    public void requestList(int listID) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Values.LIST_PARAM, String.valueOf(listID));
        currentList = listID;
        this.sendRequest(Values.API, headers);
    }

    public void play(final JsonBean.Body.SongList.Song song) throws IOException {
        mPlayservice.play(song.getUrl());
        getContext().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"now Playing "+song.getSongName(),Toast.LENGTH_SHORT);
            }
        });
    }

    public void previousSong() {

    }

    public void nextSong() {

    }

    public void refresh(){
        this.requestList(currentList);
    }

    public int getCurrentList() {
        return currentList;
    }

    private void sendRequest(String target, Map<String, String> headers) {
        if (mHttpClient == null) {
            mHttpClient = new Http(new onListReturnListener());
        }
        mHttpClient.requestData(target, headers);
    }

    private void connectService() {
        Intent intent = new Intent(getContext(), MusicPlayService.class);
        getContext().startService(intent);
        mConnector = new Connector();
        getContext().bindService(intent, mConnector, getContext().BIND_AUTO_CREATE);
    }

    void refreshList(final JsonBean list) {
        getContext().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getContext().refreshList(list);
            }
        });
    }

    public void onDestroy() {
        getContext().unbindService(mConnector);
    }

    private class onListReturnListener implements OnRequestFinishedListener {
        @Nullable
        @Override
        public void onResult(InputStream resultStream) {
            if (resultStream == null) {
                //TODO:when it's failed
                getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                    }
                });
                return;
            }
            Gson json = new Gson();
            try {
                mList = json.fromJson(readData(resultStream), JsonBean.class);
                refreshList(mList);
                Log.d(TAG, "onResult: wow");
            } catch (IOException e) {
                Log.e(TAG, "onResult: read failed", e);
                Toast.makeText(getContext(), "refresh error", Toast.LENGTH_SHORT).show();
            }
            if (mList != null) {

            }
        }

        private String readData(InputStream is) throws IOException {
            if (is == null) {
                return null;
            }
            String result;
            ByteArrayOutputStream harukimurakami = new ByteArrayOutputStream();
            int buffer;
            while ((buffer = is.read()) != -1) {
                harukimurakami.write(buffer);
            }
            result = harukimurakami.toString();
            harukimurakami.close();
            is.close();
            return result;
        }
    }

    private class Connector implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayservice = ((MusicPlayService.MusicPlayBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
}
