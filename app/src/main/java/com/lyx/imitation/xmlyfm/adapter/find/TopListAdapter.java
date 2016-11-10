package com.lyx.imitation.xmlyfm.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.model.TopListModel;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

/**
 * Created by Administrator on 2016/11/10.
 */

public class TopListAdapter extends BaseAdapter {

    private TopListModel model;
    private Context context;

    public TopListAdapter(Context context, TopListModel model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.datas.get(0).list.size() + 1 + model.datas.get(1).list.size();
    }

    @Override
    public Object getItem(int position) {
        int first = model.datas.get(0).list.size();
        if (position < first) {
            return model.datas.get(0).list.get(position);
        } else if (position == first) {
            return null;
        } else {
            return model.datas.get(1).list.get(position - first - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == model.datas.get(0).list.size() ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (getItemViewType(position) == 1) {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.include_recommend, parent, false);
            }
            TextView textView = (TextView) view.getTag();
            if (textView == null) {
                textView = (TextView) view.findViewById(R.id.id_find_recommend_include_title);
                view.setTag(textView);
            }
            textView.setText("主播榜单111");
        } else {
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.fragment_find_toplist_item, parent, false);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.id_fragment_toplist_imageView);
                holder.textView1 = (TextView) view.findViewById(R.id.id_fragment_toplist_textView_01);
                holder.textView2 = (TextView) view.findViewById(R.id.id_fragment_toplist_textView_02);
                holder.textView3 = (TextView) view.findViewById(R.id.id_fragment_toplist_textView_03);
                view.setTag(holder);
            }
            TopListModel.News data = (TopListModel.News) getItem(position);
            holder.textView1.setText(data.title);
            holder.textView2.setText(data.firstKResults.get(0).title);
            holder.textView3.setText(data.firstKResults.get(1).title);
            MyImageLoader.load(data.coverPath, holder.imageView);
        }
        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }
}
