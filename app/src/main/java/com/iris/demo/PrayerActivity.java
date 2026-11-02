package com.iris.demo;

import java.util.Calendar;
import java.util.Locale;

public class PrayerTimesHelper {

    public static class PrayerSlot {
        public final String name;
        public final String time;
        public final int hour;
        public final int minute;

        public PrayerSlot(String name, String time, int hour, int minute) {
            this.name = name;
            this.time = time;
            this.hour = hour;
            this.minute = minute;
        }
    }

    public static class PrayerInfo {
        public final String name;
        public final String time;
        public final String remaining;

        public PrayerInfo(String name, String time, String remaining) {
            this.name = name;
            this.time = time;
            this.remaining = remaining;
        }
    }

    public static PrayerSlot[] getPrayerTimes() {
        return new PrayerSlot[] {
                new PrayerSlot("الفجر", "05:10", 5, 10),
                new PrayerSlot("الشروق", "06:38", 6, 38),
                new PrayerSlot("الظهر", "12:45", 12, 45),
                new PrayerSlot("العصر", "16:08", 16, 8),
                new PrayerSlot("المغرب", "18:41", 18, 41),
                new PrayerSlot("العشاء", "20:05", 20, 5)
        };
    }

    public static PrayerInfo getNextPrayerInfo() {
        Calendar now = Calendar.getInstance();
        int currentMinutes = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);

        for (PrayerSlot slot : getPrayerTimes()) {
            int slotMinutes = slot.hour * 60 + slot.minute;
            if (slotMinutes > currentMinutes) {
                int diff = slotMinutes - currentMinutes;
                int hours = diff / 60;
                int minutes = diff % 60;
                String remaining = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
                return new PrayerInfo(slot.name, slot.time, remaining + " حتى الأذان");
            }
        }

        PrayerSlot first = getPrayerTimes()[0];
        return new PrayerInfo(first.name, first.time, "24:00 حتى الأذان");
    }
}
