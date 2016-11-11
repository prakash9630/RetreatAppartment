package project.revision.tap.retre;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import project.revision.tap.retre.Rooms.OneBedroomExecutive;
import project.revision.tap.retre.Rooms.PenthouseHimayalaView;
import project.revision.tap.retre.Rooms.ThreeBedRoomHimayalaView;
import project.revision.tap.retre.Rooms.TwoBedRoomDeluxe;
import project.revision.tap.retre.Rooms.TwoBedroomStandard;

/**
 * Created by prakash on 8/8/2016.
 */
public class customSwip extends PagerAdapter {
    private int[] imageResource={R.drawable.penthouse,R.drawable.threebedroomhimayalaview,R.drawable.onebedroomexecutive,R.drawable.twobedroomdelux,R.drawable.valueformoney};
    private String[] mImageName={"Penthouse","3 Bedroom himalaya View","1 Bedroom Executive","2 Bedroom Deluxe","2 Bedroom Standard"};
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


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=layoutInflater.inflate(R.layout.activity_custom_swipe_main_page,container,false);
        final ImageView imageView=(ImageView) itemView.findViewById(R.id.swip_image_main);
        imageView.setImageResource(imageResource[position]);
        TextView imageName=(TextView)itemView.findViewById(R.id.img_name);
        imageName.setText(mImageName[position]);
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
                    Intent i=new Intent(ctx, TwoBedroomStandard.class);
                    ctx.startActivity(i);
                }
            }
        });
        return itemView;

    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */

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
        container.removeView((View) object);

    }

    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
}

