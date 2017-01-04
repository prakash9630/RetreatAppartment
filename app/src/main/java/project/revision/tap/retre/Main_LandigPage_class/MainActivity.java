package project.revision.tap.retre.Main_LandigPage_class;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;
import project.revision.tap.retre.Adapter.customSwip;
import project.revision.tap.retre.Booking_process.Booking;
import project.revision.tap.retre.Chat.Chat_start;
import project.revision.tap.retre.Data.Channel;
import project.revision.tap.retre.Data.Item;
import project.revision.tap.retre.Gallary_activity.Gallary_activity;
import project.revision.tap.retre.Credentials.Login_form;
import project.revision.tap.retre.R;
import project.revision.tap.retre.Services.WeatherServiceCallback;
import project.revision.tap.retre.Services.YahooWeatherService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,WeatherServiceCallback {
    ViewPager viewPager;
    project.revision.tap.retre.Adapter.customSwip customSwip;
    static String url= Public_Url.Price;
    LinearLayout mLinear1,mLinear2,mLinear3,mLinear4,mLinear6,mLinear5;
    LinearLayout mContact,mBook;
    String p_night,p_week,p_month;
    String tbhv_night,tbhv_week,tbhv_month;
    String obe_night,obe_week,obe_month;
    String tbd_night,tbd_week,tbd_month;
    String tbs_night,tbs_week,tbs_month;
    NavigationView navigationView;
    TextView temperature;
    TextView condition;
    TextView location;
    YahooWeatherService service;
    LinearLayout weatherlayout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        mLinear1=(LinearLayout)findViewById(R.id.linear1);
        mLinear2=(LinearLayout)findViewById(R.id.linear2);
        mLinear3=(LinearLayout)findViewById(R.id.linear3);
        mLinear4=(LinearLayout)findViewById(R.id.linear4);
        mLinear5=(LinearLayout)findViewById(R.id.linear5);
        weatherlayout=(LinearLayout)findViewById(R.id.weather_layout);

        mLinear6=(LinearLayout)findViewById(R.id.linear6);

        mBook=(LinearLayout)findViewById(R.id.booknow);
        mContact=(LinearLayout)findViewById(R.id.contactnow);
        temperature=(TextView)findViewById(R.id.temperature_id);
        condition=(TextView)findViewById(R.id.condition);
        location=(TextView)findViewById(R.id.place);

        service=new YahooWeatherService(this);

        if (isOnline())
        {
            service.refreshWeather("Kathmandu");
            weatherlayout.setBackgroundColor(Color.parseColor("#50000000"));

        }
        else
        {

            weatherlayout.setVisibility(View.INVISIBLE);
        }



        Runtime.getRuntime().gc();
        getPrice();
//hideItem();

        mContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Contact_Activity.class);
                startActivity(i);
            }
        });




        mLinear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AppartmentType.class);
                startActivity(i);
            }
        });

        mLinear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,About_Us.class);
                startActivity(i);
            }
        });
        mLinear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDirection();

            }
        });

        mLinear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Gallary_activity.class);
                startActivity(i);
            }
        });
        mLinear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Travel_activity.class);
                startActivity(i);
            }
        });


        mLinear6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Amenities_activity.class);
                startActivity(i);
            }
        });









