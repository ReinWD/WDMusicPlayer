package com.zw.wdmusicplayer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZW on 2017/4/14.
 */

public class Lyric {
    public class Body{
        private String lyric;
        @SerializedName("lyric_txt")
        private String lyricText;
    }
}
