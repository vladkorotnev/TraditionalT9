package org.nyanya.android.traditionalt9.quirks.kyocera;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

import org.nyanya.android.traditionalt9.IInterfaceHandler;
import org.nyanya.android.traditionalt9.quirks.SoftkeyQuirks;

public class KYY31Quirks extends SoftkeyQuirks {
    private static final String PRIVATE_IME_COMMAND_ACTION_FINISH = "Finish_IME";
    private static final String PRIVATE_IME_COMMAND_ACTION_START = "Start_IME";
    private static final String PRIVATE_IME_COMMAND_BUNDLE_IS_ACTIVE_FINISH = "isActiveFinish";
    private boolean isEditing = false;

    public KYY31Quirks() {
        super(KeyEvent.KEYCODE_F1, KeyEvent.KEYCODE_F2);
    }

    @Override
    public void onFinish(InputConnection inputConnection) {
        if(isEditing) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(PRIVATE_IME_COMMAND_BUNDLE_IS_ACTIVE_FINISH, true);
            inputConnection.performPrivateCommand(PRIVATE_IME_COMMAND_ACTION_FINISH, bundle);
            isEditing = false;
        }
        super.onEndEditing(inputConnection);
    }

    @Override
    public void onBeginEditing(InputConnection inputConnection) {
        if(!isEditing) {
            // Hide the cursor to prevent it from interfering with the D-pad
            inputConnection.performPrivateCommand(PRIVATE_IME_COMMAND_ACTION_START, null);
            isEditing = true;
        }
        super.onBeginEditing(inputConnection);
    }

    @Override
    public void onEndEditing(InputConnection inputConnection) {
        if(isEditing) {
            // Show the cursor again if needed
            Bundle bundle = new Bundle();
            bundle.putBoolean(PRIVATE_IME_COMMAND_BUNDLE_IS_ACTIVE_FINISH, true);
            inputConnection.performPrivateCommand(PRIVATE_IME_COMMAND_ACTION_FINISH, bundle);
            isEditing = false;
        }
        super.onEndEditing(inputConnection);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IInterfaceHandler> Class<T> customInterfaceHandler() {
        return (Class<T>) KYY31InterfaceHandler.class;
    }
}
