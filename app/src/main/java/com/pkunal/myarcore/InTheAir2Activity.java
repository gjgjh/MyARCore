package com.pkunal.myarcore;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class InTheAir2Activity extends AppCompatActivity implements View.OnClickListener {

    private ArFragment arFragment;
    private ViewRenderable viewRenderable;
    private FloatingActionButton addDialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_the_air2);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment_intheair2);

        context=this;
        addDialog=(FloatingActionButton)findViewById(R.id.add_dialog);
        addDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                final EditText input = new EditText(this);
                builder.setTitle("Input your note");
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputText = input.getText().toString();
                        ViewRenderable.builder()
                                .setView(context, R.layout.view_dialog)
                                .build()
                                .thenAccept(renderable ->{
                                    TextView textView=(TextView) renderable.getView().findViewById(R.id.diaglog_text);
                                    textView.setText(inputText);
                                    viewRenderable = renderable;

                                    Vector3 cameraPos = arFragment.getArSceneView().getScene().getCamera().getWorldPosition();
                                    Vector3 cameraForward = arFragment.getArSceneView().getScene().getCamera().getForward();
                                    Vector3 position = Vector3.add(cameraPos, cameraForward.scaled(1.0f));
                                    Vector3 direction=Vector3.subtract(cameraPos,position);
                                    direction.y=position.y;

                                    // Create an ARCore Anchor at the position.
                                    Pose pose = Pose.makeTranslation(position.x, position.y, position.z);
                                    Anchor anchor = arFragment.getArSceneView().getSession().createAnchor(pose);
                                    AnchorNode anchorNode = new AnchorNode(anchor);
                                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                                    Node dialogNode=new Node();
                                    dialogNode.setParent(anchorNode);
                                    dialogNode.setLookDirection(direction);
                                    dialogNode.setRenderable(viewRenderable);
                                });
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;
        }
    }
}