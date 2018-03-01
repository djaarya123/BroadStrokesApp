package dhiraj.projectapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import dhiraj.projectapp.R;

public class LaunchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        StartAnimations();

    }

    private void StartAnimations() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout rlayout=(RelativeLayout) findViewById(R.id.rlayout);
        rlayout.clearAnimation();
        rlayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.image_logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                } catch (InterruptedException e) {
                    startActivity(new Intent(LaunchActivity.this,MainActivity.class));
                    Log.d("dhiraj","error is : "+e.getMessage());
                } finally {
                    LaunchActivity.this.finish();
                }

            }
        };
        splashTread.start();
    }


}
