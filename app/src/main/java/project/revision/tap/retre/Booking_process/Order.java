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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import project.revision.tap.retre.Main_LandigPage_class.Public_Url;

import project.revision.tap.retre.Pojo.Available_rooms_getter;
import project.revision.tap.retre.Pojo.Order_data;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 11/23/2016.
 */
public class Order extends AppCompatActivity {

    RecyclerView orderlist;
    Toolbar toolbar;
    RecyclerView recyclerView;
    String url = Public_Url.viewOrder;
    Order_data orderData;
    ArrayList<Order_data> list;
    String userid;
    LinearLayoutManager mLinearlayout;
    OrderAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        toolbar = (Toolbar) findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        orderlist = (RecyclerView) findViewById(R.id.order_lists);

        recyclerView = (RecyclerView) findViewById(R.id.order_lists);

        SharedPreferences preferences = getSharedPreferences("Authentication", Context.MODE_PRIVATE);

        userid = preferences.getString("uid", "");

        mLinearlayout = new LinearLayoutManager(this);


        getData();


    }


    void getData() {

        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url + userid, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                list = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject obj = response.getJSONObject(i);


                        if (obj != null) {

                            String ordernumber = obj.getString("order_number");
                            String created = obj.getString("created");
                            String updated = obj.getString("updated");
                            String status = obj.getString("status");
                            String total = obj.getString("total");
                            String customerid = obj.getString("customer_id");

                            long currentdate = Long.parseLong(created);
                            long updateddate = Long.parseLong(updated);
                            Date cdate = new Date(currentdate * 1000L);
                            Date udate = new Date(updateddate * 1000L);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
                            String mCurrent = sdf.format(cdate);
                            String mUpdated = sdf.format(udate);


                            orderData = new Order_data(ordernumber, mCurrent, mUpdated, total, status, customerid);
                            list.add(orderData);


                            adapter = new OrderAdapter(list, Order.this);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(mLinearlayout);


                        } else {
                            Toast.makeText(Order.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        Toast.makeText(Order.this,"No order has been made", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Order.this,error.toString(), Toast.LENGTH_SHORT).show();

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


class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private LayoutInflater layoutInflater;
    List<Order_data> data = new ArrayList<>();
    Context context;

    public OrderAdapter(List<Order_data> data, Context context) {
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_design, parent, false);
        OrderHolder holder = new OrderHolder(view, context, (ArrayList<Order_data>) data);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {

        final Order_data order = data.get(position);


        holder.mOrderNumber.setText(order.getOrderno());
        holder.mCreated.setText(order.getCreate());
        holder.mUpdatedDate.setText(order.getUpdated());
        holder.mTotal.setText(order.getTotal());
        holder.mOrderStatus.setText(order.getOrderStatus());
        holder.mViewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(context, ViewDetailOrder.class);
                ii.putExtra("order_number",order.getOrderno());
                context.startActivity(ii);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class OrderHolder extends RecyclerView.ViewHolder {

    TextView mOrderNumber;
    TextView mCreated;
    TextView mUpdatedDate;
    TextView mTotal;
    TextView mOrderStatus;
    Button mViewbtn;

    ArrayList<Order_data> data = new ArrayList<>();
    Context context;


    public OrderHolder(View itemView, Context context, ArrayList<Order_data> data) {
        super(itemView);

        this.data = data;
        this.context = context;


        mOrderNumber = (TextView) itemView.findViewById(R.id.order_no);
        mCreated = (TextView) itemView.findViewById(R.id.created_id);
        mUpdatedDate = (TextView) itemView.findViewById(R.id.updated_id);
        mTotal = (TextView) itemView.findViewById(R.id.total_id);
        mOrderStatus = (TextView) itemView.findViewById(R.id.order_status_id);
        mViewbtn = (Button) itemView.findViewById(R.id.single_btn);


    }
}




