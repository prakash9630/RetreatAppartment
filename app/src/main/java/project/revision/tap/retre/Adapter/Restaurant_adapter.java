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

import project.revision.tap.retre.Bamboo;
import project.revision.tap.retre.Fire_ice;
import project.revision.tap.retre.Gaia_restaurant;
import project.revision.tap.retre.Garden_of_dreams;
import project.revision.tap.retre.Ichangu_Narayan;
import project.revision.tap.retre.Kathmandu_durbar_square;
import project.revision.tap.retre.Le_sherpa;
import project.revision.tap.retre.R;
import project.revision.tap.retre.RecyclerInformation;
import project.revision.tap.retre.Roadhouse_cafe;
import project.revision.tap.retre.Seto_gumba;
import project.revision.tap.retre.Swyambhu_nath;

/**
 * Created by prakash on 9/26/2016.
 */
public class Restaurant_adapter extends RecyclerView.Adapter<Holder> {
    private LayoutInflater layoutInflater;
    List<RecyclerInformation> data=new ArrayList<>();
    Context ctx;

    public Restaurant_adapter(Context ctx, List<RecyclerInformation> data) {
        this.ctx = ctx;
        this.data = data;
        layoutInflater=LayoutInflater.from(ctx);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.travel_holder_layout,parent,false);
        Holder holder=new Holder(view,ctx,data);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        RecyclerInformation current=data.get(position);
        holder.textView.setText(current.name);
        holder.imageButton.setImageResource(current.img);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0)
                {
                    Intent i=new Intent(ctx,Gaia_restaurant.class);
                    ctx.startActivity(i);
                }
                if (position==1)
                {
                    Intent i=new Intent(ctx,Le_sherpa.class);
                    ctx.startActivity(i);
                }
                if (position==2)
                {
                    Intent i=new Intent(ctx,Roadhouse_cafe.class);
                    ctx.startActivity(i);
                }
                if (position==3)
                {
                    Intent i=new Intent(ctx,Fire_ice.class);
                    ctx.startActivity(i);
                }
                if (position==4)
                {
                    Intent i=new Intent(ctx,Garden_of_dreams.class);
                    ctx.startActivity(i);
                }
                if (position==5)
                {
                    Intent i=new Intent(ctx,Bamboo.class);
                    ctx.startActivity(i);
                }

            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class Holder extends RecyclerView.ViewHolder
{
    TextView textView;
    ImageButton imageButton;
    List<RecyclerInformation> data = new ArrayList<>();
    Context ctx;
    public Holder(View itemView, Context ctx, List<RecyclerInformation> data) {
        super(itemView);
        this.data = data;
        this.ctx = ctx;

        textView = (TextView) itemView.findViewById(R.id.name_place);
        imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
    }
}
