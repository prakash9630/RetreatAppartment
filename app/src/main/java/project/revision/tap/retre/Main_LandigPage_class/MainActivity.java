package project.revision.tap.retre.Main_LandigPage_class;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.revision.tap.retre.Booking_process.Booking;
import project.revision.tap.retre.Booking_process.Order;
import project.revision.tap.retre.Chat.Chat_start;

import project.revision.tap.retre.Data.Channel;
import project.revision.tap.retre.Data.Item;
import project.revision.tap.retre.Credentials.Login_form;
import project.revision.tap.retre.Gallary_activity.Album_View_Gallery;
import project.revision.tap.retre.Pojo.Main_sliderData;
import project.revision.tap.retre.R;
import project.revision.tap.retre.Services.WeatherServiceCallback;
import project.revision.tap.retre.Services.YahooWeatherService;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,WeatherServiceCallback {

    static String url=Public_Url.galleryAlbum;
    LinearLayout mLinear1,mLinear2,mLinear3,mLinear4,mLinear6,mLinear5;
    LinearLayout mContact,mBook;

    NavigationView navigationView;
    TextView temperature;
    TextView condition;
    TextView location;
    YahooWeatherService service;
    LinearLayout weatherlayout;

    SliderLayout slider;
    ArrayList <Main_sliderData> data;
    Main_sliderData sliderdata;

    String nid,machinename;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        slider=(SliderLayout)findViewById(R.id.slider_rooms);










        mLinear1=(LinearLayout)findViewById(R.id.linear1);
        mLinear2=(LinearLayout)findViewById(R.id.linear2);
        mLinear3=(LinearLayout)findViewById(R.id.linear3);
        mLinear4=(LinearLayout)findViewById(R.id.linear4);
        mLinear5=(LinearLayout)findViewById(R.id.linear5);

        mLinear6=(LinearLayout)findViewById(R.id.linear6);

        mBook=(LinearLayout)findViewById(R.id.booknow);
        mContact=(LinearLayout)findViewById(R.id.contactnow);
        temperature=(TextView)findViewById(R.id.temperature_id);
        condition=(TextView)findViewById(R.id.condition);
        location=(TextView)findViewById(R.id.place);
        weatherlayout=(LinearLayout)findViewById(R.id.weather_layout);


        Runtime.getRuntime().gc();
        hideItem();
        sendRequest();

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
                Intent i=new Intent(MainActivity.this,Apartment_location.class);
                startActivity(i);

            }
        });

        mLinear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Album_View_Gallery.class);
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
                Intent i=new Intent(MainActivity.this,Facilities_activity.class);
                startActivity(i);
            }
        });








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






        mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Booking.class);
                startActivity(i);
            }
        });






        setSupportActionBar(toolbar);
















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


    void sendRequest() {
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(final JSONArray response) {


data=new ArrayList<>();


                for (int i = 0; i < response.length(); i++) {


                    try {



                        final JSONObject obj = response.getJSONObject(i);
                        if (obj != null) {

                            sliderdata=new Main_sliderData();





                          sliderdata.setImage(obj.getString("image_url"));
                            sliderdata.setRoomname(obj.getString("Room Type"));

                             sliderdata.setNid(obj.getString("Nid"));
                            sliderdata.setNid(obj.getString("machine_name"));

                            data.add(sliderdata);

                            final TextSliderView textSliderView = new TextSliderView(MainActivity.this);
                            textSliderView
                                    .description(obj.getString("Room Type"))
                                    .image(obj.getString("image_url"));

//
//                            textSliderView.bundle(new Bundle());
//                            textSliderView.getBundle()
//                                    .putString("extra",obj.getString("Nid"));







                                textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {

Intent ii=new Intent(MainActivity.this,AppartmentType.class);
                                        startActivity(ii);

                                    }
                                });





















                slider.stopAutoCycle();
                slider.addSlider(textSliderView);




                        } else {
                            Toast.makeText(MainActivity.this, "NO data found", Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                       e.printStackTrace();
                    }


                    slider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
                    slider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
//                    slider.setCustomAnimation(new DescriptionAnimation());
                    slider.setDuration(50);



                }







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Check your internet", Toast.LENGTH_SHORT).show();


            }
        });


        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);


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
        if (mCookies != null)
        {
            nav_Menu.findItem(R.id.login).setVisible(false);
        }
        else
        {
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
            nav_Menu.findItem(R.id.nav_order).setVisible(false);

            nav_Menu.findItem(R.id.login).setVisible(true);

        }


    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
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
            Intent i=new Intent(this,Album_View_Gallery.class);
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
            Intent i=new Intent(this,Facilities_activity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_location) {
            Intent i=new Intent(MainActivity.this,Apartment_location.class);
            startActivity(i);



        }
        else if (id == R.id.nav_logout) {
            destroyCookies();
            hideItem();



        }
        else if (id == R.id.nav_order) {
            Intent i=new Intent(MainActivity.this,Order.class);
            startActivity(i);


        }
        else if (id == R.id.nav_feedback) {
            Intent i=new Intent(this,FeedBack.class);
            startActivity(i);

        }
        else if (id == R.id.login) {
            Intent i=new Intent(this,Login_form.class);
            startActivity(i);

        }


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