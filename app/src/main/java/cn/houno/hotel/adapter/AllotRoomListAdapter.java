package cn.houno.hotel.adapter;

import android.content.Context;
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
import cn.houno.hotel.bean.AllotRoomListBean;
import cn.houno.hotel.bean.NameCodeBean;

/**
 * 排房房间列表
 * Created by qzc on 2017-06-29.
 */

public class AllotRoomListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<AllotRoomListBean.InfoBean> mList;

    public AllotRoomListAdapter(Context context, List<AllotRoomListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    public void setData(List<AllotRoomListBean.InfoBean> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_allot_room_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvRoomIdCode.setText(mList.get(position).getIdcode());

        String sys_roomstatecode = mList.get(position).getSys_roomstatecode();
        holder.tvRoomStateCode.setText(sys_roomstatecode);
        if (TextUtils.isEmpty(sys_roomstatecode)){
            holder.tvRoomStateCode.setVisibility(View.GONE);
        }else {
            holder.tvRoomStateCode.setVisibility(View.VISIBLE);
            switch (sys_roomstatecode) {
                case "EA":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.EA));

                    break;
                case "ED":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.ED));
                    break;
                case "INCO":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.INCO));

                    break;
                case "OC":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.OC));
                    break;
                case "OD":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.OD));
                    break;
                case "OOO":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.OOO));
                    break;
                case "VC":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.VC));
                    break;
                case "VD":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.VD));
                    break;
                case "VIP":
                    holder.tvRoomStateCode.setBackgroundColor(mContext.getResources().getColor(R.color.VIP));
                    break;
            }
        }


        holder.tvRoomType.setText(mList.get(position).getRoomtypevalue());
        holder.tvRoomScenery.setText(mList.get(position).getSceneryvalue());
        holder.tvBedType.setText(mList.get(position).getBedtypevalue());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_room_id_code)
        TextView tvRoomIdCode;
        @Bind(R.id.tv_room_state_code)
        TextView tvRoomStateCode;
        @Bind(R.id.tv_room_type)
        TextView tvRoomType;
        @Bind(R.id.tv_bed_type)
        TextView tvBedType;
        @Bind(R.id.tv_room_scenery)
        TextView tvRoomScenery;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
