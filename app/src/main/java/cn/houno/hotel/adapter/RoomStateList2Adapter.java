package cn.houno.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
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

public class RoomStateList2Adapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<RoomStatusListBean.InfoBean> mList;

    public RoomStateList2Adapter(Context context, List<RoomStatusListBean.InfoBean> list) {
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
            convertView = mInflater.inflate(R.layout.item_room_status2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String sys_roomstatecode = mList.get(position).getSys_roomstatecode();

        switch (sys_roomstatecode) {
            case "EA":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.EA));

                break;
            case "ED":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.ED));

                break;
            case "INCO":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.INCO));

                break;
            case "OC":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OC));
                break;
            case "OD":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OD));
                break;
            case "OOO":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.OOO));
                break;
            case "VC":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VC));
                break;
            case "VD":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VD));
                break;
            case "VIP":
                holder.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.VIP));
                break;
        }

        holder.tvComment.setText(mList.get(position).getRoomcode());


        RoomStatusListBean.InfoBean.RoomstatedaydetailedBean roomstatedaydetailed = mList.get(position).getRoomstatedaydetailed();
        if (roomstatedaydetailed!=null){
            String sys_roomstatecode2 = roomstatedaydetailed.getSys_roomstatecode();
            switch (sys_roomstatecode2){
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
        }else {
            holder.vStatus.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        }

        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.rl_container)
        RelativeLayout rlContainer;
        @Bind(R.id.v_status)
        View vStatus;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.tv_info)
        TextView tvInfo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
