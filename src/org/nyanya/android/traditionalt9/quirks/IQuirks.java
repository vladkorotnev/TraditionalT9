package org.nyanya.android.traditionalt9.quirks;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public interface IQuirks {
    void onStart(InputConnection inputConnection);
    void onFinish(InputConnection inputConnection);
    void onBeginEditing(InputConnection inputConnection);
    void onEndEditing(InputConnection inputConnection);
    int getLeftSoftKey();
    int getRightSoftKey();
}
