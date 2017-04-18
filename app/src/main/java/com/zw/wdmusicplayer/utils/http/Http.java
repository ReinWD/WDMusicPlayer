package com.zw.wdmusicplayer.utils.http;


import android.support.annotation.Nullable;
import android.util.Log;

import com.zw.wdmusicplayer.utils.http.OnRequestFinishedListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZW on 2017/3/9.
 */

public class Http {

    private final String TAG = "Http";

    public Http(OnRequestFinishedListener listener, int ... index ){
        this.mListener = listener;
    this.index = index;}

    private OnRequestFinishedListener mListener;
    private int [] index;
    private OkHttpClient httpClient;

    public void requestData(String url,String[][] header){
        if (httpClient == null){
            buildClint();
        }
        Request request = new Request.Builder()
                .get()
                .addHeader("showapi_appid",	"35673")
                .addHeader("showapi_sign",toMD5("698d51a19d8a121ce581499d7b701668"))
                .addHeader("showapi_res_gzip","0")
                .url(url).build();
        if (header != null) {
            request = this.addMoreHeader(request,header);
        }
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mListener.onResult(null,index);
                Log.e(TAG, "on requestData():",e );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mListener.onResult(response.body().byteStream(),index);
            }
        });
    }

    public void requestData(String url){
        this.requestData(url,null);
    }

    @Nullable
    private String toMD5(String raw){
        try{
            byte[] md5Byte = MessageDigest.getInstance("MD5").digest(raw.getBytes());
            if (md5Byte != null) {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int temp;
                for (byte aMd5Byte : md5Byte) {
                    temp = aMd5Byte;
                    os.write(temp);
                }
                return os.toString();
            }else {return null;}

        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "toMD5: ", e);
            e.printStackTrace();
            return null;
        }
    }

    private Request addMoreHeader(Request requests,String [][] headers){
            Request.Builder builder = requests.newBuilder();
            for (int i = 0; i < headers.length; i++) {
                builder.addHeader(headers[i][0],headers[i][1]);
            }
            return builder.build();
    }

    private void buildClint(){
        httpClient =new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
    }

}