package com.zw.wdmusicplayer.controler;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.zw.wdmusicplayer.Values;
import com.zw.wdmusicplayer.model.HotList;
import com.zw.wdmusicplayer.utils.http.Http;
import com.zw.wdmusicplayer.utils.http.OnRequestFinishedListener;
import com.zw.wdmusicplayer.view.MainActivity;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZW on 2017/4/20.
 */

public class MainControler extends BaseControler<MainActivity> {
    Http mHttpClient;
    int currentList;

    public MainControler(MainActivity activity) {
        super(activity);
    }

    public void requestList(int listID){
        HashMap<String,String> headers = new HashMap<>();
        headers.put(Values.LIST_PARAM,String.valueOf(listID));
        this.sendRequest(Values.API,headers);
    }

    private void sendRequest(String target, Map<String,String>headers){
        if (mHttpClient == null) {
            mHttpClient = new Http(new onListReturnListener());
        }
        mHttpClient.requestData(target,headers);
    }

    private class onListReturnListener implements OnRequestFinishedListener{
        @Nullable
        @Override
        public void onResult(InputStream resultStream) {
            if(resultStream == null){
                //TODO:when it's failed
                Gson json = new Gson();
            }
        }
    }
}
