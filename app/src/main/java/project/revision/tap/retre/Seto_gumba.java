package project.revision.tap.retre;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by prakash on 9/26/2016.
 */
public class Seto_gumba extends AppCompatActivity {
Button button;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seto_gumba_layout);
        button=(Button)findViewById(R.id.s_google_map);
        toolbar=(Toolbar)findViewById(R.id.seto_gumba_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline())
                {
                    getDirection();
                }
                else
                {
                    Toast.makeText(Seto_gumba.this, "There is no Internet connection", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
void getDirection()
{
    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)",27.744437, 85.368351, "Druk Amitabh Monastery");
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
            Toast.makeText(Seto_gumba.this, "Please install a maps application", Toast.LENGTH_LONG).show();
        }
    }
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
}
