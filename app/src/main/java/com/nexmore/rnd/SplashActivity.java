package com.nexmore.rnd;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nexmore.rnd.ui.login.LoginActivity;
import com.nexmore.rnd.utils.PermissionUtils;

public class SplashActivity extends AppCompatActivity {

    public final static int REQUEST_LOCATION = 100;    // 위치 권한 요청 CODE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PermissionUtils.permissionCheck(SplashActivity.this) ) {
                    nextActivity();
                } else {
                    ActivityCompat.requestPermissions(
                            SplashActivity.this,
                            new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION },
                            REQUEST_LOCATION);
                }
            }
        }, 1000);
    }

    private void nextActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if ( requestCode == REQUEST_LOCATION ) {
            if ( grantResults[0] != PackageManager.PERMISSION_GRANTED ) {
                // 권한 거부
                finish();
            } else {
                // 권한 승낙
                nextActivity();
            }
        }
    }
}
