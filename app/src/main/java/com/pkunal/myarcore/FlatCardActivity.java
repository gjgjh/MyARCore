package com.pkunal.myarcore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ViewRenderable.builder()
                .setView(this, R.layout.planet_card_view)
                .build()
                .thenAccept(renderable -> viewRenderable = renderable);

        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (viewRenderable == null) {
                        return;
                    }

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

                    // update text when the renderable's node is tapped
                    View flatCardView=viewRenderable.getView();
                    TextView textView=(TextView)flatCardView.findViewById(R.id.planetInfoCard);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            textView.setText("Clicked!");
                        }
                    });

                });

    }
}
