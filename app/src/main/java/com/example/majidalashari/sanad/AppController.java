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
//        Jafari-----------Ithna Ashari
//        Karachi----------University of Islamic Sciences, Karachi
//        ISNA-------------Islamic Society of North America (ISNA)
//        MWL--------------Muslim World League (MWL)
//        Makkah-----------Umm al-Qura, Makkah
//        Egypt------------Egyptian General Authority of Survey
//        Custom-----------Custom Setting
//        Tehran-----------Institute of Geophysics, University of Tehran

    // Juristic Methods
    int juristicMethod = prayTime.Shafii;
//        Shafii-----------Shafii (standard)
//        Hanafi-----------Hanafi

    // Adjusting Methods for Higher Latitudes
    int adjustMethodForHigherLat = prayTime.AngleBased;
//        None-------------No adjustment
//        MidNight---------middle of night
//        OneSeventh-------1/7th of night
//        AngleBased-------angle/60th of night

    // Time Formats
    int timeFormat = prayTime.Time12;
    //        Time24-----------24-hour format
//        Time12-----------12-hour format
//        Time12NS---------2-hour format with no suffix
//        Floating---------Floating point number
// ------------------------------------------------------------
    static AppController getInstance() {
        return ourInstance;
    }

    private AppController() {
    }
}
