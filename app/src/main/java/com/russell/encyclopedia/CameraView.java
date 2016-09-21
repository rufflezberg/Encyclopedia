package com.russell.encyclopedia;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by rerickson on 8/1/2016.
 */
public class CameraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder cameraHolder;
    private Camera camera;

    public CameraView(Context context, Camera camera){
        super(context);

        this.camera = camera;
        this.camera.setDisplayOrientation(90);
        cameraHolder = getHolder();
        cameraHolder.addCallback(this);
        cameraHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try{
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        }catch(IOException e){
            Log.d("ERROR", "Camera error on surfaceCreated " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        if(cameraHolder.getSurface() == null){
            return;
        }

        try{
            camera.stopPreview();
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            camera.setPreviewDisplay(cameraHolder);
            camera.startPreview();
        }catch(IOException e){
            Log.d("ERROR", "Camera error on surfaceChanged " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
    }
}
