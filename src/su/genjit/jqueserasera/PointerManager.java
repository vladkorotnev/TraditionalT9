package su.genjit.jqueserasera;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class PointerManager {
    private final IBinder mService;

    public PointerManager() {
        try {
            Class<?> smgr = Class.forName("android.os.ServiceManager");
            Object remote = smgr.getMethod("getService", String.class).invoke(smgr, "kcpointer");
            mService = (IBinder) remote;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get kcpointer service", e);
        }
    }

    private void blindTxn(int code, Parcel tx) throws RemoteException {
        Parcel response = Parcel.obtain();
        mService.transact(code, tx, response, 0);
        response.recycle();
    }

    private Parcel parcel() {
        Parcel p = Parcel.obtain();
        p.writeInterfaceToken("com.kyocera.pointer.IKcPointerManager");
        return p;
    }

    public void setPointerVisible(boolean visible) throws RemoteException {
        Parcel tx = parcel();
        tx.writeInt(visible ? 1 : 0);
        blindTxn(3, tx);
        tx.recycle();
    }

    public void setPointerTemporary(boolean visible, String id) throws RemoteException {
        Parcel tx = parcel();
        tx.writeInt(visible ? 1 : 0);
        tx.writeString(id);
        blindTxn(1, tx);
        tx.recycle();
    }

    public void endTemporary(String id) throws RemoteException {
        Parcel tx = parcel();
        tx.writeString(id);
        blindTxn(2, tx);
        tx.recycle();
    }
}