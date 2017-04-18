package com.zw.wdmusicplayer.utils.download;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZW on 2017/4/16.
 */

public interface DownloadListener {
    void onSuccess();
    void onFailed(IOException e);
    void onPause();
    void onCancel();
}
