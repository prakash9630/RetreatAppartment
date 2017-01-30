package project.revision.tap.retre.Main_LandigPage_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 9/12/2016.
 */
public class Facilities_activity extends AppCompatActivity{
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amenities_layout);

        toolbar=(Toolbar)findViewById(R.id.amenities_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
}
