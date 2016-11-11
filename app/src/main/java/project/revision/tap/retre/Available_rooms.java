package project.revision.tap.retre;

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

/**
 * Created by prakash on 10/20/2016.
 */
public class Available_rooms extends AppCompatActivity {

    static String url;
    String start,end,unit1,unit2,unit3;
    int unit;


    private String parameterString;
    ProgressDialog pDilog;

    Available_rooms_getter availableRooms;
    ArrayList<Available_rooms_getter> arrayRoom;


    String Unit_type,Body,Image;
    int unit_no;
    double Booking_price;
    RecyclerView recyclerView;
    RecyclerBookingAdapter adapter;
    LinearLayoutManager mLinearlayout;
    Toolbar toolbar;
    String roomType="all";
    String mUnitname;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        setContentView(R.layout.available_room_layout);
        start =intent.getExtras().getString("arrival");
        end=intent.getExtras().getString("departure");
        unit=intent.getExtras().getInt("unit");
        unit1=intent.getExtras().getString("u1");
        unit2=intent.getExtras().getString("u2");
        unit3=intent.getExtras().getString("u3");
        pDilog=new ProgressDialog(this);
        pDilog.setMessage("Searching.....");
        pDilog.setCancelable(true);
        pDilog.show();
        toolbar=(Toolbar)findViewById(R.id.available_toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




        recyclerView=(RecyclerView)findViewById(R.id.avilable_room_id);

            roomType = getIntent().getStringExtra("mtype");

mUnitname=getIntent().getStringExtra("unit_name");


//        roomType = getIntent().getStringExtra("mtype");

        mLinearlayout=new LinearLayoutManager(this);
        parameterString="?type="+roomType+"&"
                +"rooms_group_size1="
                + unit1+"&"+"rooms_group_size2="
                + unit2+"&"+"rooms_group_size3="
                + unit3;



        url="https://www.retreatservicedapartments.com/androidapi/v1/retreatapp_availability_search/" + start + "/" + end + "/"
                + unit+parameterString;



        Log.d("myurl",url);











        sendRequest();

    }

    void  alertDilog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Available_rooms.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Sorry");

        // Setting Dialog Message
        alertDialog.setMessage(" For the specified dates, all units of "+mUnitname+" are booked. Please select available units below.");

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

    void sendRequest()
    {
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


arrayRoom=new ArrayList<>();
                for (int i=0;i<response.length();i++)
                {

                    try {
                        JSONObject obj=response.getJSONObject(i);
                        if(obj==null)
                        {

                            Toast.makeText(Available_rooms.this,"Currently no room available", Toast.LENGTH_SHORT).show();
                        }

                            int wasnotavailable=obj.getInt("was_not_available");
                        String notfund=String.valueOf(wasnotavailable);

                        Unit_type=obj.getString("unit_type");
                        if(notfund=="1")
                        {
                            alertDilog();
                        }


                        Booking_price=obj.getDouble("booking_price");

                        JSONObject nestedObj=obj.getJSONObject("unit_type_details");
                        Body=nestedObj.getString("body");
                        Image=nestedObj.getString("photo");


                        JSONObject UnitObj=obj.getJSONObject("units");
                        unit_no=UnitObj.length();


                        availableRooms=new Available_rooms_getter(Unit_type,Booking_price,Body,Image,unit_no);
                        arrayRoom.add(availableRooms);


                        pDilog.dismiss();




                    adapter=new RecyclerBookingAdapter(arrayRoom,Available_rooms.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(mLinearlayout);







                    } catch (JSONException e) {
                        e.printStackTrace();

                        pDilog.dismiss();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Available_rooms.this,error.toString(), Toast.LENGTH_SHORT).show();
                pDilog.dismiss();

            }
        });



        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);



    }

}
