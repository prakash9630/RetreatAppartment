package project.revision.tap.retre;

import android.content.Intent;
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

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    customSwip customSwip;
    LinearLayout mLinear1,mLinear2,mLinear3,mLinear4;
    LinearLayout mContact,mBook;




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
        mBook=(LinearLayout)findViewById(R.id.booknow);
        mContact=(LinearLayout)findViewById(R.id.contactnow);




        mLinear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AppartmentType.class);
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







mBook.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this,Booking.class);
        startActivity(i);
    }
});






        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
