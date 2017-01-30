package project.revision.tap.retre.Main_LandigPage_class;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.revision.tap.retre.Pojo.Appartment_type_data;
import project.revision.tap.retre.Pojo.Test_galleryData;
import project.revision.tap.retre.R;


/**
 * Created by prakash on 8/11/2016.
 */
public class AppartmentType extends AppCompatActivity {
    Toolbar mRoomType_toolbar;
    RecyclerView recyclerView;

    ArrayList<Appartment_type_data> data;
    Appartment_type_data apartments;
    String url = Public_Url.galleryAlbum;
    ApartmentAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appartmenttype_layout);
        mRoomType_toolbar = (Toolbar) findViewById(R.id.roomtype_toolbar);
        setSupportActionBar(mRoomType_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.appartment_types);

sendRequest();

    }



    void sendRequest() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                data = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject obj = response.getJSONObject(i);
                        if (obj != null) {
                            apartments= new Appartment_type_data();


                            apartments.setImage(obj.getString("image_url"));
                            apartments.setAppartmentname(obj.getString("Room Type"));
                            apartments.setPrice(obj.getString("Base Price"));
                            apartments.setWeekprice(obj.getString("Base Price Week"));
                            apartments.setMonthprice(obj.getString("Base Price Month"));
                            apartments.setNid(obj.getString("Nid"));
                            apartments.setMachinename(obj.getString("machine_name"));




                            data.add(apartments);
                            adapter=new ApartmentAdapter(AppartmentType.this,data);

                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(AppartmentType.this));



//


                        } else {

                        }


                    } catch (JSONException e) {
                        Toast.makeText(AppartmentType.this, "Network problem", Toast.LENGTH_SHORT).show();

                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AppartmentType.this,"Check your internet connection", Toast.LENGTH_SHORT).show();


            }
        });


        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


    }
}


class ApartmentAdapter extends RecyclerView.Adapter<ApartmetnHolder>
{
Context context;
    ArrayList<Appartment_type_data> data;
    LayoutInflater layoutInflater;

    public ApartmentAdapter(Context context, ArrayList<Appartment_type_data> data) {
        this.context = context;
        this.data = data;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public ApartmetnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.appartment_type_design,parent,false);
        ApartmetnHolder holder=new ApartmetnHolder(view,context,data);

        return holder;
    }

    @Override
    public void onBindViewHolder(ApartmetnHolder holder, int position) {
        Appartment_type_data current=data.get(position);
        holder.uniteType.setText(current.getAppartmentname());
        holder.unitPrice.setText("$"+current.getPrice()+" /Night "+" $"+current.getWeekprice()+" /Week "+" $"+current.getMonthprice()+" /Month");

        Picasso.with(context)
                .load(current.getImage())
                .placeholder(R.drawable.defult)   // optional
                .error(R.drawable.error)      // optional
                .resize(400, 190)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class ApartmetnHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

ImageView image;
    TextView uniteType,unitPrice;
    Context context;
    ArrayList<Appartment_type_data> data;



    public ApartmetnHolder(View itemView,Context context,ArrayList<Appartment_type_data> data) {
        super(itemView);
        this.context=context;
        this.data=data;
        itemView.setOnClickListener((View.OnClickListener) this);

        image=(ImageView)itemView.findViewById(R.id.apartment_img);
        uniteType=(TextView)itemView.findViewById(R.id.apartment_name);
        unitPrice=(TextView)itemView.findViewById(R.id.price_unit);
    }


    @Override
    public void onClick(View v) {
int positon=getAdapterPosition();

        Appartment_type_data current=data.get(positon);

        Intent i=new Intent(context,Appartment_detail.class);
        i.putExtra("nid",current.getNid());
        i.putExtra("machinename",current.getMachinename());
        context.startActivity(i);


    }
}