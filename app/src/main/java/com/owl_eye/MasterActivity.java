package com.owl_eye;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Bundle;
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
                Log.d("CallBAck","RecivedBitMap");
                camImage.setImageBitmap(bitmap);
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

    public void recivingPohot(){
       // camImage.setImageIcon(new Icon());
    }




}
