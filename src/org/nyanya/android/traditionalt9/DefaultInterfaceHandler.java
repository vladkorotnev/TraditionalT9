package org.nyanya.android.traditionalt9;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.nyanya.android.traditionalt9.quirks.IQuirks;
import org.nyanya.android.traditionalt9.quirks.Quirks;

public class DefaultInterfaceHandler implements View.OnClickListener, View.OnLongClickListener, IInterfaceHandler {

	private static final int[] buttons = { R.id.main_left, R.id.main_right, R.id.main_mid };
	private TraditionalT9 parent;
	private View mainview;
	private final IQuirks mQuirks = Quirks.getCurrentPhoneQuirks();

	public DefaultInterfaceHandler(TraditionalT9 iparent) {
		mainview = iparent.getLayoutInflater().inflate(R.layout.mainview, null);
		this.parent = iparent;
		changeView(mainview);
	}

	@Override
	public void rebuildView(InputMethodService svc) {
		mainview = svc.getLayoutInflater().inflate(R.layout.mainview, null);
		changeView(mainview);
	}

	private void changeView(View v) {
		this.mainview = v;
		View button;
		for (int buttid : buttons) {
			button = v.findViewById(buttid);
			button.setOnClickListener(this);
			if (!parent.mAddingWord) {
				button.setOnLongClickListener(this);
			}
		}
	}

	@Override
	public void setPressed(int keyCode, boolean pressed) {
		int id = 0;
		if(keyCode == mQuirks.getLeftSoftKey()) {
			id = R.id.main_left;
		}
		else if (keyCode == mQuirks.getRightSoftKey()) {
			id = R.id.main_right;
		}
		else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
			id = R.id.main_mid;
		}
		if (id != 0) {
			((View) mainview.findViewById(id)).setPressed(pressed);
		}
	}

	@Override
	public void showNotFound(boolean notFound) {
		if (notFound) {
			((TextView) mainview.findViewById(R.id.left_hold_upper))
				.setText(R.string.main_left_notfound);
			((TextView) mainview.findViewById(R.id.left_hold_lower))
				.setText(R.string.main_left_insert);
		} else {
			((TextView) mainview.findViewById(R.id.left_hold_upper))
				.setText(R.string.main_left_insert);
			((TextView) mainview.findViewById(R.id.left_hold_lower))
				.setText(R.string.main_left_addword);
		}
	}

	@Override
	public void midButtonUpdate(boolean composing) {
		if (composing) {
			((TextView) mainview.findViewById(R.id.main_mid)).setText(R.string.main_mid_commit);
		} else {
			((TextView) mainview.findViewById(R.id.main_mid)).setText(R.string.main_mid);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_left:
			if (parent.mAddingWord) {
				parent.showSymbolPage();
			} else {
				if (parent.mWordFound) {
					parent.showSymbolPage();
				} else {
					parent.showAddWord();
				}
			}
			break;
		case R.id.main_mid:
			parent.handleMidButton();
			break;
		case R.id.main_right:
			parent.nextKeyMode();
			break;
		}
	}

	@Override
	public void showHold(boolean show) {
		ViewSwitcher vs = (ViewSwitcher) mainview.findViewById(R.id.main_left);
		if (show) {
			vs.setDisplayedChild(1);
		} else {
			vs.setDisplayedChild(0);
		}
	}

	@Override
	public boolean onLongClick(View v) {
		switch (v.getId()) {
		case R.id.main_left:
			parent.showAddWord();
			break;
		case R.id.main_right:
			parent.launchOptions();
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public View getView() {
		return mainview;
	}
}
