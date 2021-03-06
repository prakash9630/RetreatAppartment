package project.revision.tap.retre.Booking_process;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import project.revision.tap.retre.Adapter.RecyclerBookingAdapter;
import project.revision.tap.retre.Pojo.Available_rooms_getter;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 10/20/2016.
 */
public class Available_rooms extends AppCompatActivity {

    static String url;
    String start, end, unit1, unit2, unit3;
    int unit;


    private String parameterString;
    ProgressDialog pDilog;

    Available_rooms_getter availableRooms;
    ArrayList<Available_rooms_getter> arrayRoom;


    String Unit_type, Body, Image, Unit_type_machinename;
    int unit_no;

    RecyclerView recyclerView;
    RecyclerBookingAdapter adapter;
    LinearLayoutManager mLinearlayout;
    Toolbar toolbar;
    String roomType = "all";
    String mUnitname;
    int bookingprice;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.available_room_layout);
        start = intent.getExtras().getString("arrival");
        end = intent.getExtras().getString("departure");
        unit = intent.getExtras().getInt("unit");


//        unit1=intent.getExtras().getString("u1");   // commented only for sometime
//        unit2=intent.getExtras().getString("u2");
//        unit3=intent.getExtras().getString("u3");

        unit1 = "1";
        unit2 = "1";
        unit3 = "1";


        pDilog = new ProgressDialog(this);
        pDilog.setMessage("Searching.....");
        pDilog.setCancelable(true);
        pDilog.show();
        toolbar = (Toolbar) findViewById(R.id.available_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.avilable_room_id);

        roomType = getIntent().getStringExtra("mtype");

        mUnitname = getIntent().getStringExtra("unit_name");


        mLinearlayout = new LinearLayoutManager(this);
        parameterString = "?type=" + roomType + "&"
                + "rooms_group_size1="
                + unit1 + "&" + "rooms_group_size2="
                + unit2 + "&" + "rooms_group_size3="
                + unit3;


        url = "https://www.retreatservicedapartments.com/androidapi/v1/retreatapp_availability_search/" + start + "/" + end + "/"
                + unit + parameterString;


        Log.d("myurl", url);


        sendRequest();

    }

    void noRoomDilog() {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Available_rooms.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry");

        // Setting Dialog Message
        alertDialog.setMessage("Unfortunately no units are available - try different dates if possible.");

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

                Intent ii=new Intent(Available_rooms.this,Booking.class);
                startActivity(ii);
                finish();

            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    void alertDilog() {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Available_rooms.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry");

        // Setting Dialog Message
        alertDialog.setMessage(" For the specified dates, all units of " + mUnitname + " are booked. Please select available units below.");

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to execute after dialog closed

            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    void sendRequest() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                arrayRoom = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {

                    try {


                            JSONObject obj = response.getJSONObject(i);

                            int wasnotavailable = obj.getInt("was_not_available");
                            String notfund = String.valueOf(wasnotavailable);

                            Unit_type = obj.getString("unit_type");
                            Unit_type_machinename = obj.getString("unit_type_machine_name");
                            if (notfund == "1") {
                                alertDilog();
                            }


                            Double Booking_price = obj.getDouble("booking_price");
                            bookingprice = Booking_price.intValue();

                            JSONObject nestedObj = obj.getJSONObject("unit_type_details");


                            Body = nestedObj.getString("body");
                            Image = nestedObj.getString("photo");


                            JSONObject UnitObj = obj.getJSONObject("units");
                            unit_no = UnitObj.length();


                            availableRooms = new Available_rooms_getter(Unit_type_machinename, Unit_type, bookingprice, Body, Image, unit_no);
                            arrayRoom.add(availableRooms);


                            pDilog.dismiss();


                            adapter = new RecyclerBookingAdapter(arrayRoom, Available_rooms.this);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(mLinearlayout);






                    } catch (JSONException e) {
                        e.printStackTrace();

                        pDilog.dismiss();
                        noRoomDilog();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Available_rooms.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                pDilog.dismiss();

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
