package com.jey.mymvp.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jey.mymvp.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

import static android.R.attr.x;

/**
 * 工具类
 * Created by xyan on 2016/8/4.
 */
public class SystemUtil {

    /**
     * 检查WIFI是否连接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifiInfo != null;
    }
    /**
     * 检查手机网络(4G/3G/2G)是否连接
     */
    public static boolean isMobileNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mobileNetworkInfo != null;
    }


    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 保存文字到剪贴板
     * @param context
     * @param text
     */
    public static void copyToClipBoard(Context context, String text) {
        ClipData clipData = ClipData.newPlainText("url", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        Log.i("SystemUtil","已复制到剪贴板");
    }

    /**
     * 保存图片到本地
     * @param context
     * @param url
     * @param bitmap
     */
    public static Uri saveBitmapToFile(Context context, String url, Bitmap bitmap, View container, boolean isShare){
        String fileName = url.substring(url.lastIndexOf("/"),url.lastIndexOf(".")) + ".png";
        File fileDir = new File(ConstUtil.PATH_DATA);
        if (!fileDir.exists()){
            fileDir.mkdir();
        }
        File imageFile = new File(fileDir,fileName);
        Uri uri = Uri.fromFile(imageFile);
        if (isShare && imageFile.exists()) {
            return uri;
        }
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            boolean isCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            if (isCompress) {
                Log.i("SystemUtil","保存成功");
            } else {
                Log.i("SystemUtil","保存失败");
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("SystemUtil","保存失败");
        }
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), imageFile.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri));
        return uri;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(float dpValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取文本宽度
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 获取窗口宽度
     * @return
     */
    public static int getWindowWidth(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return  metric.heightPixels;   // 屏幕高度（像素）
    }


    /**
     * 获取android 版本
     * @return
     */
    public static String getAndroidVersion(){
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel(){
        return  android.os.Build.MODEL;
    }

    /**
     * 获取本地IP
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            // Log.e("WifiPreference IpAddress", ex.getMessage());
        }
        return null;
    }

    /**
     * 修改TextView ImageSpan 实现gif图片的图文混排
     * @param textView
     * @param html
     */
    public  static void setWord(TextView textView, String html){
        Spanned spanned= Html.fromHtml(html);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            textView.setTextIsSelectable(true);
        }
        textView.setText(spanned);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = textView.getText();
        if(text instanceof Spannable){
            int end = text.length();
            Spannable sp = (Spannable)text;
            ImageSpan[] imgs = sp.getSpans(0,end,ImageSpan.class);
            SpannableStringBuilder style=new SpannableStringBuilder(text);
            //style.set;
            for(ImageSpan url : imgs){
              //  ImageSpan span = new ImageSpan(getUrlDrawable(url.getSource(),textView),url.getSource());
              //  style.setSpan(span,sp.getSpanStart(url),sp.getSpanEnd(url),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

            textView.setText(style);
        }

    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className
     *            某个界面名称
     */
    private boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 减法精度
     * @param start
     * @param end
     * @return
     */
    public  static  float getDiffer(float start,float end){
        return  (new BigDecimal(Float.toString(start))).subtract(new BigDecimal(Float.toString(end))).floatValue();
    }
    /**
     * 加法精度
     * @param start
     * @param end
     * @return
     */
    public  static  float getAdd(float start,float end){
        return  (new BigDecimal(Float.toString(start))).add(new BigDecimal(Float.toString(end))).floatValue();
    }
    /**
     * 乘法精度
     * @param start
     * @param end
     * @return
     */
    public  static  float getMultiply(float start,float end){
        return  (new BigDecimal(Float.toString(start))).multiply(new BigDecimal(Float.toString(end))).floatValue();
    }
    /**
     * 除法精度
     * @param start
     * @param end
     * @return
     */
    public  static  float getDivide(float start,float end){
        return  (new BigDecimal(Float.toString(start))).divide(new BigDecimal(Float.toString(end))).floatValue();
    }
    /**
     * 1 public BigDecimal add(BigDecimal value);                        //加法
     2 public BigDecimal subtract(BigDecimal value);                   //减法
     3 public BigDecimal multiply(BigDecimal value);                   //乘法
     4 public BigDecimal divide(BigDecimal value);                     //除法
     */

}
