package cn.houno.hotel.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by qzc on 2017-06-06.
 */

public class DatePickerDialog extends android.app.DatePickerDialog{

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DatePickerDialog(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public DatePickerDialog(Context context, OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }

    public DatePickerDialog(Context context, int themeResId, OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, themeResId, listener, year, monthOfYear, dayOfMonth);
    }
}
