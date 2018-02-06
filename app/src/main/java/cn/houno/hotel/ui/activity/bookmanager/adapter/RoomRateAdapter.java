package cn.houno.hotel.ui.activity.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.home.bean.BookQueryListBean;


/**
 * 创建订单里面的房价码列表
 * Created by qzc on 2017-06-08.
 */

public class RoomRateAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater mInflater;
    private List<BookQueryListBean.InfoBean> mList;

    public RoomRateAdapter(Context context, List<BookQueryListBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_room_rate_new_order, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCode.setText(mList.get(position).getRoomratemainidcode() + "(" + mList.get(position).getRoomratemainname() + ")");
        holder.tvRate.setText(mList.get(position).getAmountsex());

        return convertView;
    }


    static class ViewHolder {

        @Bind(R.id.tv_rate_code)
        TextView tvCode;
        @Bind(R.id.tv_rate)
        TextView tvRate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
