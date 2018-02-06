package cn.houno.hotel.home.adapter;

import android.content.Context;
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
import cn.houno.hotel.bean.RoomStatusListBean;

/**
 * 房态列表
 * Created by qzc on 2017/5/8 0008.
 */

public class RoomStateListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<RoomStatusListBean.InfoBean> mList;

    public RoomStateListAdapter(Context context, List<RoomStatusListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
    }

    public void setData(List<RoomStatusListBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_room_status, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String sys_roomstatecode = null;
        if (mList.size() > 0 && mList != null) {
            sys_roomstatecode = mList.get(position).getSys_roomstatecode();
        }

        switch (sys_roomstatecode) {
            case "EA":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.EA));
                break;
            case "ED":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.ED));
                break;
            case "INCO":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.INCO));
                break;
            case "OC":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OC));
                break;
            case "OD":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OD));
                break;
            case "OOO":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OOO));
                break;
            case "VC":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VC));
                break;
            case "VD":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VD));
                break;
            case "VIP":
                holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VIP));
                break;
        }

        holder.tvRoomCode.setText(mList.get(position).getRoomcode());
        holder.tvComment.setText(mList.get(position).getRoomclasscode() + "/"
                + mList.get(position).getRoomtypecode() + "/" + mList.get(position).getRoomscenerycode());
        String info = "";
        if (mList.get(position).getRoomorderinfo() != null && mList.get(position).getRoomorderinfo().size() != 0) {
            for (int i = 0; i < mList.get(position).getRoomorderinfo().size(); i++) {
                info += mList.get(position).getRoomorderinfo().get(i).getMainname();
                if (i != mList.get(position).getRoomorderinfo().size() - 1) {
                    info += "|";
                }
            }
        }
        holder.tvInfo.setText(info);

        RoomStatusListBean.InfoBean.RoomstatedaydetailedBean roomstatedaydetailed
                = mList.get(position).getRoomstatedaydetailed();
        if (roomstatedaydetailed != null) {
            String sys_roomstatecode2 = roomstatedaydetailed.getSys_roomstatecode();
            switch (sys_roomstatecode2) {
                case "EA":
                    holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.EA));
                    break;
                case "ED":
                    holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.ED));
                    break;
                case "INCO":
                    holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.INCO));
                    break;
                case "VIP":
                    holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.VIP));
                    break;
            }
        } else {
            holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        }

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_room_code)
        TextView tvRoomCode;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.tv_info)
        TextView tvInfo;
        @Bind(R.id.v_status)
        View vStatus;
        @Bind(R.id.ll_container)
        LinearLayout llContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
