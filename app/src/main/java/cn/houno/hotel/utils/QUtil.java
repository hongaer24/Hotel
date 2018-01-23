package cn.houno.hotel.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.image.ImageOptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.houno.hotel.R;
import cn.houno.hotel.view.CustomDialog;

/**
 * Created by qzc on 2017-03-16.
 */

public class QUtil {


    //防止快速连续点击
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /*
    * 拨打400客服
    * */
    public static void showDialogCallTheNumber(final Context context) {

        CustomDialog.Builder callDialog = new CustomDialog.Builder(context);
        callDialog.setMessage("是否拨打客服电话 400-030-1679");
        callDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4000301679"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        callDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        callDialog.create().show();
    }

    /*
    * md5加密
    * */
    public static String md5(String string, String slat) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    static ImageOptions options;
    static ImageOptions fOptions;
    static ImageOptions hOptions;

    public static ImageOptions getImageOptions() {
        if (options == null) {
            options = new ImageOptions.Builder().setFadeIn(true)
                    // .setLoadingDrawableId(R.drawable.loading_default_img)
                    .build();
        }
        return options;
    }




    public static ImageOptions getCircularHeaderOptions() {
        if (fOptions == null) {
            fOptions = new ImageOptions.Builder().setFadeIn(true)
                    //  .setFailureDrawableId(R.drawable.icon_header_failure)
                    .setCircular(true)
                    .build();
        }
        return fOptions;
    }

    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.pd_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.anim_pd_loading);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        if (TextUtils.isEmpty(msg)) {
            tipTextView.setVisibility(View.GONE);
        } else {
            tipTextView.setText(msg);// 设置加载信息
        }
        Dialog loadingDialog = new Dialog(context, R.style.style_loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;

    }


}
