package cn.houno.hotel.home.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.houno.hotel.R;
import cn.houno.hotel.home.bean.BookQueryListBean;


/**
 * Created by 123 on 2018/1/30.
 */

public class BookQueryEXpAdapter extends BaseExpandableListAdapter {
    private  ArrayList<String> mList;
    private Context mContext;
    private  Map<String, List<BookQueryListBean.InfoBean>> children;

    public BookQueryEXpAdapter(Context mContext, ArrayList<String> mList, Map<String, List<BookQueryListBean.InfoBean>> children) {
         this.mContext=mContext;
         this.mList=mList;
         this.children = children;

    }
    public void setData(  ArrayList<String> list, Map<String, List<BookQueryListBean.InfoBean>> children) {
        this.mList = list;
        this.children = children;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId=mList.get(groupPosition);
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public BookQueryListBean.InfoBean getChild(int groupPosition, int childPosition) {
              String groupId=mList.get(groupPosition);

        return children.get(groupId).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_book_query_group, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
              gholder.txExpandChild.setText(mList.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_book_query, null);
            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        final BookQueryListBean.InfoBean infoBean =  getChild(groupPosition, childPosition);

        cholder.tvPriceCode.setText(Html.fromHtml("房价码：<font color='#838281'>"
                + infoBean.getRoomratemainidcode() + "</font>"));
        cholder.tvTypeCode.setText(Html.fromHtml("房型码：<font color='#838281'>"
                + infoBean.getRoomtypeidcode() + "</font>"));
        cholder.tvPrice.setText(Html.fromHtml("房　价：<font color='#838281'>"
                + infoBean.getAmountsex() + "</font>"));
        cholder.tvPackageCode.setText(Html.fromHtml("套餐码：<font color='#838281'>"
                + infoBean.getPackageheaderidcode() + "</font>"));
        String isdiscount = infoBean.getIsdiscount();
        if (TextUtils.equals("0", isdiscount)) {
            //无折扣
            cholder.tvDiscountPrice.setText(Html.fromHtml("<font color='#e25558'>" + "折扣价："
                    + infoBean.getDiscountamounts() + "</font>"));
        } else {
            cholder.tvDiscountPrice.setText(Html.fromHtml("<font color='#84c225'>" + "折扣价："
                    + infoBean.getDiscountamounts() + "</font>"));
        }

        cholder.tvDate.setText(Html.fromHtml("日　期：<font color='#838281'>"
                + infoBean.getBusinessday().substring(0, 10) + "</font>"));
        cholder.tvRoomNumber.setText(Html.fromHtml("房间数：<font color='#838281'>"
                + infoBean.getRoomcount() + "</font>"));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        @Bind(R.id.tx_expand_group)
        TextView txExpandGroup;
        @Bind(R.id.tx_expand_child)
        TextView txExpandChild;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
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

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }



}
