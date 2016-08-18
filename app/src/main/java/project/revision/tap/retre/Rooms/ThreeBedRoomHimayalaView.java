package project.revision.tap.retre.Rooms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import project.revision.tap.retre.Adapter.ThreeBedroomHimalayaView_adapter;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class ThreeBedRoomHimayalaView extends AppCompatActivity {
    ViewPager mThreebedhimalayaview;
    ThreeBedroomHimalayaView_adapter adapter;
    ImageButton mRight,mLeft;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threebedroomhimalayaview_layout);
        mThreebedhimalayaview=(ViewPager)findViewById(R.id.threebedroomhimalayaview_rooms);
        adapter=new ThreeBedroomHimalayaView_adapter(this);
        mThreebedhimalayaview.setAdapter(adapter);

        mRight=(ImageButton)findViewById(R.id.right_nav);
        mLeft=(ImageButton)findViewById(R.id.left_nav);


        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mThreebedhimalayaview.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mThreebedhimalayaview.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mThreebedhimalayaview.setCurrentItem(tab);
                }
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mThreebedhimalayaview.getCurrentItem();
                tab++;
                mThreebedhimalayaview.setCurrentItem(tab);

            }
        });


    }
}
