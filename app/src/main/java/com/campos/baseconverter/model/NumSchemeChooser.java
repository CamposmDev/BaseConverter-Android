package com.campos.baseconverter.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.campos.baseconverter.app.App;

public class NumSchemeChooser {
    public static void save(Context c, int code) {
        SharedPreferences pref = c.getSharedPreferences(App.SETTINGS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(App.NUM_SCHEME_KEY, code);
        editor.apply();
//        try {
//            FileOutputStream fis = c.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream oos = new ObjectOutputStream(fis);
//            oos.writeInt(code);
//            oos.close();
//            Log.d(TAG, "Successfully saved number scheme!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static int load(Context c) {
        SharedPreferences pref = c.getSharedPreferences(App.SETTINGS_FILE, Context.MODE_PRIVATE);
        return pref.getInt(App.NUM_SCHEME_KEY, -1);
//        int code = -1;
//        try {
//            FileInputStream fis = c.openFileInput(FILE_NAME);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            code = ois.readInt();
//            ois.close();
//            Log.d(TAG, "Successfully loaded number scheme!");
//        } catch (IOException e) {
//            Log.d(TAG, "Failed t load nunber scheme :(");
//        } finally {
//            setScheme(c, code);
//            return code;
//        }
    }
}
