package cn.houno.hotel.ui.activity.book.adapter;

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
import cn.houno.hotel.home.bean.OrderListBean;


/**
 * 订单列表
 * Created by qzc on 2017-06-12.
 */

public class OrderListAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<OrderListBean.InfoBean> mList;


    public OrderListAdapter(Context context, List<OrderListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    public void setData(List<OrderListBean.InfoBean> list) {
        mList = list;
        notifyDataSetChanged();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_query_order, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvOrderId.setText(mList.get(position).getIdcode());
        holder.tvName.setText(mList.get(position).getMainname());
        holder.tvDate.setText(mList.get(position).getBegindate().substring(0, 10) + " - " + mList.get(position).getEnddate().substring(0, 10));

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_order_id)
        TextView tvOrderId;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_date)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
