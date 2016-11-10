package com.lyx.imitation.xmlyfm.adapter.find;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyx.imitation.xmlyfm.R;
import com.lyx.imitation.xmlyfm.model.AncherModel;
import com.lyx.imitation.xmlyfm.util.MyImageLoader;

/**
 * Created by Administrator on 2016/11/10.
 */

public class AnchorAdapter extends BaseAdapter {

    private Context context;
    private AncherModel model;

    public AnchorAdapter(Context context, AncherModel model) {
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.list.size();
    }

    @Override
    public Object getItem(int position) {
        return model.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, R.layout.fragment_find_anchor_item, null);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.txtTitle = (TextView) view.findViewById(R.id.id_find_recommend_include_title);
            holder.albumIcons = new ImageView[3];
            holder.albumNames = new TextView[3];
            for (int i = 0; i < 3; i++) {
                holder.albumIcons[i] = (ImageView) view.findViewById(context.getResources().getIdentifier("id_fragment_anchor_item_iv_" + i, "id", context.getPackageName()));
                holder.albumNames[i] = (TextView) view.findViewById(context.getResources().getIdentifier("id_fragment_anchor_item_tv_" + i, "id", context.getPackageName()));
            }
            view.setTag(holder);
        }
        AncherModel.Data data = model.list.get(position);
        holder.txtTitle.setText(data.title);
        for (int i = 0; i < 3; i++) {
            holder.albumNames[i].setText(data.list.get(i).nickname);
            MyImageLoader.load(data.list.get(i).smallLogo, holder.albumIcons[i]);
        }
        return view;
    }

    private class ViewHolder {
        TextView txtTitle;
        ImageView[] albumIcons;//三张图片，在不同的RelativeLayout里面
        TextView[] albumNames;//三个专辑标题
    }
}
