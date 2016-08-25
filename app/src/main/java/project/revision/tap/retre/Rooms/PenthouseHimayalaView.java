package project.revision.tap.retre.Rooms;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import project.revision.tap.retre.Adapter.Penthouse_swipe_activity;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class PenthouseHimayalaView extends AppCompatActivity {
WebView mParagraph;
    ImageButton mLeft,mRight;
    ViewPager mPenthouse;
    TextView mFirst,mSecond;
    Penthouse_swipe_activity penthouse_swipe_adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penthhousehimalayaview_layout);
        mPenthouse=(ViewPager)findViewById(R.id.penthouse_rooms);
        penthouse_swipe_adapter=new Penthouse_swipe_activity(this);
        mPenthouse.setAdapter(penthouse_swipe_adapter);
        mFirst=(TextView)findViewById(R.id.penthouse_p1);
        mSecond=(TextView)findViewById(R.id.penthouse_p2);
        Typeface myTypeface=Typeface.createFromAsset(getAssets(),"Raleway-ExtraLight.ttf");
        mFirst.setTypeface(myTypeface);
        mSecond.setTypeface(myTypeface);

        mLeft=(ImageButton)findViewById(R.id.left_nav);
        mRight=(ImageButton)findViewById(R.id.right_nav);

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mPenthouse.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mPenthouse.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mPenthouse.setCurrentItem(tab);
                }

            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mPenthouse.getCurrentItem();
                tab++;
                mPenthouse.setCurrentItem(tab);
            }
        });






    }
}
