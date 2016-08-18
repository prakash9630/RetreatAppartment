package project.revision.tap.retre.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/17/2016.
 */
public class OneBedroomExecutive_adaptor extends PagerAdapter {
    private int oneroomexecutive[]={R.drawable.onebedroomexecutive,R.drawable.onebedroomexecutive1,R.drawable.onebedroomexecutive2,R.drawable.onebedroomexecutive3};

    Context ctx;
    LayoutInflater inflater;

    public OneBedroomExecutive_adaptor(Context c) {
        this.ctx=c;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return oneroomexecutive.length;
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
        inflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflater.inflate(R.layout.activity_custom_swip,container,false);
        final ImageView imageView=(ImageView) itemView.findViewById(R.id.swip_image);
        imageView.setImageResource(oneroomexecutive[position]);
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

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}
