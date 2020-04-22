package com.wenxiangli;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity {
    private CameraSurfaceView cameraSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraSurfaceView = findViewById(R.id.csf);
        new RxPermissions(this).request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO)
                .subscribe(granted -> {

                });
    }

    public void takePic(View view) {
        cameraSurfaceView.capture();
    }

    public void takeRec(View view) {
        String path = getExternalCacheDir() + "/" + System.currentTimeMillis() + ".mp4";
        Log.d("takeRec", "takeRec: "+path);
        cameraSurfaceView.startRecord(path);
    }

    boolean isOpen = false;

    public void openFlash(View view) {
        isOpen = !isOpen;
        cameraSurfaceView.switchLight(isOpen);
    }

    public void endRec(View view) {
        cameraSurfaceView.stopRecord();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSurfaceView.closeCamera();
    }
}
