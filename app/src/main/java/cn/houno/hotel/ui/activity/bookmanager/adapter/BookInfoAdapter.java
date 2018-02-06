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
import cn.houno.hotel.bean.BookInfoListBean;

/**
 * Created by qzc on 2017-06-07.
 */

public class BookInfoAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<BookInfoListBean.InfoBean> mList;


    public BookInfoAdapter(Context context, List<BookInfoListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    public void setData(List<BookInfoListBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_personal_info, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(mList.get(position).getMainName());
        holder.tvPhone.setText(mList.get(position).getContactinformationStr());
        holder.tvSex.setText(mList.get(position).getSex());
        holder.tvIdCard.setText(mList.get(position).getIDCard());
        holder.tvAddress.setText(mList.get(position).getCountry()+" "+mList.get(position).getState()
                +" "+mList.get(position).getCity());

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_phone)
        TextView tvPhone;
        @Bind(R.id.tv_sex)
        TextView tvSex;
        @Bind(R.id.tv_id_card)
        TextView tvIdCard;
        @Bind(R.id.tv_address)
        TextView tvAddress;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
