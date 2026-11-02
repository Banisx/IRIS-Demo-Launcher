package com.iris.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends Activity {

    private GridView favoritesGrid;
    private Button btnAllApps;
    private Button btnSettings;
    private TextView tvClock;

    private List<AppInfo> favoriteApps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoritesGrid = findViewById(R.id.favoritesGrid);
        btnAllApps = findViewById(R.id.btnAllApps);
        btnSettings = findViewById(R.id.btnSettings);
        tvClock = findViewById(R.id.tvClock);

        updateClock();
        loadFavorites();
        setupListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
        updateClock();
    }

    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        tvClock.setText(sdf.format(new Date()));
    }

    private void loadFavorites() {
        Set<String> selectedPackages = PrefManager.getFavorites(this);
        List<AppInfo> allApps = AppUtils.getInstalledApps(this);

        favoriteApps.clear();

        for (AppInfo app : allApps) {
            if (selectedPackages.contains(app.getPackageName())) {
                favoriteApps.add(app);
            }
        }

        if (favoriteApps.isEmpty()) {
            for (int i = 0; i < Math.min(8, allApps.size()); i++) {
                favoriteApps.add(allApps.get(i));
            }
        }

        AppAdapter adapter = new AppAdapter(this, favoriteApps);
        favoritesGrid.setAdapter(adapter);
    }

    private void setupListeners() {
        favoritesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo app = favoriteApps.get(position);
                AppUtils.launchApp(MainActivity.this, app.getPackageName());
            }
        });

        btnAllApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllAppsActivity.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }
}
