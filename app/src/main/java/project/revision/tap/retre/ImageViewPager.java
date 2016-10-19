package project.revision.tap.retre;

import android.content.Intent;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;





import com.polites.android.GestureImageView;

import java.util.ArrayList;
import java.util.List;

import project.revision.tap.retre.Adapter.Gallary_Adapter;
import project.revision.tap.retre.Adapter.ImagePagerAdapter;

/**
 * Created by prakash on 10/3/2016.
 */
public class ImageViewPager extends AppCompatActivity {
    int position;

    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        Intent p = getIntent();
        position = p.getExtras().getInt("id");

        Gallary_Adapter adapter=new Gallary_Adapter(this);
        List<GestureImageView> images = new ArrayList<GestureImageView>();
        for (int i = 0; i < adapter.getCount(); i++) {
//            imageView = new ImageView(this);
//         ImageView imageView=new ImageView(this);
//      imageView.setImageResource(adapter.images[i]);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            images.add(imageView);
            GestureImageView imageView=new GestureImageView(this);

      imageView.setImageResource(adapter.images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            images.add(imageView);









            ImagePagerAdapter pageradapter = new ImagePagerAdapter(images);
            ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
            viewpager.setAdapter(pageradapter);

            viewpager.setCurrentItem(position);
//            viewpager.setPageTransformer(true, new AccordionTransformer());






        }
    }

}
