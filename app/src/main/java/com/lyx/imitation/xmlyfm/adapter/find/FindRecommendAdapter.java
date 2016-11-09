package com.lyx.imitation.xmlyfm.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.model.Recommend;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */

public class FindRecommendAdapter extends BaseAdapter {

    private Context context;
    private Recommend recommend;
    private List<ItemEntry> entryList = new ArrayList<>();

    private class ItemEntry {
        Object object;
        ViewType type;

        ItemEntry(Object object, ViewType type) {
            this.object = object;
            this.type = type;
        }
    }

    private enum ViewType {
        Recommend(0), Special(1), Discovery(2);
        private int value;

        ViewType(int value) {
            this.value = value;
        }
    }

    public FindRecommendAdapter(Context context, Recommend recommend) {
        this.context = context;
        this.recommend = recommend;
        initList();
    }

    public void setRecommend(Recommend recommend) {
        this.recommend = recommend;
        initList();
        notifyDataSetChanged();
    }

    private void initList() {
        if (recommend != null) {
            entryList.clear();
            if (recommend.editorRecommendAlbums.list.size() > 0) {// 小编推荐
                entryList.add(new ItemEntry(recommend.editorRecommendAlbums, ViewType.Recommend));
            }
            if (recommend.specialColumn.list.size() > 0) {// 精品听单
                entryList.add(new ItemEntry(recommend.specialColumn, ViewType.Special));
            }
            if (recommend.discoveryColumns.list.size() > 0) {//  发现新奇
                entryList.add(new ItemEntry(recommend.discoveryColumns, ViewType.Discovery));
            }
            //热门推荐
            for (Recommend.Hot hot : recommend.hotRecommends.list) {
                entryList.add(new ItemEntry(hot, ViewType.Recommend));
            }
        }
    }

    @Override
    public int getCount() {
        return entryList.size();
    }

    @Override
    public Object getItem(int position) {
        return entryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return entryList.get(position).type.value;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemEntry itemEntry = entryList.get(position);
        switch (itemEntry.type) {
            case Recommend:
                return buildRecommendView(itemEntry, position, convertView, parent);
            case Special:
                return buildSpecialView(itemEntry, position, convertView, parent);
            case Discovery:
                return buildDiscoveryView(itemEntry, position, convertView, parent);
        }
        TextView textView = new TextView(context);
        textView.setText("类型错误-" + itemEntry.type.value);
        return textView;
    }

    private View buildRecommendView(ItemEntry itemEntry, int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_find_recommend_item_recommend, parent, false);
        }
        RecommendViewHolder holder = (RecommendViewHolder) view.getTag();
        if (holder == null) {
            holder = new RecommendViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.id_find_recommend_include_title);
            holder.albumIcons = new ImageView[3];
            holder.albumNames = new TextView[3];
            holder.trackNames = new TextView[3];
            for (int i = 0; i < 3; i++) {
                holder.albumIcons[i] = (ImageView) view.findViewById(context.getResources().getIdentifier("id_fragment_recommend_item_iv_" + i, "id", context.getPackageName()));
                holder.albumNames[i] = (TextView) view.findViewById(context.getResources().getIdentifier("id_fragment_recommend_item_tv_" + i, "id", context.getPackageName()));
            }
            view.setTag(holder);
        }
        Recommend.Hot datas = (Recommend.Hot) itemEntry.object;
        holder.txtTitle.setText(datas.title);
        for (int i = 0; i < 3; i++) {
            holder.albumNames[i].setText(datas.list.get(i).trackTitle);
            MyImageLoader.load(datas.list.get(i).albumCoverUrl290, holder.albumIcons[i]);
        }
        return view;
    }

    private View buildSpecialView(ItemEntry itemEntry, int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_find_recommend_item_container, parent, false);
        }
        SpecialViewHolder holder = (SpecialViewHolder) view.getTag();
        if (holder == null) {
            holder = new SpecialViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.id_find_recommend_include_title);
            holder.itemContainer = (LinearLayout) view.findViewById(R.id.id_find_recommend_special_layout);
            view.setTag(holder);
        }
        Recommend.SpecialColumn datas = (Recommend.SpecialColumn) itemEntry.object;
        holder.txtTitle.setText(datas.title);
        // 清空旧的LinearLayout数据，根据听单的item来添加
        holder.itemContainer.removeAllViews();
        for (Recommend.Column column : datas.list) {
            View v = View.inflate(context, R.layout.fragment_find_recommend_item_special, null);
            ((TextView) v.findViewById(R.id.fragment_recommend_item_boutique_top_title_tv)).setText(column.title);
            ((TextView) v.findViewById(R.id.fragment_recommend_item_boutique_top_content_tv)).setText(column.subtitle);
            ImageView iv = (ImageView) v.findViewById(R.id.fragment_recommend_item_boutique_top_iv);
            MyImageLoader.load(column.coverPath, iv);
            holder.itemContainer.addView(v);
        }
        return view;
    }

    private View buildDiscoveryView(ItemEntry itemEntry, int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_find_recommend_item_container, parent, false);
        }
        DiscoveryViewHolder holder = (DiscoveryViewHolder) view.getTag();
        if (holder == null) {
            holder = new DiscoveryViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.id_find_recommend_include_title);
            holder.layout = (LinearLayout) view.findViewById(R.id.id_find_recommend_special_layout);
            view.setTag(holder);
        }
        Recommend.DiscoveryColumns datas = (Recommend.DiscoveryColumns) itemEntry.object;
        holder.txtTitle.setText(datas.title);
        holder.layout.removeAllViews();
        for (Recommend.Discovery discovery : datas.list) {
            View v = View.inflate(context, R.layout.fragment_find_recommend_item_discovery, null);
            ((TextView) v.findViewById(R.id.fragment_recommend_hear_shopping_title)).setText(discovery.title);
            ((TextView) v.findViewById(R.id.fragment_recommend_hear_shopping_content)).setText(discovery.subtitle);
            ImageView iv = (ImageView) v.findViewById(R.id.fragment_recommend_hear_shopping_iv);
            MyImageLoader.load(discovery.coverPath, iv);
            holder.layout.addView(v);
        }
        return view;
    }

    //推荐的ViewHolder
    private class RecommendViewHolder {
        public TextView txtTitle;
        public TextView txtMore;
        public ImageView[] albumIcons;//三张图片，在不同的RelativeLayout里面
        public TextView[] albumNames;//三个专辑标题
        public TextView[] trackNames;//三个曲目名称
    }

    private static class SpecialViewHolder {
        public TextView txtTitle;
        public TextView txtMore;
        //存储SpecialItemView
        public LinearLayout itemContainer;
    }

    private class DiscoveryViewHolder {
        public TextView txtTitle;
        public LinearLayout layout;
    }

}
