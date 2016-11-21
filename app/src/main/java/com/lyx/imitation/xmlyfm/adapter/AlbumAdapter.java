package com.lyx.imitation.xmlyfm.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.activity.PlayBackActivity;
import com.lyx.imitation.xmlyfm.model.AlbumJup;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;
import com.lyx.imitation.xmlyfm.util.T;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class AlbumAdapter extends BaseAdapter {

    private Activity mActivity;
    private AlbumJup albumJup;
    private List<AlbumJup.News> list;

    public AlbumAdapter(Activity mActivity, AlbumJup albumJup) {
        this.mActivity = mActivity;
        this.albumJup = albumJup;
        list = albumJup.data.tracks.list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mActivity, R.layout.activity_album_item, null);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.album_item_ll);
            holder.tv = (TextView) convertView.findViewById(R.id.album_item_title);
            holder.iv = (ImageView) convertView.findViewById(R.id.album_item_iv);
            holder.album_item_left_play = (TextView) convertView.findViewById(R.id.album_item_left_play);
            holder.album_item_center_time = (TextView) convertView.findViewById(R.id.album_item_center_time);
            holder.album_item_right_comment = (TextView) convertView.findViewById(R.id.album_item_right_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlbumJup.News data = list.get(position);
        holder.tv.setText(data.title);
        MyImageLoader.load(data.coverSmall, holder.iv);
        holder.album_item_left_play.setText(data.duration + "万");
        holder.album_item_center_time.setText(data.playtimes + "");
        holder.album_item_right_comment.setText(data.duration + "");

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayBackActivity.startActivity(mActivity, 0, "", 0);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView tv;
        ImageView iv;
        TextView album_item_left_play;
        TextView album_item_center_time;
        TextView album_item_right_comment;
        LinearLayout ll;
    }
}
