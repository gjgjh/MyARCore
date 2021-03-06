package com.pkunal.myarcore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class FlatCardActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private ViewRenderable viewRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_card);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment_flat_card);

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    ViewRenderable.builder()
                            .setView(this, R.layout.view_planet_card)
                            .build()
                            .thenAccept(renderable ->{
                                // update text when the renderable's node is tapped
                                TextView textView=(TextView) renderable.getView().findViewById(R.id.planetInfoCard);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        textView.setText("Clicked!");
                                    }
                                });
                                viewRenderable = renderable;

                                Anchor anchor = hitResult.createAnchor();
                                AnchorNode anchorNode = new AnchorNode(anchor);
                                anchorNode.setParent(arFragment.getArSceneView().getScene());

                                TransformableNode view = new TransformableNode(arFragment.getTransformationSystem());
                                view.setParent(anchorNode);
                                view.setRenderable(viewRenderable);
                                view.getRotationController();
                                view.getScaleController();
                                view.getTranslationController();
                                view.select();

                            });
                });

    }
}
