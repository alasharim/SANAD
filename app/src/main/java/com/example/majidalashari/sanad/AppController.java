package com.example.majidalashari.sanad;

/**
 * Created by MajidAlashari on 10/19/2017.
 */

class AppController {
    private static final AppController ourInstance = new AppController();
    PrayTime prayTime = new PrayTime();
    double longitude = 1;
    double latitude = 1;
    // ------------------------------------------------------------
    // Calculation Methods
    int calculationMethod = prayTime.Makkah;
//        int Jafari; // Ithna Ashari
//        int Karachi; // University of Islamic Sciences, Karachi
//        int ISNA; // Islamic Society of North America (ISNA)
//        int MWL; // Muslim World League (MWL)
//        int Makkah; // Umm al-Qura, Makkah
//        int Egypt; // Egyptian General Authority of Survey
//        int Custom; // Custom Setting
//        int Tehran; // Institute of Geophysics, University of Tehran

    // Juristic Methods
    int juristicMethod = prayTime.Shafii;
//        int Shafii; // Shafii (standard)
//        int Hanafi; // Hanafi

    // Adjusting Methods for Higher Latitudes
    int adjustMethodForHigherLat = prayTime.AngleBased;
//        int None; // No adjustment
//        int MidNight; // middle of night
//        int OneSeventh; // 1/7th of night
//        int AngleBased; // angle/60th of night

    // Time Formats
    int timeFormat = prayTime.Time12;
//        int Time24; // 24-hour format
//        int Time12; // 12-hour format
//        int Time12NS; // 12-hour format with no suffix
//        int Floating; // floating point number
    static AppController getInstance() {
        return ourInstance;
    }

    private AppController() {
    }
}
