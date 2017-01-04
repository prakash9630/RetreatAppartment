package project.revision.tap.retre.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import project.revision.tap.retre.Places.Ichangu_Narayan;
import project.revision.tap.retre.Places.Kathmandu_durbar_square;
import project.revision.tap.retre.R;
import project.revision.tap.retre.Helper.RecyclerInformation;
import project.revision.tap.retre.Places.Seto_gumba;
import project.revision.tap.retre.Places.Swyambhu_nath;

/**
 * Created by prakash on 9/16/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private LayoutInflater layoutInflater;
    List<RecyclerInformation> data=new ArrayList<>();
    Context ctx;


    public RecyclerAdapter(Context context,List<RecyclerInformation> data) {

        layoutInflater=LayoutInflater.from(context);
        this.data=data;
        ctx=context;




    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
View view=layoutInflater.inflate(R.layout.travel_holder_layout,parent,false);
        RecyclerViewHolder holder=new RecyclerViewHolder(view,ctx,data);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        RecyclerInformation current=data.get(position);
        holder.textView.setText(current.name);
        holder.imageButton.setImageResource(current.img);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (position==0)
                {
                    Intent i=new Intent(ctx,Swyambhu_nath.class);
                    ctx.startActivity(i);
                }
                if (position==1)
                {
                    Intent i=new Intent(ctx, Kathmandu_durbar_square.class);
                    ctx.startActivity(i);
                }
                if (position==2)
                {
                    Intent i=new Intent(ctx,Seto_gumba.class);
                    ctx.startActivity(i);
                }
                if (position==3)
                {
                    Intent i=new Intent(ctx,Ichangu_Narayan.class);
                    ctx.startActivity(i);
                }
            }
        });




    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return data.size();
    }
}

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageButton imageButton;
    List<RecyclerInformation> data = new ArrayList<>();
    Context ctx;


    public RecyclerViewHolder(View itemView, Context ctx, List<RecyclerInformation> data) {
        super(itemView);
        this.data = data;
        this.ctx = ctx;

        textView = (TextView) itemView.findViewById(R.id.name_place);
        imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);


    }


}