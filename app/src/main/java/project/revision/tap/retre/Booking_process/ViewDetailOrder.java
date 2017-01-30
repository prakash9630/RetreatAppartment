package project.revision.tap.retre.Booking_process;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import project.revision.tap.retre.Main_LandigPage_class.Public_Url;
import project.revision.tap.retre.Pojo.Order_data;
import project.revision.tap.retre.Pojo.Order_unit_data;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 1/17/2017.
 */
public class ViewDetailOrder extends AppCompatActivity {

    String userid,orderNo;
    String url = Public_Url.viewOrder;

    Order_unit_data data;
    ArrayList<Order_unit_data> list;
    Intent i;
OrderUnit adapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Toolbar toolbar;
    TextView mOrdertotal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_layout);
        i = getIntent();
        orderNo = i.getStringExtra("order_number");
        recyclerView=(RecyclerView)findViewById(R.id.order_units);
        linearLayoutManager=new LinearLayoutManager(this);
        toolbar=(Toolbar)findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mOrdertotal=(TextView)findViewById(R.id.ordertotal);



        SharedPreferences preferences = getSharedPreferences("Authentication", Context.MODE_PRIVATE);

        userid = preferences.getString("uid", "");

        getData();
    }


    void getData() {

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + userid, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {





                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject obj = response.getJSONObject(i);



                            String orderno=obj.getString("order_number");
                        String ordertotal=obj.getString("total");





                            if (orderno.equals(orderNo))

                            {

                                mOrdertotal.setText("Total :"+ordertotal);




                                JSONArray array = obj.getJSONArray("line_items");

                                list=new ArrayList<>();


                            for (int ii = 0; ii < array.length(); ii++) {
                                JSONObject obj1 = array.getJSONObject(ii);

                                String unitName = obj1.getString("line_item_label");
                                String quantity = obj1.getString("quantity");


                                data = new Order_unit_data(unitName, quantity);
                                list.add(data);


                                adapter = new OrderUnit(ViewDetailOrder.this, list);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(linearLayoutManager);
                            }





                            }







                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewDetailOrder.this, "Network problem", Toast.LENGTH_SHORT).show();




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

class OrderUnit extends RecyclerView.Adapter<OrderUnitHolder> {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Order_unit_data> list = new ArrayList<>();


    public OrderUnit(Context context, ArrayList<Order_unit_data> list) {


        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);


    }

    @Override
    public OrderUnitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.view_detail_order_singel_desigen,parent,false);
        OrderUnitHolder holder=new OrderUnitHolder(view,context,list);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderUnitHolder holder, int position) {
        Order_unit_data data=list.get(position);

        holder.unitname.setText(data.getUnitname());
        holder.quantity.setText(data.getUnitQuantity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class OrderUnitHolder extends RecyclerView.ViewHolder {

    TextView unitname;
    TextView quantity;

    ArrayList<Order_unit_data> list;
    Context context;


    public OrderUnitHolder(View itemView,Context context,ArrayList<Order_unit_data> list) {
        super(itemView);
        this.list=list;
        this.context=context;


        unitname = (TextView) itemView.findViewById(R.id.order_unit);
        quantity = (TextView) itemView.findViewById(R.id.order_quantity);


    }
}