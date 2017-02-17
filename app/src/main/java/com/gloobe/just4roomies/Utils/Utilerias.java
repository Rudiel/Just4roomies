package com.gloobe.just4roomies.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.net.HttpURLConnection;
import java.net.URL;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by rudielavilaperaza on 03/10/16.
 */
public class Utilerias extends Activity {

    public static final String URL_GLOBAL = "http://45.55.161.90/just4rommies/public/api/";

    public static boolean isOnline(Context context) {

        if (haveNetworkConnection(context))
            return true;
        else
            return false;
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static boolean isInternetAvailable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            Object objData = urlConnect.getContent();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


    public static void SaveBatch(Context context, String chatID) {

        int badgeCount;
        int total;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        badgeCount = preferences.getInt("CHAT" + chatID, 0);
        badgeCount = badgeCount + 1;

        total = preferences.getInt("CHAT", 0);
        total = total + 1;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("CHAT" + chatID, badgeCount);
        editor.putInt("CHAT", total);
        editor.apply();

        ShortcutBadger.applyCount(context, total); //for 1.1.4+

    }

    public static void RemoveBatch(Context context, String chatID) {

        int badgeCount;
        int total;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        badgeCount = preferences.getInt("CHAT" + chatID, 0);
        total = preferences.getInt("CHAT", 0);

        preferences.edit().remove("CHAT" + chatID).apply();
        preferences.edit().putInt("CHAT", total - badgeCount).apply();

        ShortcutBadger.applyCount(context, total - badgeCount);

    }

    public static String getPatchChat(Context context, String chatID) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return String.valueOf(preferences.getInt("CHAT" + chatID, 0));
    }

    public static void saveNotificationsEnable(Context context, Boolean isEnable) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("NOTIFICATIONS_ENABLE", isEnable);
        editor.apply();
    }

    public static boolean getNotificationsEnabled(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("NOTIFICATIONS_ENABLE", true);
    }

    public static void saveLocationEnable(Context context, Boolean isEnable) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("LOCATION_ENABLE", isEnable);
        editor.apply();
    }

    public static boolean getLocationEnabled(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean("LOCATION_ENABLE", true);
    }

    public static Typeface getMavenProRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/MavenPro_Regular.ttf");
    }


}
