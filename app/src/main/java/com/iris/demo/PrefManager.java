package com.iris.demo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PrefManager {
    private static final String PREF_NAME = "iris_demo_prefs";
    private static final String KEY_FAVORITES = "favorite_apps";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static Set<String> getFavorites(Context context) {
        return getPrefs(context).getStringSet(KEY_FAVORITES, new HashSet<String>());
    }

    public static void addFavorite(Context context, String packageName) {
        Set<String> favorites = new HashSet<>(getFavorites(context));
        favorites.add(packageName);
        getPrefs(context).edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    public static void removeFavorite(Context context, String packageName) {
        Set<String> favorites = new HashSet<>(getFavorites(context));
        favorites.remove(packageName);
        getPrefs(context).edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }
}
