package com.zw.wdmusicplayer.utils.download;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.zw.wdmusicplayer.Values;
import com.zw.wdmusicplayer.view.MainActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZW on 2017/4/14.
 */

public class DownloadTask extends AsyncTask<Map<String,String >,Integer,Boolean> {
    private static final String TAG = "DownloadTask";

    private DownloadListener mListener;
    private Context mContext;
    private String mTargetName;
    private boolean isPaused = false;
    private boolean isCanceled = false;

    public DownloadTask(Context context, DownloadListener mListener, String targetName){
        this.mContext = context;
        this.mListener = mListener;
        this.mTargetName = targetName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Map<String, String>... params) {
        try {
            return startDownload(params[0], mListener);
        } catch (IOException e) {
            Log.e(TAG, "doInBackground: download failed", e);
            ((MainActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                Toast.makeText(mContext,"download failed",Toast.LENGTH_SHORT).show();
                }
            });
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    private boolean startDownload(Map<String,String> params, final DownloadListener listener) throws IOException {
        OkHttpClient client = new OkHttpClient();
        final boolean[] isSuccess = new boolean[1];
        File targetFile = new File(mContext.getFilesDir().getPath()+"\\downloads", mTargetName);
        if(!targetFile.exists()){
           targetFile.createNewFile();
        }
        final RandomAccessFile target = new RandomAccessFile(targetFile,"rw");
        long downloadedLength = target.length();//现存文件大小
        target.seek(downloadedLength);

        //生成Request
        Request.Builder builder = new Request.Builder()
                .get()
                .url(params.get(Values.KEY_URL_TAG))
                .addHeader("range",String.valueOf(downloadedLength)+"-");
        params.remove(Values.KEY_URL_TAG);
        //添加headers
        for (Map.Entry<String,String> param :params.entrySet()
             ) {
            builder.addHeader(param.getKey(),param.getValue());
        }
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            //失败时
            @Override
            public void onFailure(Call call, IOException e) {
                isSuccess[0] = false;
                Log.e(TAG, "onFailure: Network failed",e);
                listener.onFailed(e);
            }
            //成功时
            @Override
            public void onResponse(Call call, Response response){
                try {
                    download(target,response);
                    isSuccess[0] = true;
                } catch (IOException e) {
                    //发回下载发生错误的提示
                    isSuccess[0]=false;
                    ((MainActivity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"error while downloading",Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });
        return isSuccess[0];
    }

    private void download(RandomAccessFile target,Response response) throws IOException {
        long targetLength;//文件大小
        long currentLength = target.length();

        //得到InputStream
        InputStream inputStream = response.body().byteStream();
        targetLength= response.body().contentLength();
        if(targetLength == 0){
            mListener.onFailed(new IOException());
        }
        int readBuffer;
        while (((readBuffer = inputStream.read()))!=-1){
            if(isPaused){
                mListener.onPause();
                return;
            }
            if(isCanceled){
                mListener.onCancel();
                return;// TODO: 2017/4/16 add cancel confirm function
            }
            if(currentLength == targetLength){
                mListener.onSuccess();
                return;
            }
                target.write(readBuffer);
                currentLength++;
        }

    }
}
