package project.revision.tap.retre.Main_LandigPage_class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import project.revision.tap.retre.R;
import project.revision.tap.retre.Rooms.OneBedroomExecutive;
import project.revision.tap.retre.Rooms.PenthouseHimayalaView;
import project.revision.tap.retre.Rooms.ThreeBedRoomHimayalaView;
import project.revision.tap.retre.Rooms.TwoBedRoomDeluxe;
import project.revision.tap.retre.Rooms.TwoBedroomStandard;

/**
 * Created by prakash on 8/11/2016.
 */
public class AppartmentType extends AppCompatActivity {
    Toolbar mRoomType_toolbar;
    LinearLayout penthouse,onebedroomexecutive,twobedroomdelux,twobedroomstandard,threebeedroomhimalayaview;

  TextView p_price,obe_price,tbhv_price,tbd_price,tbs_price;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appartmenttype_layout);
        mRoomType_toolbar=(Toolbar)findViewById(R.id.roomtype_toolbar);
        setSupportActionBar(mRoomType_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        p_price=(TextView)findViewById(R.id.ap_price);
        obe_price=(TextView)findViewById(R.id.aobe_price);
        tbhv_price=(TextView)findViewById(R.id.atbhv_price);
        tbd_price=(TextView)findViewById(R.id.atbd_price);
        tbs_price=(TextView)findViewById(R.id.atbs_price);
        SharedPreferences sharedPreferences=getSharedPreferences("price", Context.MODE_PRIVATE);

        p_price.setText(sharedPreferences.getString("p_night",""));
        obe_price.setText(sharedPreferences.getString("obe_night",""));
        tbhv_price.setText(sharedPreferences.getString("tbhv_night",""));
        tbd_price.setText(sharedPreferences.getString("tbd_night",""));
        tbs_price.setText(sharedPreferences.getString("tbs_night",""));


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
                Intent ts=new Intent(AppartmentType.this, TwoBedroomStandard.class);
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
