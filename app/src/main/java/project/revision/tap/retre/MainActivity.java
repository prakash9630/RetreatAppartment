package project.revision.tap.retre;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Location;
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
import android.widget.Toast;

import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    customSwip customSwip;
    LinearLayout mLinear1,mLinear2,mLinear3,mLinear4,mlinear8,mLinear7,mLinear6,mLinear5;
    LinearLayout mContact,mBook;
    Location location;




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
        mlinear8=(LinearLayout)findViewById(R.id.linear8);
        mLinear6=(LinearLayout)findViewById(R.id.linear6);
        mLinear7=(LinearLayout)findViewById(R.id.linear7);
        mBook=(LinearLayout)findViewById(R.id.booknow);
        mContact=(LinearLayout)findViewById(R.id.contactnow);

        Runtime.getRuntime().gc();


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
                Intent i=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
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
                         String uri = String.format(Locale.ENGLISH, "geo:%f,%f",27.713308, 85.297986);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

                startActivity(intent);



            }
        });
        mLinear7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Amenities_activity.class);
                startActivity(i);
            }
        });
mlinear8.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this,FeedBack.class);
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

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_travel) {

        } else if (id == R.id.nav_appartments) {
            Intent i=new Intent(this,AppartmentType.class);
            startActivity(i);

        } else if (id == R.id.nav_emergency_call) {
            Intent i=new Intent(this,Emergency_calls.class);
            startActivity(i);

        }
        else if (id == R.id.nav_amenities) {

        }
        else if (id == R.id.nav_location) {

        }
        else if (id == R.id.nav_feedback) {
            Intent i=new Intent(this,FeedBack.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
