package com.zw.wdmusicplayer.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zw.wdmusicplayer.R;

/**
 * Adapter for recycler in MainActivity.
 * list songs.
 * Created by ZW on 2017/4/20.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context mContext;

    public MainRecyclerAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_recycler,parent));
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        private TextView mSongName;
        private TextView mSinger;
        private TextView mAlbum;
        private ImageView mCover;

        MainViewHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView){
            mCover = (ImageView) itemView.findViewById(R.id.image_cover_item);
            mSongName = (TextView) itemView.findViewById(R.id.tv_song_name_item);
            mSinger = (TextView) itemView.findViewById(R.id.tv_singer_item);
            mAlbum = (TextView) itemView.findViewById(R.id.tv_album_item);
        }

        void setSongName(String songName){
            this.mSongName.setText(songName);
        }
        void setSinger(String singerName){
            this.mSinger.setText(singerName);
        }
        void setAlbum(String album){
            this.mAlbum.setText(album);
        }
        void setCover(Bitmap cover){
            this.mCover.setImageBitmap(cover);
        }
    }
}
