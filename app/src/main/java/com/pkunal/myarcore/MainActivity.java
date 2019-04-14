package com.pkunal.myarcore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButtonAddFlatCard;
    private Button mButtonAddAndy;
    private Button mButtonAddShape;

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
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.button_flat_card:
                break;
            case R.id.button_andy:
                intent=new Intent(MainActivity.this,AndyActivity.class);
                startActivity(intent);
                break;
            case R.id.button_shape:
                intent=new Intent(MainActivity.this,ShapeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
