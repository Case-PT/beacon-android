package li.brianv.rescueme.util;

import li.brianv.rescueme.BuildConfig;

public class Log {
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void i(String tag, String string) {
        if (DEBUG) android.util.Log.i(tag, string);
    }

    public static void e(String tag, Throwable throwable) {
        if (DEBUG) android.util.Log.e(tag, throwable.toString());
    }

    public static void d(String tag, String string) {
        if (DEBUG) android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (DEBUG) android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (DEBUG) android.util.Log.w(tag, string);
    }
}