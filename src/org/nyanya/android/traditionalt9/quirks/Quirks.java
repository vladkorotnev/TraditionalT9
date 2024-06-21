package org.nyanya.android.traditionalt9.quirks;

import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;

import org.nyanya.android.traditionalt9.quirks.kyocera.KYY31Quirks;

public class Quirks {
    public static IQuirks getCurrentPhoneQuirks() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        Log.v("T9_Quirks", "Get quirks for "+model+" of "+manufacturer);

        switch (manufacturer) {
            case "SHARP":
                if (model.equals("SH-01J")) {
                    return new SoftkeyQuirks(KeyEvent.KEYCODE_F1, KeyEvent.KEYCODE_F2);
                }
                break;
            case "KYOCERA":
                if (model.equals("KYY31")) {
                    return new KYY31Quirks();
                }
                break;
            case "PANASONIC":
                if (model.equals("P-01J")) {
                    return new SoftkeyQuirks(KeyEvent.KEYCODE_MENU, KeyEvent.KEYCODE_F2);
                }
                break;
        }

        return new SoftkeyQuirks();
    }
}
