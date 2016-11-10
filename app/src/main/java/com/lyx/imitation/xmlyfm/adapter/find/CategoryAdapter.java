package com.lyx.imitation.xmlyfm.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.model.Category;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

/**
 * Created by Administrator on 2016/11/10.
 */

public class CategoryAdapter extends BaseAdapter {

    private Category category;
    private Context context;

    public CategoryAdapter(Context context, Category category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public int getCount() {
        return category.list.size();
    }

    @Override
    public Object getItem(int position) {
        return category.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_find_category_item, parent, false);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.id_fragment_category_imageView);
            holder.textView = (TextView) view.findViewById(R.id.id_fragment_category_textView);
            view.setTag(holder);
        }
        Category.Entry data = category.list.get(position);
        holder.textView.setText(data.title);
        MyImageLoader.load(data.coverPath, holder.imageView);
        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
