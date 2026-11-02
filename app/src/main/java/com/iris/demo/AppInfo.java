package com.iris.demo;

public class AppInfo {
    private final String appName;
    private final String packageName;
    private final android.graphics.drawable.Drawable icon;

    public AppInfo(String appName, String packageName, android.graphics.drawable.Drawable icon) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public android.graphics.drawable.Drawable getIcon() {
        return icon;
    }
}
