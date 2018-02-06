package cn.houno.hotel.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;

/**
 * Created by 123 on 2018/1/31.
 */

public class HomeAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final String[] mText;
    private final int[] mImage;
    private Context mContext;

    public HomeAdapter(Context context, String[] text, int[] image) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mText = text;
        mImage = image;
    }

    @Override
    public int getCount() {
        return mText.length;
    }

    @Override
    public Object getItem(int position) {
        return mText[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_home, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.llContainer.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        holder.image.setImageResource(mImage[position]);
        holder.title.setText(mText[position]);
        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.ll_container)
        LinearLayout llContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
