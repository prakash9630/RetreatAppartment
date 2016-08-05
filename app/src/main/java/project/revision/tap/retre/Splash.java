package project.revision.tap.retre;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by prakash on 8/4/2016.
 */
public class Splash extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH=3000;

    ImageView mSplash_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        mSplash_icon=(ImageView)findViewById(R.id.splash_logo);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.anim_down);

        mSplash_icon.setAnimation(anim);


        anim.setDuration(2000);
        anim.start();
    ;

    /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                    Intent mainIntent = new Intent(Splash.this,MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();


            }
        }, SPLASH_DISPLAY_LENGTH);

    }

}
