package com.russell.encyclopedia;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.io.File;
import java.util.concurrent.Semaphore;
import java.util.logging.Handler;

/**
 * Created by rerickson on 8/1/2016.
 */
public class PhotoRecognition extends Activity {

    private String cameraId;

    private CameraCaptureSession cameraCaptureSession;

    private CameraDevice cameraDevice;

    private Size previewSize;

    private Semaphore cameraOpenCloseLock = new Semaphore(1);

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback(){
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevices){
            cameraOpenCloseLock.release();
            cameraDevice = cameraDevices;
            createCameraPreviewSession();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevices){
            cameraOpenCloseLock.release();
            cameraDevices.close();
            cameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevices, int error){
            cameraOpenCloseLock.release();
            cameraDevices.close();
            cameraDevice = null;
            finish();
        }
    };

    private HandlerThread backgroundThread;

    private Handler backgroundHandler;

    private File file;

    private CaptureRequest.Builder previewRequestBuilder;

    private CaptureRequest previewRequest;



    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photorecognition);

        ImageButton imageClose = (ImageButton)findViewById(R.id.imageClose);
        imageClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent homeActivity = new Intent(view.getContext(), HomeActivity.class);
                startActivityForResult(homeActivity, 0);
            }
        });
    }
}
