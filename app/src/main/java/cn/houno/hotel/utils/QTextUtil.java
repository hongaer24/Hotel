package cn.houno.hotel.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import java.text.DecimalFormat;

import cn.houno.hotel.R;

/**
 * 文字工具类
 * Created by qzc on 2017-03-15.
 */

public class QTextUtil {

    public static String getResult(String result) {
        int begin = result.indexOf("\"");
        int finish = result.lastIndexOf("\"");
        if (begin == 0 && finish == result.length() - 1) {
            result = result.substring(1, result.length() - 1);
        }
        result = result.replace("\\\"", "\"");
        return result;
    }

    /*
    * 替换带斜线的内容
    * */
    public static String replaceContent(String result) {
        if (result.contains("\\\\")) {
            result = result.replace("\\\\r\\\\n", "\r\n");
        }

        if (result.contains("\\")){
            result = result.replace("\\r\\n", "\r\n");
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null"))
            return true;
        else
            return false;
    }

    public static String replaceSubString(String str, int start, int end) {
        if (!TextUtils.isEmpty(str) && str.length() > end) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (i >= start && i <= end) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return "";
    }

    /*
    * 如果有小数点后面全是零则返回整型，否则全部返回
    * */
    public static String getIntegerString(double number) {
        String afterN;
        if (number - Math.floor(number) == 0) {
            afterN = (int) number + "";
        } else {
            afterN = number + "";
        }
        return afterN;
    }


    /**
     * 定义常量
     **/
    public static final int PRICE_FULL = 1; // ¥180元
    public static final int PRICE_END = 2;    //180元
    public static final int PRICE_START = 3;    //¥180


    public static String getQiPrice(String price) {
        return getQiPrice(price, PRICE_FULL);
    }

    /*
    * 时间补零
    * */
    public static String supplementZero( int i) {
        return i < 10 ? "0" + i : "" + i;
    }

    public static String getQiPrice(String price, int type) {

        if (price != null || !TextUtils.isEmpty(price)) {
            if (type == PRICE_FULL) {
                return "¥" + getIntPrice(price) + "起";
            } else if (type == PRICE_END) {
                return getIntPrice(price) + "起";
            } else if (type == PRICE_START) {
                return "¥" + getIntPrice(price);
            }
        } else {
            return null;
        }
        return price;
    }

    public static String getIntPrice(String price) {
        if (price.indexOf(".") > 0) {
            //正则表达
            price = price.replaceAll("0+?$", "");//去掉后面无用的零
            price = price.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return price;
    }

    /*
    * 保留两位小数
    * */
    public static String getDoublePrice(double n) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(n);
    }



    public static boolean isTel(String phone) {
        boolean isTel = true;
        //判断输入的用户名是否是电话号码
        if (phone.length() == 11) {
            for (int i = 0; i < phone.length(); i++) {
                char c = phone.charAt(i);
                if (!Character.isDigit(c)) {
                    isTel = false;
                    break;//只要有一位不符合要求退出循环
                }
            }
        } else {
            isTel = false;
        }
        return isTel;
    }
}
