package com.loconav.configurator;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by prateek on 10/02/18.
 */

public class CurrentLocation {

    private Context context;
    CurrentLocation(Context context) {
        this.context = context;
    }
    TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
    GsmCellLocation loc = (GsmCellLocation) tm.getCellLocation();

    int cellid = loc.getCid();
    int lac = loc.getLac();


}
