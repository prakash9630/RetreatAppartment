package project.revision.tap.retre.Adapter;

/**
 * Created by prakash on 10/3/2016.
 */
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.polites.android.GestureImageView;

public class ImagePagerAdapter extends PagerAdapter {

    private List<GestureImageView> images;

    public ImagePagerAdapter(List<GestureImageView> images) {
        this.images = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        ImageView imageView = images.get(position);


        GestureImageView imageView= (GestureImageView) images.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}