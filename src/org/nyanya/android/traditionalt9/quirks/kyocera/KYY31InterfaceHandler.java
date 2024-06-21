package org.nyanya.android.traditionalt9.quirks.kyocera;

import android.inputmethodservice.InputMethodService;
import android.view.View;

import org.nyanya.android.traditionalt9.IInterfaceHandler;
import org.nyanya.android.traditionalt9.R;
import org.nyanya.android.traditionalt9.TraditionalT9;

public class KYY31InterfaceHandler implements IInterfaceHandler {
    private TraditionalT9 parent;
    private KYY31SoftkeyGuide guide;
    private View dummy;

    private final static int MID_SOFTKEY_IDX = 0;
    private final static int LEFT_SOFTKEY_IDX = 1;
    private final static int RIGHT_SOFTKEY_IDX = 2;

    public KYY31InterfaceHandler(TraditionalT9 p) {
        parent = p;
        dummy = new View(p);
        dummy.setVisibility(View.GONE);
        rebuildView(p);
    }

    @Override
    public View getView() {
        return dummy;
    }

    @Override
    public void rebuildView(InputMethodService svc) {
        guide = KYY31SoftkeyGuide.createFor(svc.getWindow().getWindow());

        if(guide == null) return;

        guide.setText(MID_SOFTKEY_IDX, R.string.main_mid);
        guide.setText(LEFT_SOFTKEY_IDX, R.string.main_left_insert);
        guide.setText(RIGHT_SOFTKEY_IDX, R.string.main_right_upper);
        guide.invalidate();
    }

    @Override
    public void showHold(boolean show) {

    }

    @Override
    public void midButtonUpdate(boolean composing) {
        if(guide == null) return;

        guide.setText(MID_SOFTKEY_IDX, composing ? R.string.main_mid_commit : R.string.main_mid);
        guide.invalidate();
    }

    @Override
    public void setPressed(int keyCode, boolean pressed) {
        // no-op
    }

    @Override
    public void showNotFound(boolean notFound) {
        if(guide == null) return;

        if(notFound) {
            guide.setText(LEFT_SOFTKEY_IDX, R.string.main_left_notfound);
        } else {
            guide.setText(LEFT_SOFTKEY_IDX, R.string.main_left_insert);
        }
        guide.invalidate();
    }
}
