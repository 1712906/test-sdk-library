package com.production.mytest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;

public class ActivityBridge {private static final String KEY_ACTIVITY_BRIDGE = "ACTIVITY_BRIDGE";
    private final Context context;
    private SharedPreferences sharedPreferences;


    public ActivityBridge(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(KEY_ACTIVITY_BRIDGE, Context.MODE_PRIVATE);
    }


    @SuppressLint("ApplySharedPref")
    public void putData(Bundle bundle, Intent intent) {
        sharedPreferences.edit()
                .putString(
                        intent.toString(),
                        Base64.encodeToString(bundleToBytes(bundle), 0)
                )
                .commit();
    }


    @SuppressLint("ApplySharedPref")
    public Bundle getData(Intent intent) {
        Bundle bundle;
        final String bundleString = sharedPreferences.getString(intent.toString(), "");

        if (TextUtils.isEmpty(bundleString)) {
            return null;
        } else {
            bundle = bytesToBundle(Base64.decode(bundleString, 0));
        }

        sharedPreferences.edit()
                .clear()
                .commit();

        return bundle;
    }


    public byte[] bundleToBytes(Bundle bundle) {
        Parcel parcel = Parcel.obtain();
        parcel.writeBundle(bundle);
        byte[] bytes = parcel.marshall();
        parcel.recycle();
        return bytes;
    }


    public Bundle bytesToBundle(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        Bundle bundle = parcel.readBundle(context.getClassLoader());
        parcel.recycle();
        return bundle;
    }
}
