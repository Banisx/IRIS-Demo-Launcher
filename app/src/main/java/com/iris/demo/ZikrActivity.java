package com.iris.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class PrayerActivity extends Activity {

    private TextView[] prayerTexts = new TextView[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);

        prayerTexts[0] = findViewById(R.id.prayerFajr);
        prayerTexts[1] = findViewById(R.id.prayerSunrise);
        prayerTexts[2] = findViewById(R.id.prayerDhuhr);
        prayerTexts[3] = findViewById(R.id.prayerAsr);
        prayerTexts[4] = findViewById(R.id.prayerMaghrib);
        prayerTexts[5] = findViewById(R.id.prayerIsha);

        PrayerTimesHelper.PrayerSlot[] slots = PrayerTimesHelper.getPrayerTimes();
        for (int i = 0; i < slots.length; i++) {
            prayerTexts[i].setText(slots[i].name + "   " + slots[i].time);
        }
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
