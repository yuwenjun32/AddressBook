package com.example.lovem.addressbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv_main_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_main_welcome=(ImageView)findViewById(R.id.iv_main_welcome);
        Animation anim=AnimationUtils.loadAnimation(this,R.anim.set);
        iv_main_welcome.startAnimation(anim);


    }
}
