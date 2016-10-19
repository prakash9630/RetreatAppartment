package project.revision.tap.retre.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/23/2016.
 */
public class Gallary_Adapter extends BaseAdapter {
    private Context context;


    public int[] images={R.drawable.penthouse,R.drawable.penthouse1,R.drawable.penthouse2,R.drawable.penthouse3,R.drawable.penthouse4,R.drawable.onebedroomexecutive,
                          R.drawable.onebedroomexecutive2,R.drawable.onebedroomexecutive3,R.drawable.twobedroomstandard1,R.drawable.twobedroomstandard2,R.drawable.twobedroomstandard3,
            R.drawable.twobedroomdelux1,R.drawable.twobedroomdelux2};

    public Gallary_Adapter(Context c) {
        context=c;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        ImageView imageView;
        if (view == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            int size_width=(int)context.getResources().getDimension(R.dimen.image_size_width);
            int size_height=(int)context.getResources().getDimension(R.dimen.image_size_heignt);

//
            imageView.setLayoutParams(new GridView.LayoutParams(size_width,size_height));
//            imageView.setAdjustViewBounds(true);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5,5,5,5);
        } else {
            imageView = (ImageView) view;
        }

        imageView.setImageResource(images[position]);

        return imageView;
    }


}
