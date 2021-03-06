package cn.houno.hotel.adapter;

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
import cn.houno.hotel.bean.BookInfoListBean;

/**
 * Created by qzc on 2017-05-24.
 */

public class CompanyAndTravelAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<BookInfoListBean.InfoBean> mList;

    public CompanyAndTravelAdapter(Context context, List<BookInfoListBean.InfoBean> list) {
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
        RoomPriceCodeAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_dialog_room_type_code, parent, false);
            holder = new RoomPriceCodeAdapter.ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RoomPriceCodeAdapter.ViewHolder) convertView.getTag();
        }
        holder.tvCode.setText(mList.get(position).getIDCode());
        holder.tvName.setText(mList.get(position).getMainName());

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
