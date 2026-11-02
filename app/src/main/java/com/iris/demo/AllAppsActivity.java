package com.iris.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

public class AllAppsActivity extends Activity {

    private GridView allAppsGrid;
    private List<AppInfo> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apps);

        allAppsGrid = findViewById(R.id.allAppsGrid);
        apps = AppUtils.getInstalledApps(this);
        allAppsGrid.setAdapter(new AppAdapter(this, apps));

        allAppsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo app = apps.get(position);
                AppUtils.launchApp(AllAppsActivity.this, app.getPackageName());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
