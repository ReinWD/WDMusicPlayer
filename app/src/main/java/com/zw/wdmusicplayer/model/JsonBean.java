package com.zw.wdmusicplayer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ZW on 2017/4/10.
 */
public class JsonBean {
    @SerializedName("show_res_code")
    private int showapiResCode;
    @SerializedName("showapi_res_error")
    private String showapiResError;
    @SerializedName("showapi_res_body")
    private Body showapiResBody;

    public int getShowapiResCode() {
        return showapiResCode;
    }

    public String getShowapiResError() {
        return showapiResError;
    }

    public Body getShowapiResBody() {
        return showapiResBody;
    }

    public void setShowapiResCode(int showapiResCode) {
        this.showapiResCode = showapiResCode;
    }

    public void setShowapiResError(String showapiResError) {
        this.showapiResError = showapiResError;
    }

    public void setShowapiResBody(Body showapiResBody) {
        this.showapiResBody = showapiResBody;
    }

    public class Body {
        @SerializedName("ret_code")
        private int retCode;
        @SerializedName("pagebean")
        private SongList songList;

        public class SongList {
            @SerializedName("songlist")
            private ArrayList<Song> pagebean;
            public class Song {
                @SerializedName("songname")
                private String songName;
                private int seconds;
                private String albummid;
                private int songid;
                private int singerid;
                @SerializedName("albumpic_big")
                private String albumpicBig;
                @SerializedName("albumpic_small")
                private String albumpicSmall;
                @SerializedName("downurl")
                private String downUrl;
                private String url;
                @SerializedName("singername")
                private String singerName;
                private String albumid;

                public void setAlbumid(String albumid) {
                    this.albumid = albumid;
                }
                public void setAlbummid(String albummid) {
                    this.albummid = albummid;
                }
                public void setAlbumpicBig(String albumpicBig) {
                    this.albumpicBig = albumpicBig;
                }
                public void setAlbumpicSmall(String albumpicSmall) {
                    this.albumpicSmall = albumpicSmall;
                }
                public void setDownUrl(String downUrl) {
                    this.downUrl = downUrl;
                }
                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }
                public void setSingerid(int singerid) {
                    this.singerid = singerid;
                }
                public void setSingerName(String singerName) {
                    this.singerName = singerName;
                }
                public void setSongid(int songid) {
                    this.songid = songid;
                }
                public void setSongName(String songName) {
                    this.songName = songName;
                }
                public void setUrl(String url) {
                    this.url = url;
                }

                public String getAlbumid() {
                    return albumid;
                }
                public String getAlbummid() {
                    return albummid;
                }
                public String getAlbumpicBig() {
                    return albumpicBig;
                }
                public String getAlbumpicSmall() {
                    return albumpicSmall;
                }
                public String getDownUrl() {
                    return downUrl;
                }
                public int getSeconds() {
                    return seconds;
                }
                public int getSingerid() {
                    return singerid;
                }
                public String getSingerName() {
                    return singerName;
                }
                public int getSongid() {
                    return songid;
                }
                public String getSongName() {
                    return songName;
                }
                public String getUrl() {
                    return url;
                }
            }

            public void setPagebean(ArrayList<Song> pagebean) {
                this.pagebean = pagebean;
            }

            public ArrayList<Song> getPagebean() {
                return pagebean;
            }
        }

        public SongList getSongList() {
            return songList;
        }

        public int getRetCode() {
            return retCode;
        }

        public void setSongList(SongList songList) {
            this.songList = songList;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }
    }

}



