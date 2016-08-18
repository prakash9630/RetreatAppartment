package project.revision.tap.retre.Rooms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import project.revision.tap.retre.Adapter.TwoBedroomDelux_adapter;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class TwoBedRoomDeluxe extends AppCompatActivity {
    ViewPager mTwobedroomdelux;
    TwoBedroomDelux_adapter adapter;
    ImageButton mRight,mLeft;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twobedroomdelux_layout);

        mTwobedroomdelux=(ViewPager)findViewById(R.id.twobedroomdelux_rooms);
        adapter=new TwoBedroomDelux_adapter(this);
        mTwobedroomdelux.setAdapter(adapter);

        mRight=(ImageButton)findViewById(R.id.right_nav);
        mLeft=(ImageButton)findViewById(R.id.left_nav);

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab=mTwobedroomdelux.getCurrentItem();
                if(tab > 0)
                {
                    tab--;
                    mTwobedroomdelux.setCurrentItem(tab);


                }
                else if(tab==0)
                {
                    mTwobedroomdelux.setCurrentItem(tab);
                }
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mTwobedroomdelux.getCurrentItem();
                tab++;
                mTwobedroomdelux.setCurrentItem(tab);
            }
        });
    }
}
