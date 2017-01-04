package project.revision.tap.retre.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import project.revision.tap.retre.Places.Cultural_places_activity;
import project.revision.tap.retre.Restaurant.Restaurant_activity;

/**
 * Created by prakash on 9/21/2016.
 */
public class Fragment_adapter extends FragmentStatePagerAdapter {
    public Fragment_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
            Cultural_places_activity c=new Cultural_places_activity();
            return c;
            case 1:
                Restaurant_activity r=new Restaurant_activity();
                return r;

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}

