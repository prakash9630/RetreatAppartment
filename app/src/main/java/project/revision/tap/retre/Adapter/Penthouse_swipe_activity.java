package project.revision.tap.retre.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class Penthouse_swipe_activity extends PagerAdapter {
    private int penthouseRooms[]={R.drawable.penthouse,R.drawable.penthouse1,R.drawable.penthouse2,R.drawable.penthouse3,R.drawable.penthouse4};

    Context context;
    private LayoutInflater layoutInflater;


    public Penthouse_swipe_activity(Context c) {
        this.context=c;

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return penthouseRooms.length;
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View in which the page will be shown.
     * @param position  The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.activity_custom_swip,container,false);
        final ImageView imageView=(ImageView) itemView.findViewById(R.id.swip_image);
        imageView.setImageResource(penthouseRooms[position]);
        container.addView(itemView);


        return itemView;
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View from which the page will be removed.
     * @param position  The page position to be removed.
     * @param object    The same object that was returned by
     *                  {@link #instantiateItem(View, int)}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
