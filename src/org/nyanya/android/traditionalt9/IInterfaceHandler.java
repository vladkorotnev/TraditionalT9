package org.nyanya.android.traditionalt9;

import android.inputmethodservice.InputMethodService;
import android.view.View;

public interface IInterfaceHandler {
    View getView();
    void rebuildView(InputMethodService svc);
    void showHold(boolean show);
    void midButtonUpdate(boolean composing);
    void setPressed(int keyCode, boolean pressed);
    void showNotFound(boolean notFound);
}
