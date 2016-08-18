package project.revision.tap.retre.Rooms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import project.revision.tap.retre.Adapter.OneBedroomExecutive_adaptor;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class OneBedroomExecutive extends AppCompatActivity {
    ViewPager mOneroomexecutive;
    OneBedroomExecutive_adaptor adaptor;
    ImageButton leftNav,rightNav;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onebedroomexecutive_layout);
        mOneroomexecutive=(ViewPager)findViewById(R.id.oneroomexecutive_rooms);
        leftNav = (ImageButton)findViewById(R.id.left_nav);
        rightNav = (ImageButton)findViewById(R.id.right_nav);
        adaptor=new OneBedroomExecutive_adaptor(this);
        mOneroomexecutive.setAdapter(adaptor);


        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mOneroomexecutive.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mOneroomexecutive.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mOneroomexecutive.setCurrentItem(tab);
                }

            }
        });

        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mOneroomexecutive.getCurrentItem();
                tab++;
                mOneroomexecutive.setCurrentItem(tab);
            }
        });


    }
}
