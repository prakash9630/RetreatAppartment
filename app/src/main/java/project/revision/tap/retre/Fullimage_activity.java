package project.revision.tap.retre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import project.revision.tap.retre.Adapter.Gallary_Adapter;

public class Fullimage_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage_activity);

        Intent i=getIntent();
        int position=i.getExtras().getInt("id");

        Gallary_Adapter adapter=new Gallary_Adapter(this);
        ImageView imageView=(ImageView)findViewById(R.id.imageView2);
   imageView.setImageResource(adapter.images[position]);


    }
}
