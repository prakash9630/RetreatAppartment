package project.revision.tap.retre;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import project.revision.tap.retre.Rooms.OneBedroomExecutive;
import project.revision.tap.retre.Rooms.PenthouseHimayalaView;
import project.revision.tap.retre.Rooms.ThreeBedRoomHimayalaView;
import project.revision.tap.retre.Rooms.TwoBedRoomDeluxe;
import project.revision.tap.retre.Rooms.ValueForMoney;

/**
 * Created by prakash on 8/8/2016.
 */
public class customSwip extends PagerAdapter {
    private int[] imageResource={R.drawable.penthouse,R.drawable.threebedroomhimalayaview,R.drawable.onebedroomexecutive,R.drawable.twobedroomdeluxe,R.drawable.valueformoney};
    private Context ctx;
    private LayoutInflater layoutInflater;


    public customSwip(Context c) {
        ctx=c;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return imageResource.length;
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
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.activity_custom_swip,container,false);
        final ImageView imageView=(ImageView) itemView.findViewById(R.id.swip_image);
        imageView.setImageResource(imageResource[position]);
        container.addView(itemView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           if (position==0)
           {
               Intent i=new Intent(ctx,PenthouseHimayalaView.class);
               ctx.startActivity(i);
           }
                if (position==1)
                {
                    Intent i=new Intent(ctx, ThreeBedRoomHimayalaView.class);
                    ctx.startActivity(i);
                }
                if(position==2)
                {
                    Intent i=new Intent(ctx, OneBedroomExecutive.class);
                    ctx.startActivity(i);
                }
                if(position==3)
                {
                    Intent i=new Intent(ctx, TwoBedRoomDeluxe.class);
                    ctx.startActivity(i);
                }
                if(position==4)
                {
                    Intent i=new Intent(ctx, ValueForMoney.class);
                    ctx.startActivity(i);
                }
            }
        });
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
