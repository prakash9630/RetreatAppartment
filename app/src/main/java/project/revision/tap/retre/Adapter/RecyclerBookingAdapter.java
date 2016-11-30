package project.revision.tap.retre.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import project.revision.tap.retre.Checkout;

import project.revision.tap.retre.Pojo.Available_rooms_getter;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 10/27/2016.
 */
public class RecyclerBookingAdapter extends RecyclerView.Adapter<BookingHolder> {

    private LayoutInflater layoutInflater;
    List<Available_rooms_getter> data=new ArrayList<>();
    Context context;
    public RecyclerBookingAdapter(List<Available_rooms_getter> data,Context context) {
        this.data=data;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);



    }

    @Override
    public BookingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= layoutInflater.inflate(R.layout.single_available_room_design,parent,false);
        BookingHolder holder=new BookingHolder(view,context, (ArrayList<Available_rooms_getter>) data);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BookingHolder holder, int position) {
        final Available_rooms_getter current=data.get(position);


        holder.Unit_name.setText(current.getApartmentName());
        holder.Unit_price.setText("Total price : $"+current.getBookingPrice().toString());


      holder.Unit_body.loadData(current.getBody(), "text/html; charset=utf-8", "UTF-8");
        String image=current.getImage();


        Picasso.with(context)
                .load("http://www.retreatservicedapartments.com/sites/default/files/"+image)
                .placeholder(R.drawable.defult)   // optional
                .error(R.drawable.error)      // optional
                .resize(600, 340)                        // optional
                                           // optional
                .into(holder.Unit_image);




        final List<String> list=new ArrayList<String>();
        int countNo=current.getUnitNo();
     for (int i=0;i<=countNo;i++)
     {
         list.add(""+i);

     }
        holder.Unit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no_of_unit=holder.unit_no.getSelectedItem().toString();
                
                if (holder.unit_no.getSelectedItem().equals("0"))
                {
                    Toast.makeText(context, "Please select a unit in order to continue with booking.", Toast.LENGTH_SHORT).show();
                }else {

                    Intent i=new Intent(context,Checkout.class);
                i.putExtra("Unit_Type",current.getApartmentName());
                i.putExtra("Unit_Price",""+current.getBookingPrice());
                    i.putExtra("Unit_no",no_of_unit);
                    context.startActivity(i);

            }}
        });


        ArrayAdapter<String> adp1=new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.unit_no.setAdapter(adp1);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class BookingHolder extends RecyclerView.ViewHolder {

    TextView Unit_name,Unit_price;
    WebView Unit_body;
    ImageView Unit_image;
    Button Unit_button;
    Spinner unit_no;


    ArrayList<Available_rooms_getter> data=new ArrayList<>();
    Context context;




    public BookingHolder(View itemView,Context context,ArrayList<Available_rooms_getter> data) {
        super(itemView);

        this.data=data;
        this.context=context;

        Unit_name=(TextView)itemView.findViewById(R.id.unit_name);
        Unit_price=(TextView)itemView.findViewById(R.id.unit_price);
        Unit_body=(WebView)itemView.findViewById(R.id.unit_body);
        Unit_image=(ImageView)itemView.findViewById(R.id.unit_image);
        Unit_button=(Button)itemView.findViewById(R.id.unit_button);
        unit_no=(Spinner)itemView.findViewById(R.id.spinner_unit);
       Unit_body .setBackgroundColor(Color.TRANSPARENT);





    }
}