mBook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this,Booking.class);
        startActivity(i);
    }
});






        setSupportActionBar(toolbar);



        viewPager=(ViewPager)findViewById(R.id.fragment_viewpager);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.inciator);

        customSwip=new customSwip(this);
        viewPager.setAdapter(customSwip);
        indicator.setViewPager(viewPager);












        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);





        navigationView.setNavigationItemSelectedListener(this);


    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    void getPrice()
    {


        final JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++)
                {

                    try {
                        JSONObject object=response.getJSONObject(i);
                        String night=object.getString("Base Price");
                        String week=object.getString("Base Price Week");
                        String month=object.getString("Base Price Month");
                        String room=object.getString("Room Type");



                        if (room.equals("Penthouse Himalaya View"))
                        {
                            p_night=night;
                            p_week=week;
                            p_month=month;
                        }
                        if (room.equals("3 Bedroom Himalaya View"))
                        {
                            tbhv_night=night;
                            tbhv_week=week;
                            tbhv_month=month;
                        }
                        if (room.equals("1 Bedroom Executive"))
                        {
                            obe_night=night;
                            obe_week=week;
                            obe_month=month;
                        }
                        if (room.equals("2 Bedroom Deluxe"))
                        {
                            tbd_night=night;
                            tbd_week=week;
                            tbd_month=month;
                        }
                        if (room.equals("2 Bedroom Standard"))
                        {
                            tbs_night=night;
                            tbs_week=week;
                            tbs_month=month;
                        }



                        SharedPreferences sharedPreferences=getSharedPreferences("price", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("p_night",p_night);
                        editor.putString("p_week",p_week);
                        editor.putString("p_month",p_month);

                        editor.putString("tbhv_night",tbhv_night);
                        editor.putString("tbhv_week",tbhv_week);
                        editor.putString("tbhv_month",tbhv_month);

                        editor.putString("tbd_night",tbd_night);
                        editor.putString("tbd_week",tbd_week);
                        editor.putString("tbd_month",tbd_month);

                        editor.putString("obe_night",obe_night);
                        editor.putString("obe_week",obe_week);
                        editor.putString("obe_month",obe_month);

                        editor.putString("tbs_night",tbs_night);
                        editor.putString("tbs_week",tbs_week);
                        editor.putString("tbs_month",tbs_month);

                        editor.commit();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {



                    }
                });

        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue quee= Volley.newRequestQueue(this);
        quee.add(request);


    }

    private void destroyCookies()
    {

        SharedPreferences preferences=getSharedPreferences("Authentication", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.clear();
        editor.commit();
//        finish();
    }


    private void hideItem()
    {
        SharedPreferences preferences=getSharedPreferences("Authentication", Context.MODE_PRIVATE);
        String mCookies = preferences.getString("cookies",null);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
//        if (mCookies != null)
//        {
//            nav_Menu.findItem(R.id.login).setVisible(false);
//        }
//        else
//        {
//            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
//
//
//            nav_Menu.findItem(R.id.login).setVisible(true);
//
//        }


    }



void getDirection()
    {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",27.713594, 85.298008, "Retreat Serviced Apartments");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(MainActivity.this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_book) {
            Intent i=new Intent(MainActivity.this,Booking.class);
            startActivity(i);

        } else if (id == R.id.nav_gallery) {
            Intent i=new Intent(this,Gallary_activity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_chat) {
            Intent i=new Intent(this,Chat_start.class);
            startActivity(i);

        }
        else if (id == R.id.nav_contact) {
            Intent i=new Intent(this,Contact_Activity.class);
            startActivity(i);

        } else if (id == R.id.nav_travel) {
            Intent i=new Intent(this,Travel_activity.class);
            startActivity(i);

        } else if (id == R.id.nav_appartments) {
            Intent i=new Intent(this,AppartmentType.class);
            startActivity(i);

        }

        else if (id == R.id.nav_amenities) {
            Intent i=new Intent(this,Amenities_activity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_location) {
            getDirection();



        }
//        else if (id == R.id.nav_logout) {
//            destroyCookies();
//            hideItem();
//
//
//
//        }

        else if (id == R.id.nav_feedback) {
            Intent i=new Intent(this,FeedBack.class);
            startActivity(i);

        }
//        else if (id == R.id.login) {
//            Intent i=new Intent(this,Login_form.class);
//            startActivity(i);
//
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void serviceSuccess(Channel channel) {
        Item item = channel.getItem();

        location.setText(service.getLocation());
        condition.setText(item.getCondition().getDescription());
        temperature.setText(item.getCondition().getTemperature()+"\u00B0 "+channel.getUnits().getTemperature());

    }

    @Override
    public void serviceFalure(Exception exception) {
        weatherlayout.setVisibility(View.GONE);
    }
}
