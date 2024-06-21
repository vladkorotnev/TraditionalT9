package org.nyanya.android.traditionalt9.quirks;

import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class SoftkeyQuirks implements IQuirks {
    int leftSoftKey = KeyEvent.KEYCODE_SOFT_LEFT;
    int rightSoftKey = KeyEvent.KEYCODE_SOFT_RIGHT;

    public SoftkeyQuirks(int left, int right) {
        leftSoftKey = left;
        rightSoftKey = right;
    }

    public SoftkeyQuirks() {
        this(KeyEvent.KEYCODE_SOFT_LEFT, KeyEvent.KEYCODE_SOFT_RIGHT);
    }

    @Override
    public void onStart(InputConnection inputConnection) {}

    @Override
    public void onFinish(InputConnection inputConnection) {}

    @Override
    public void onBeginEditing(InputConnection inputConnection) {}

    @Override
    public void onEndEditing(InputConnection inputConnection) {}

    @Override
    public int getLeftSoftKey() {
        return leftSoftKey;
    }

    @Override
    public int getRightSoftKey() {
        return rightSoftKey;
    }
}

