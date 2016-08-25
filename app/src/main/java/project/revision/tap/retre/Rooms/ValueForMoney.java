package project.revision.tap.retre.Rooms;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import project.revision.tap.retre.Adapter.TwoBedroomStandard_adapter;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class ValueForMoney extends AppCompatActivity{
    ViewPager mTwobedroomstandard;
    TwoBedroomStandard_adapter adapter;
    ImageButton mRignt,mLeft;
    TextView mFirst;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.valueformoneytwobedroomstandrad_layout);
        mTwobedroomstandard=(ViewPager)findViewById(R.id.twobedroomstandard_rooms);
        adapter=new TwoBedroomStandard_adapter(this);
        mTwobedroomstandard.setAdapter(adapter);
        mRignt=(ImageButton)findViewById(R.id.right_nav);
        mLeft=(ImageButton)findViewById(R.id.left_nav);
        mFirst=(TextView)findViewById(R.id.twoBedroomStandard_paragraph1);
        Typeface myTypeface=Typeface.createFromAsset(getAssets(),"Raleway-ExtraLight.ttf");
        mFirst.setTypeface(myTypeface);

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mTwobedroomstandard.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mTwobedroomstandard.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mTwobedroomstandard.setCurrentItem(tab);
                }
            }
        });
        mRignt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mTwobedroomstandard.getCurrentItem();
                tab++;
                mTwobedroomstandard.setCurrentItem(tab);
            }
        });

    }
}
