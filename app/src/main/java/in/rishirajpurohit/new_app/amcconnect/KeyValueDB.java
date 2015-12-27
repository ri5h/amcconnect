package in.rishirajpurohit.new_app.amcconnect;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rex on 26-12-2015.
 */
public class KeyValueDB {
    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "prefs";

    public KeyValueDB() {
        // Blank
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getUsername(Context context) {
        return getPrefs(context).getString("user_email", "default_username");
    }

    public static void setUsername(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("user_email", input);
        editor.commit();
    }
    public static String getLevel(Context context) {
        return getPrefs(context).getString("level", "level 0");
    }

    public static void setLevel(Context context, String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("level", input);
        editor.commit();
    }
}