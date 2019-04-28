package com.pkunal.myarcore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButtonAddFlatCard;
    private Button mButtonAddAndy;
    private Button mButtonAddShape;
    private Button mButtonRuntime;
    private Button mButtonRecording;
    private Button mButtonInTheAir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    private void initWidgets() {
        mButtonAddFlatCard=findViewById(R.id.button_flat_card);
        mButtonAddFlatCard.setOnClickListener(this);
        mButtonAddAndy=findViewById(R.id.button_andy);
        mButtonAddAndy.setOnClickListener(this);
        mButtonAddShape=findViewById(R.id.button_shape);
        mButtonAddShape.setOnClickListener(this);
        mButtonRuntime=findViewById(R.id.button_runtime);
        mButtonRuntime.setOnClickListener(this);
        mButtonRecording=findViewById(R.id.button_video_recording);
        mButtonRecording.setOnClickListener(this);
        mButtonInTheAir=findViewById(R.id.button_add_object_in_the_air);
        mButtonInTheAir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.button_flat_card:
                intent=new Intent(MainActivity.this,FlatCardActivity.class);
                startActivity(intent);
                break;
            case R.id.button_andy:
                intent=new Intent(MainActivity.this,AndyActivity.class);
                startActivity(intent);
                break;
            case R.id.button_shape:
                intent=new Intent(MainActivity.this,ShapeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_runtime:
                intent=new Intent(MainActivity.this,RuntimeActivity.class);
                startActivity(intent);
                break;
            case R.id.button_video_recording:
                intent=new Intent(MainActivity.this,RecordingActivity.class);
                startActivity(intent);
                break;
            case R.id.button_add_object_in_the_air:
                intent=new Intent(MainActivity.this,InTheAirActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
