package com.andr.simon.simonlib;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by jk on 2017-02-06.
 * 안드로이드 라이브러리 기본유틸
 */
public class CommonUtil {

    private static Toast mToast = null;
    private static boolean mFlag = false;
    private static Handler handler;
    private static Runnable runnable;

    /**
     * Long Toast 출력
     * @param o , msg
     */
    public static void showToast(Object o, String msg){
        showCustomToast((Context) o, msg, Toast.LENGTH_LONG);
    }

    /**
     * custom Toast 출력
     * @param context , msg , duration
     */
    public static void showToast(Context context, CharSequence message, int duration) {
        try {
            Activity act = (Activity)context;
            LayoutInflater inflater = act.getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast_layout,
                    (ViewGroup) act.findViewById(R.id.toast_layout_root));
            TextView msgView = (TextView)layout.findViewById(R.id.toastMessage);
            msgView.setText(message);

            if (mFlag == false) {
                mToast = new Toast(context);
                mToast.setGravity(Gravity.BOTTOM, 0, 100);
                mToast.setDuration(duration);
                mToast.setView(layout);
                mToast.show();

                mFlag = true;

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        mFlag = false;
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        }catch (Exception e){
            showToastOri(context, message, duration);
        }
    }

    /**
     * Custom Toast
     *
     */
    private static void showCustomToast(Context context, String msg, int duration){
        try {
            //Retrieve the layout inflator
            Activity act = (Activity)context;
            LayoutInflater inflater = act.getLayoutInflater();
            //Assign the custom layout to view
            // Parameter 1 - Custom layout XML
            // Parameter 2 - Custom layout ID present in linearlayout tag of XML
            View layout = inflater.inflate(R.layout.custom_toast_layout,
                    (ViewGroup) act.findViewById(R.id.toast_layout_root));
            TextView msgView = (TextView)layout.findViewById(R.id.toastMessage);
            msgView.setText(msg);
            //Return the application context
            Toast toast = new Toast(context);
            //Set toast gravity to bottom
            toast.setGravity(Gravity.BOTTOM, 0, 100);
            //Set toast duration
            toast.setDuration(duration);
            //Set the custom layout to Toast
            toast.setView(layout);
            //Display toast
            toast.show();
        }catch (Exception e){
            showToastOri(context, msg, duration);
        }
    }

    //Short Toast출력(표시시간지정)
    public static void showToastOri(Context context, CharSequence message, int duration) {
        try {
            if (mFlag == false) {
                mToast = Toast.makeText(context, message, duration);
                mToast.show();

                mFlag = true;

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        mFlag = false;
                    }
                };
                handler.postDelayed(runnable, 2000);
            }
        }catch (Exception e){}
    }

}
