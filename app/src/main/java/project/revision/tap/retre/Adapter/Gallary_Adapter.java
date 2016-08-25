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

    public Integer[] images={R.drawable.penthouse,R.drawable.penthouse1,R.drawable.penthouse2,R.drawable.penthouse3,R.drawable.penthouse4,R.drawable.onebedroomexecutive,
                          R.drawable.onebedroomexecutive1,R.drawable.onebedroomexecutive2,R.drawable.onebedroomexecutive3,R.drawable.valueformoney,R.drawable.twobedroomstandard1,R.drawable.twobedroomstandard2,R.drawable.twobedroomstandard3,
            R.drawable.twobedroomdeluxe,R.drawable.twobedroomdelux1,R.drawable.twobedroomdelux2,R.drawable.twobedroomdelux4,R.drawable.twobedroomdelux5,R.drawable.threebedroomhimalayaview,R.drawable.threebedroomhimayalaview1,
            R.drawable.threebedroomhimayalaview2,
            R.drawable.threebedroomhimayalaview3,R.drawable.threebedroomhimayalaview4};

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView=new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(250,250));

        return imageView;
    }
}
