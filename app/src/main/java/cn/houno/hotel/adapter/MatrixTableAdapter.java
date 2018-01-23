package cn.houno.hotel.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;

import cn.houno.hotel.view.BorderTextView;

public class MatrixTableAdapter<T> extends BaseTableAdapter {

    private static int WIDTH_DIP = 110;
    private static int HEIGHT_DIP = 32;

    private Context context;

    private T[][] table;

    private int width;
    private int height;

    public MatrixTableAdapter(Context context) {
        this(context, null);
    }

    public MatrixTableAdapter(Context context, T[][] table) {
        this.context = context;
        Resources r = context.getResources();

        width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
        height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));

        setInformation(table);
    }


    public void setData(Context context, T[][] table) {
        this.context = context;
        Resources r = context.getResources();

        width = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, WIDTH_DIP, r.getDisplayMetrics()));
        height = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEIGHT_DIP, r.getDisplayMetrics()));
        setInformation(table);
        notifyDataSetChanged();
    }

    public void setInformation(T[][] table) {
        this.table = table;
    }

    @Override
    public int getRowCount() {
        return table.length - 1;
    }

    @Override
    public int getColumnCount() {
        return table[0].length - 1;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new BorderTextView(context);
            ((BorderTextView) convertView).setGravity(Gravity.CENTER);
        }
        ((BorderTextView) convertView).setText(table[row + 1][column + 1].toString());
        return convertView;
    }

    @Override
    public int getHeight(int row) {
        return height;
    }

    @Override
    public int getWidth(int column) {
        if (column == -1) {
            return width;
        } else {
            return width / 2;
        }
    }

    @Override
    public int getItemViewType(int row, int column) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
