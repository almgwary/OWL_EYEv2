package com.owl_eye;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.owl_eye.controller.CallBack;
import com.owl_eye.controller.InternalController;
import com.owl_eye.messageSystem.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MasterActivity extends AppCompatActivity {
    String token;
    Button getPhoto ;
    Button mute ;
    Button umMute ;
    Button runMotion ;
    Button stopMotion ;
    ImageView camImage ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        token = getIntent().getExtras().getString("token");
        getPhoto = (Button) findViewById(R.id.getPhoto);

        mute = (Button) findViewById(R.id.mute);
        umMute = (Button) findViewById(R.id.unMute);
        runMotion = (Button) findViewById(R.id.runMotion);
        stopMotion = (Button) findViewById(R.id.stopMotion);

        camImage = (ImageView) findViewById(R.id.camImage);


        InternalController.start(token, new CallBack() {
            @Override
            public void onRecivePhoto(Bitmap bitmap) {
                Log.d("CallBAck", "RecivedBitMap");

                camImage.setImageBitmap(bitmap);
                storeImage(bitmap);
             }
        });



        getPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalController.sentOrder(Task.GET_PHOTO);
            }
        });

        umMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalController.sentOrder(Task.UN_MUTE);
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalController.sentOrder(Task.MUTE);
            }
        });

        runMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalController.sentOrder(Task.RUN_MOTION);
            }
        });

        stopMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InternalController.sentOrder(Task.STOP_MOTION);
            }
        });

    }



    // To Save your bitmap in sdcard use the following code

// Store Image

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("Saving File",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("Saving File", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("Saving File", "Error accessing file: " + e.getMessage());
        }
    }



//To Get the Path for Image Storage

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }


}
