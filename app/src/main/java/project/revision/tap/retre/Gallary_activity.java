package project.revision.tap.retre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import project.revision.tap.retre.Adapter.Gallary_Adapter;

/**
 * Created by prakash on 8/23/2016.
 */
public class Gallary_activity extends AppCompatActivity{
    Toolbar mToolbar;
    GridView gridView;
    Gallary_Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallary_layout);
        mToolbar=(Toolbar)findViewById(R.id.gallary_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DisplayImageOptions displayImageOptions=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(displayImageOptions)
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);




        gridView=(GridView)findViewById(R.id.gridview);
         adapter=new Gallary_Adapter(this);
        gridView.setAdapter(adapter);




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent ii=new Intent(getApplicationContext(),Fullimage_activity.class);
                ii.putExtra("id",position);
                startActivity(ii);
            }
        });


    }



}
