package project.revision.tap.retre.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by prakash on 10/3/2016.
 */
public class full_screen_image_adapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
