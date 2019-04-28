package com.pkunal.myarcore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.ar.core.Anchor;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class InTheAirActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ViewRenderable viewRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_the_air);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment_intheair);

        ViewRenderable.builder()
                .setView(this, R.layout.view_dialog)
                .build()
                .thenAccept(renderable -> viewRenderable = renderable);

        arFragment.getArSceneView().setOnTouchListener((view, motionEvent) -> {
            if(motionEvent.getAction()==motionEvent.ACTION_DOWN){
                Log.d("Point on screen", motionEvent.getX()+","+motionEvent.getY());

                if (viewRenderable == null) {
                    return false;
                }

                Vector3 cameraPos = arFragment.getArSceneView().getScene().getCamera().getWorldPosition();
                Vector3 cameraForward = arFragment.getArSceneView().getScene().getCamera().getForward();
                Vector3 position = Vector3.add(cameraPos, cameraForward.scaled(1.0f));

                // Create an ARCore Anchor at the position.
                Pose pose = Pose.makeTranslation(position.x, position.y, position.z);
                Anchor anchor = arFragment.getArSceneView().getSession().createAnchor(pose);
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                TransformableNode dialog = new TransformableNode(arFragment.getTransformationSystem());
                dialog.setParent(anchorNode);
                dialog.setRenderable(viewRenderable);
            }
            return true;
        });
    }

}
