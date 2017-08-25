package com.base.maxb.client.moudle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Log日志打印类
 * <p/>
 * Trace.d和Trace.w不写入日志文件
 * <p/>
 * <p/>
 * Trace.i和Trace.e写入日志文件
 * <p/>
 *
 * @author qinfeifei@ata.net.cn
 */
@SuppressLint("SimpleDateFormat")
public final class Trace {
    public static String TAG = Trace.class.getSimpleName();
    public static DateFormat df = new SimpleDateFormat("yyyyMMdd");
    public static final boolean DEBUG = true;
    public static final String SDCARD = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    public static String LOG_SAVE_DIR = SDCARD + "/trace/";
    static Logger log = Logger.getLogger(TAG);

    public static void init(Context ctx) {
        try {
            File logDir = ctx.getExternalFilesDir(null);
            LOG_SAVE_DIR = logDir.getAbsolutePath();
            ;
            System.out.println(LOG_SAVE_DIR);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            FileHandler fHandler = new FileHandler(LOG_SAVE_DIR + "/" + TAG
                    + "_" + df.format(new Date()) + ".log", true);
            fHandler.setLevel(Level.ALL);
            fHandler.setFormatter(new SimpleFormatter());
            log.addHandler(fHandler);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CrashHandler.init(ctx);
        Trace.i(TAG, "init logs");
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            StringBuffer buf = new StringBuffer();
            buf.append("time:")
                    .append(DateFormat.getDateTimeInstance().format(
                            System.currentTimeMillis())).append("\n");
            buf.append("package:").append(pi.packageName).append("\n");
            buf.append("suid:")
                    .append(pi.sharedUserLabel + "," + pi.sharedUserId)
                    .append("\n");
            buf.append("version:")
                    .append(pi.versionName + "," + pi.versionCode).append("\n");
//            buf.append("rom:").append(SysProp.getSystemVersion()).append("\n");
//            buf.append("mac:").append(Utils.getMacAddress(ctx)).append("\n");
//            buf.append("ip:").append(Utils.getLocalIpAddress()).append("\n");
            Trace.i("init", "" + buf.toString());
        } catch (Exception e) {
        }

    }

    public static void show(Context ctx, String msg) {
        // if (DEBUG) {
        // Toast.makeText(ctx, msg + "", Toast.LENGTH_LONG).show();
        // Trace.i(TAG, "show():" + msg);
        // }
    }

    ;

    //    public static void d(String tag, Object msg) {
//        if (DEBUG) Log.d(TAG, format(tag, gson.toJson(msg)));
//    }
    public static void d(String tag, String msg) {
        if (DEBUG) Log.d(TAG, format(tag, msg));
    }

    public static void w(String tag, String msg) {
        if (DEBUG) Log.w(TAG, format(tag, msg));
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) Log.w(TAG, format(tag, msg), tr);
    }

    public static void i(String tag, String msg) {
        log(Level.INFO, tag, msg, null);
    }

    public static void e(String tag, String msg) {
        log(Level.SEVERE, tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(Level.SEVERE, tag, msg, tr);
    }

    private static void log(Level level, String tag, String msg, Throwable tr) {
        LogRecord record = new LogRecord(level, format(tag, msg));
        record.setThrown(tr);
        record.setLoggerName(tag);
        try {
            StackTraceElement e = new Throwable().getStackTrace()[2];
            record.setSourceClassName(e.getClassName());
            record.setSourceMethodName(e.getMethodName());
        } catch (Exception e) {
        }
        log.log(record);
    }

    private static String format(String tag, String msg) {
        return "<" + tag + "> " + msg + " ";
    }


    static class CrashHandler implements Thread.UncaughtExceptionHandler {
        public static final String TAG = "CrashHandler";
        private static CrashHandler sInst = new CrashHandler();
        private Thread.UncaughtExceptionHandler mDefaultHandler;

        private CrashHandler() {
            mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }

        public static void init(Context ctx) {
            if (sInst == null) sInst = new CrashHandler();
        }

        @Override
        public void uncaughtException(Thread thread, final Throwable ex) {
            if (!handleException(ex) && mDefaultHandler != null) {
                // 如果用户没有处理则让系统默认的异常处理器来处理
                mDefaultHandler.uncaughtException(thread, ex);
            }

            new Thread() {
                @Override
                public void run() {
                    // Looper.prepare();
                    Trace.e(TAG, "未捕获的异常", ex);
                    Trace.e("未捕獲的異常", "原因：" + ex.getLocalizedMessage());
                    android.os.Process.killProcess(android.os.Process.myPid());
                    // System.exit(0);
                    // Looper.loop();
                }
            }.start();
        }

        /**
         * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
         *
         * @param ex
         * @return true:如果处理了该异常信息;否则返回false
         */
        private boolean handleException(final Throwable ex) {
            if (ex == null) {
                return true;
            }
            // new Handler(Looper.getMainLooper()).post(new Runnable() {
            // @Override
            // public void run() {
            // new AlertDialog.Builder(mContext).setTitle("提示")
            // .setMessage("程序崩溃了...").setNeutralButton("我知道了", null)
            // .create().show();
            // }
            // });
            Trace.e(TAG, "应用程序异常", ex);
            return true;
        }
    }
}
