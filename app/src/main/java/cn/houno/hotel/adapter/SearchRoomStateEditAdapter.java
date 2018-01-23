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
import cn.houno.hotel.bean.SearchBillsBean;
import cn.houno.hotel.bean.SearchRoomStateEditBean;

/**
 * Created by qzc on 2017-05-12.
 */

public class SearchRoomStateEditAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<SearchRoomStateEditBean.InfoBean> mList;

    public SearchRoomStateEditAdapter(Context context, List<SearchRoomStateEditBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_room_state_edit, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.tvRoomCode.setText(Html.fromHtml("房号：<font color='#838281'>" + mList.get(position).getRoomcode() + "</font>"));
        holder.tvRoomName.setVisibility(View.INVISIBLE);
        holder.tvBusinessDate.setText(Html.fromHtml("营业时间：<font color='#838281'>" + mList.get(position).getBusinessday().substring(0, 19) + "</font>"));
        holder.tvFinishTime.setText(Html.fromHtml("操作时间：<font color='#838281'>" + mList.get(position).getCreatedatetime().substring(0, 19) + "</font>"));
        String oldRoomStateCode = mList.get(position).getOldsys_roomstatecode();
        String newRoomStateCode = mList.get(position).getSys_roomstatecode();
        int oldColor = getColor(oldRoomStateCode);
        holder.vOld.setBackgroundResource(oldColor);
        int newColor = getColor(newRoomStateCode);
        holder.vNews.setBackgroundResource(newColor);
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_room_code)
        TextView tvRoomCode;
        @Bind(R.id.tv_room_name)
        TextView tvRoomName;
        @Bind(R.id.tv_business_date)
        TextView tvBusinessDate;
        @Bind(R.id.tv_finish_time)
        TextView tvFinishTime;
        @Bind(R.id.ll_container)
        LinearLayout llContainer;
        @Bind(R.id.v_old)
        TextView vOld;
        @Bind(R.id.v_new)
        TextView vNews;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private int getColor(String state) {
        int colorRsId = 0;
        switch (state) {
            case "VC":
                colorRsId = R.color.VC;
                break;
            case "VD":
                colorRsId = R.color.VD;
                break;
            case "OC":
                colorRsId = R.color.OC;
                break;
            case "OD":
                colorRsId = R.color.OD;
                break;
            case "OOO":
                colorRsId = R.color.OOO;
                break;
        }
        return colorRsId;
    }
}
