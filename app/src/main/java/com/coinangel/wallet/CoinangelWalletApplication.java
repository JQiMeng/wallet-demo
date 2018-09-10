package com.coinangel.wallet;

/**
 * Created by lianzhi on 2018/9/10.
 */
import android.support.multidex.MultiDexApplication;
import android.util.Log;

public class CoinangelWalletApplication extends MultiDexApplication {
    private static final String TAG = "Application";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "CoinangelWalletApplication==onCreate");

    }
}
