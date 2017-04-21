package com.zw.wdmusicplayer.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zw.wdmusicplayer.R;
import com.zw.wdmusicplayer.model.JsonBean;
import com.zw.wdmusicplayer.view.MainActivity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Adapter for recycler in MainActivity.
 * list songs.
 * Created by ZW on 2017/4/20.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {
    private static final String TAG = "MainRecyclerAdapter";

    private Context mContext;
    private boolean hasData;
    private ArrayList<JsonBean.Body.SongList.Song> mSongList;

    public MainRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(JsonBean list) {
        if (list == null) {
            hasData = false;
            return;
        }
        if(list.getShowapiResBody().getSongList()==null){
            hasData=false;
            return;
        }if(list.getShowapiResBody().getSongList().getPagebean()==null){
            hasData = false;
        }else {
            hasData = true;
            this.mSongList = list.getShowapiResBody().getSongList().getPagebean();
            notifyDataSetChanged();
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_recycler, null));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        if(hasData){
            final JsonBean.Body.SongList.Song song = mSongList.get(position);
            holder.mSinger.setText(song.getSingerName());
            holder.mSongName.setText(song.getSongName());
            holder.itemView.setOnClickListener(new OnItemClickedListener(song));
        }
    }

    @Override
    public int getItemCount(){
        if (!hasData)return 0;
        else return mSongList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView mSongName;
        TextView mSinger;
        TextView mAlbum;
        ImageView mCover;

        MainViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            mCover = (ImageView) itemView.findViewById(R.id.image_cover_item);
            mSongName = (TextView) itemView.findViewById(R.id.tv_song_name_item);
            mSinger = (TextView) itemView.findViewById(R.id.tv_singer_item);
            mAlbum = (TextView) itemView.findViewById(R.id.tv_album_item);
        }

        void setSongName(String songName) {
            this.mSongName.setText(songName);
        }

        void setSinger(String singerName) {
            this.mSinger.setText(singerName);
        }

        void setAlbum(String album) {
            this.mAlbum.setText(album);
        }

        void setCover(Bitmap cover) {
            this.mCover.setImageBitmap(cover);
        }

    }
    class OnItemClickedListener implements View.OnClickListener{
        JsonBean.Body.SongList.Song mSong;
        OnItemClickedListener(JsonBean.Body.SongList.Song song){
            this.mSong =song;
        }
        @Override
        public void onClick(final View v) {
            ((MainActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ((MainActivity) mContext).getControler().play(mSong);
                    } catch (IOException e) {
                        ((MainActivity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext,"IOE",Toast.LENGTH_SHORT);
                            }
                        });
                    }
                }
            });
        }
    }
}
