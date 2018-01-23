package cn.houno.hotel.ui.activity.book.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.ui.activity.book.bean.BookQueryListBean;

/**
 * 预订询价列表适配器
 * Created by qzc on 2017-06-07.
 */

public class BookQueryListAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<BookQueryListBean.InfoBean> mList;


    public BookQueryListAdapter(Context context, List<BookQueryListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    public void setData(List<BookQueryListBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_book_query, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPriceCode.setText(Html.fromHtml("房价码：<font color='#838281'>"
                + mList.get(position).getRoomratemainidcode() + "</font>"));
        holder.tvTypeCode.setText(Html.fromHtml("房型码：<font color='#838281'>"
                + mList.get(position).getRoomtypeidcode() + "</font>"));
        holder.tvPrice.setText(Html.fromHtml("房　价：<font color='#838281'>"
                + mList.get(position).getAmountsex() + "</font>"));
        holder.tvPackageCode.setText(Html.fromHtml("套餐码：<font color='#838281'>"
                + mList.get(position).getPackageheaderidcode() + "</font>"));
        String isdiscount = mList.get(position).getIsdiscount();
        if (TextUtils.equals("0", isdiscount)) {
            //无折扣
            holder.tvDiscountPrice.setText(Html.fromHtml("<font color='#e25558'>" + "折扣价："
                    + mList.get(position).getDiscountamounts() + "</font>"));
        } else {
            holder.tvDiscountPrice.setText(Html.fromHtml("<font color='#84c225'>" + "折扣价："
                    + mList.get(position).getDiscountamounts() + "</font>"));
        }

        holder.tvDate.setText(Html.fromHtml("日　期：<font color='#838281'>"
                + mList.get(position).getBusinessday().substring(0, 10) + "</font>"));
        holder.tvRoomNumber.setText(Html.fromHtml("房间数：<font color='#838281'>"
                + mList.get(position).getRoomcount() + "</font>"));

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_price_code)
        TextView tvPriceCode;
        @Bind(R.id.tv_type_code)
        TextView tvTypeCode;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.tv_package_code)
        TextView tvPackageCode;
        @Bind(R.id.tv_discount_price)
        TextView tvDiscountPrice;
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_room_number)
        TextView tvRoomNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
