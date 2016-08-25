package project.revision.tap.retre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import project.revision.tap.retre.Rooms.OneBedroomExecutive;
import project.revision.tap.retre.Rooms.PenthouseHimayalaView;
import project.revision.tap.retre.Rooms.ThreeBedRoomHimayalaView;
import project.revision.tap.retre.Rooms.TwoBedRoomDeluxe;
import project.revision.tap.retre.Rooms.ValueForMoney;

/**
 * Created by prakash on 8/11/2016.
 */
public class AppartmentType extends AppCompatActivity {
    Toolbar mRoomType_toolbar;
    LinearLayout penthouse,onebedroomexecutive,twobedroomdelux,twobedroomstandard,threebeedroomhimalayaview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appartmenttype_layout);
        mRoomType_toolbar=(Toolbar)findViewById(R.id.roomtype_toolbar);
        setSupportActionBar(mRoomType_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

penthouse=(LinearLayout)findViewById(R.id.penthouse_layout);
        onebedroomexecutive=(LinearLayout)findViewById(R.id.onebedroomexecutive_layout);
        twobedroomdelux=(LinearLayout)findViewById(R.id.twobedroomdelux_layout);
        twobedroomstandard=(LinearLayout)findViewById(R.id.twobedroomstandard_layout);
        threebeedroomhimalayaview=(LinearLayout)findViewById(R.id.threebedroomhimalayaview_layout);


        penthouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p=new Intent(AppartmentType.this, PenthouseHimayalaView.class);
                startActivity(p);
            }
        });

        twobedroomdelux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent td=new Intent(AppartmentType.this,TwoBedRoomDeluxe.class);
                startActivity(td);
            }
        });

        twobedroomstandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ts=new Intent(AppartmentType.this, ValueForMoney.class);
                startActivity(ts);
            }
        });
threebeedroomhimalayaview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent th=new Intent(AppartmentType.this, ThreeBedRoomHimayalaView.class);
        startActivity(th);
    }
});
        onebedroomexecutive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(AppartmentType.this, OneBedroomExecutive.class);
                startActivity(o);
            }
        });

    }

}
