package cn.houno.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;

/**
 * Created by qzc on 2017/5/8 0008.
 */

public class RommTypeAdapter extends BaseAdapter {


    @Bind(R.id.tv_category)
    TextView tvCategory;

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] mCategories;
    private String[] mRoomCount;


    public RommTypeAdapter(Context context, String[] categories, String[] roomCount) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCategories = categories;
        mRoomCount = roomCount;
    }

    @Override
    public int getCount() {
        return mCategories.length;
    }

    @Override
    public Object getItem(int position) {
        return mCategories[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_room_type_grid, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.app_theme_light_black));
        holder.tvCategory.setText(mCategories[position]);
        holder.tvCount.setText(mRoomCount[position]);
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_category)
        TextView tvCategory;
        @Bind(R.id.tv_count)
        TextView tvCount;
        @Bind(R.id.ll_container)
        LinearLayout llContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }




    /*static class ViewHolder {
        @Bind(R.id.tv_category)
        TextView tvCategory;
        @Bind(R.id.ll_container)
        LinearLayout llContainer;
        @Bind(R.id.tv_count)
        TextView tvCount;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
*/

}
