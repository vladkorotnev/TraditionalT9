package org.nyanya.android.traditionalt9;

import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;

import su.genjit.jqueserasera.PointerManager;

public class Quirks {
    public static Quirks getCurrentPhoneQuirks() {
        Quirks q = new Quirks();

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        Log.v("T9_Quirks", "Get quirks for "+model+" of "+manufacturer);

        switch (manufacturer) {
            case "SHARP":
                if (model.equals("SH-01J")) {
                    q.leftSoftKey = KeyEvent.KEYCODE_F1;
                    q.rightSoftKey = KeyEvent.KEYCODE_F2;
                }
                break;
            case "KYOCERA":
                if (model.equals("KYY31")) {
                    q.leftSoftKey = KeyEvent.KEYCODE_F1;
                    q.rightSoftKey = KeyEvent.KEYCODE_F2;

                    // Pointer interferes with choosing candidates on the KYY31, temporarily disable it
                    // using my shiny new reverse engineered class
                    q.onStartEdit = () -> {
                        Log.i("T9_Quirks", "KYY31 PreStart");
                        try {
                            PointerManager pmg = new PointerManager();
                            pmg.setPointerVisible(false); // for some reason temporary is not working here
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    };
                }
                break;
            case "PANASONIC":
                if (model.equals("P-01J")) {
                    q.leftSoftKey = KeyEvent.KEYCODE_MENU;
                    q.rightSoftKey = KeyEvent.KEYCODE_F2;
                }
                break;
        }

        return q;
    }

    public int leftSoftKey = KeyEvent.KEYCODE_SOFT_LEFT;
    public int rightSoftKey = KeyEvent.KEYCODE_SOFT_RIGHT;
    public Runnable onStartEdit = null;
    public Runnable onFinishEdit = null;

    public void runStartHooks() {
        if(onStartEdit != null) onStartEdit.run();
    }

    public void runEndHooks() {
        if(onFinishEdit != null) onFinishEdit.run();
    }
}
