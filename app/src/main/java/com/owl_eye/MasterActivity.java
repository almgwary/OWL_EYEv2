package com.owl_eye;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.owl_eye.controller.InternalController;

public class MasterActivity extends AppCompatActivity {
    String token;
    Button getPhoto ;
    Button mute ;
    Button umMute ;
    Button runMotion ;
    Button stopMotion ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        token = getIntent().getExtras().getString("token");
        InternalController.start(token , this);
        getPhoto = (Button) findViewById(R.id.getPhoto);

         mute = (Button) findViewById(R.id.mute);
         umMute = (Button) findViewById(R.id.unMute);
          runMotion = (Button) findViewById(R.id.runMotion);
         stopMotion = (Button) findViewById(R.id.stopMotion);

    }

}
