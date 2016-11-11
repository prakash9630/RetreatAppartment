package project.revision.tap.retre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by prakash on 11/4/2016.
 */
public class Checkout extends AppCompatActivity {
    Spinner mCountry;
    String country;
    String mArrival,mDeparture,mUnit;
    Button mCheck;
    EditText mFullname,mEmail,mAddress,mCity;
    String name,email,address,city;



    Locale[] locale = Locale.getAvailableLocales();
    ArrayList<String> countries = new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_layout);
        mFullname=(EditText) findViewById(R.id.check_fullname);
        mEmail=(EditText) findViewById(R.id.check_email);
        mAddress=(EditText) findViewById(R.id.check_address);
        mCity=(EditText) findViewById(R.id.check_fullname);





        Toast.makeText(Checkout.this, name+""+email+""+address+""+city, Toast.LENGTH_LONG).show();

        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        SharedPreferences sharedPreferences=getSharedPreferences("booking", Context.MODE_PRIVATE);
        mArrival=sharedPreferences.getString("ArrivalDate","");
        mDeparture=sharedPreferences.getString("DepartureDate","");
        mUnit=sharedPreferences.getString("UnitRoom","");







        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        mCountry=(Spinner)findViewById(R.id.checkout_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        mCountry.setAdapter(adapter);
        mCheck=(Button)findViewById(R.id.check_button);

        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=mFullname.getText().toString();
                email=mEmail.getText().toString();
                address=mAddress.getText().toString();
                city=mCity.getText().toString();

                if (name.length()<0)
                {
                    mFullname.setError("Please fill you name");
                }
                 else if (address.length()<0)
                {
                    mAddress.setError("Please fill your address");
                }
                else if (city.length()<0)
                {
                    mCity.setError("Please enter your city");
                }
                else if (!isValid(email))
                {
                    mEmail.setError("Set valid email address");
                }
                else {



                    String body="<html><head><title>Title</title></head><body>This is random text.</body></html>";
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"zzruven@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "Booking request");


                    i.putExtra(Intent.EXTRA_TEXT, "Full name :" + name + " Email : " + email + " Country :" + mCountry.getSelectedItem() + " Address " + address + " City " + city + " Arrival date :" + mArrival + " Departure date " + mDeparture + " Unit :" + mUnit + " Unit name :" + getIntent().getStringExtra("Unit_Type") + " Unit_Price :$" + getIntent().getStringExtra("Unit_Price"));
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                        Intent ii=new Intent(Checkout.this,MainActivity.class);
                        startActivity(ii);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Checkout.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });





    }


    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }
}
