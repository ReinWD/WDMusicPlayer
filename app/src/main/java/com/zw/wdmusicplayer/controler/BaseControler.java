package com.zw.wdmusicplayer.controler;

import android.app.Activity;

import com.zw.wdmusicplayer.view.MainActivity;

/**
 * Created by ZW on 2017/4/20.
 */

public abstract class BaseControler<T extends Activity> {
    private T mContext;

    public BaseControler(T activity){
        this.mContext = activity;
    }

    T getContext(){
        return mContext;
    }
}
