package project.revision.tap.retre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by prakash on 8/11/2016.
 */
public class RoomType extends AppCompatActivity {
    Toolbar mRoomType_toolbar;
    CardView room1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roomtype_layout);
        mRoomType_toolbar=(Toolbar)findViewById(R.id.roomtype_toolbar);
        setSupportActionBar(mRoomType_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        room1=(CardView)findViewById(R.id.detai_room1);


    }
    public void Room1()
    {
        room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(RoomType.this,Room_detail.class);
                startActivity(i);
            }
        });
    }
}
