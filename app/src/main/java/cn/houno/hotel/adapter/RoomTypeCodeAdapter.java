package cn.houno.hotel.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.bean.RoomTypeCodeBean;

/**
 * 房型代码列表
 * Created by qzc on 2017-05-13.
 */

public class RoomTypeCodeAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<RoomTypeCodeBean> mList;

    public RoomTypeCodeAdapter(Context context, List<RoomTypeCodeBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_dialog_room_type_code, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCode.setText(mList.get(position).getCode());
        holder.tvName.setText(mList.get(position).getName());

        return convertView;
    }


    static class ViewHolder {

        @Bind(R.id.tv_code)
        TextView tvCode;
        @Bind(R.id.tv_name)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
