package cn.houno.hotel.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.bean.MyCleanRoomListBean;

/**
 * 个人清洁单据
 * Created by qzc on 2017-05-11.
 */

public class PerCleanDocAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<MyCleanRoomListBean.InfoBean> mList;

    List<Boolean> mChecked;


    public PerCleanDocAdapter(Context context, List<MyCleanRoomListBean.InfoBean> list) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mList = list;
        mChecked = new ArrayList<>();

        for (int i = 0; i < getCount(); i++) {
            mChecked.add(false);
        }

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
            convertView = mInflater.inflate(R.layout.item_clean_doc, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvRoomCode.setText(mList.get(position).getRoomidcode());
        holder.tvRoomType.setText(mList.get(position).getRoomtypename());
        holder.tvRoomState.setText(mList.get(position).getSys_roomstatecode());
        holder.tvCreateDate.setText(mList.get(position).getCreatedatetime().substring(0,19).replace(" ","\n"));
        holder.mCheckBox.setChecked(mChecked.get(position));

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                mChecked.set(position, cb.isChecked());
            }
        });

        return convertView;
    }

    public List<Boolean> getChecked() {
        return mChecked;
    }


    public void setChecked(boolean isCheck) {
        mChecked.clear();
        for (int i = 0; i < getCount(); i++) {
            mChecked.add(isCheck);
        }
        notifyDataSetChanged();
    }


    static class ViewHolder {
        @Bind(R.id.cb_clean_room)
        AppCompatCheckBox mCheckBox;
        @Bind(R.id.tv_room_code)
        TextView tvRoomCode;
        @Bind(R.id.tv_room_type)
        TextView tvRoomType;
        @Bind(R.id.tv_room_state)
        TextView tvRoomState;
        @Bind(R.id.tv_create_date)
        TextView tvCreateDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
