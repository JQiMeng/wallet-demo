package com.coinangel.wallet.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coinangel.wallet.MbwManager;
import com.coinangel.wallet.PinDialog;
import com.coinangel.wallet.R;

import com.coinangel.wallet.activity.modern.ModernMain;

import java.util.UUID;

public class StartupActivity extends AppCompatActivity implements AccountCreatorHelper.AccountCreationObserver {
    private static final String TAG = "StartupActivity";

    private static final int MINIMUM_SPLASH_TIME = 500;
    private static final int REQUEST_FROM_URI = 2;
    private static final int IMPORT_WORDLIST = 0;

    private static final String URI_HOST_GLIDERA_REGISTRATION = "glideraRegistration";

    private boolean _hasClipboardExportedPrivateKeys;
    private boolean hasClipboardExportedPublicKeys;
    private MbwManager _mbwManager;
    private AlertDialog _alertDialog;
    private PinDialog _pinDialog;
    private ProgressDialog _progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);
        Log.i(TAG,"StartupActivity== onCreate");
    }

    private void normalStartup() {
        // Normal startup, show the selected account in the BalanceActivity
        startActivity(new Intent(StartupActivity.this, ModernMain.class));
        finish();
    }

    private Runnable delayedFinish = new Runnable() {
        @Override
        public void run() {
            if (_mbwManager.isUnlockPinRequired()) {

                // set a click handler to the background, so that
                // if the PIN-Pad closes, you can reopen it by touching the background
                getWindow().getDecorView().findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        delayedFinish.run();
                    }
                });

                Runnable start = new Runnable() {
                    @Override
                    public void run() {
                        _mbwManager.setStartUpPinUnlocked(true);
                        start();
                    }
                };

                // set the pin dialog to not cancelable
                _pinDialog = _mbwManager.runPinProtectedFunction(StartupActivity.this, start, false);
            } else {
                start();
            }
        }

        private void start() {
            // Check whether we should handle this intent in a special way if it
            // has a bitcoin URI in it
//            if (handleIntent()) {
//                return;
//            }

            if(hasClipboardExportedPublicKeys){
//                warnUserOnClipboardKeys(false);
            }
            else if ( _hasClipboardExportedPrivateKeys) {
//                warnUserOnClipboardKeys(true);
            }
            else {
                normalStartup();
            }
        }
    };


    @Override
    public void onAccountCreated(UUID accountId) {
        delayedFinish.run();
    }
}
