package cn.houno.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;

/**
 * Created by qzc on 2017/5/8 0008.
 */

public class WorkCategoryAdapter extends BaseAdapter {

    @Bind(R.id.tv_category)
    TextView tvCategory;

    private Context mContext;
    private LayoutInflater mInflater;
    private String [] mCategories;


    public WorkCategoryAdapter(Context context,String []categories) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCategories = categories;
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
            convertView = mInflater.inflate(R.layout.item_square_grid, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvCategory.setBackgroundResource(R.drawable.selector_btn_org);
        holder.tvCategory.setText(mCategories[position]);
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_category)
        TextView tvCategory;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
