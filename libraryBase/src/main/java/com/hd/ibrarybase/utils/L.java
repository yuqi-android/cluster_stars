package com.hd.ibrarybase.utils;

import android.util.Log;


public class L {

    public static boolean sDeBug=true;

    private final static String TAG = "log--->";

    public static void e(String s) {
        e(TAG, s);
    }

    public static void e(String tag, String s) {
      if (sDeBug) {
          Log.e(tag, s);
      }

    }

    public static void d(String tag, String s) {
       if (sDeBug) {
           Log.d(tag, s);
       }
    }
    public static void setDeBug(boolean deBug) {
        sDeBug = deBug;
    }
}
