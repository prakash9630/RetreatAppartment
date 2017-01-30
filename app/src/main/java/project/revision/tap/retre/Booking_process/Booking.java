package project.revision.tap.retre.Booking_process;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/10/2016.
 */
public class Booking extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    Toolbar toolbar;
    TextView mFirstunit,mUnitType,mUnittypename;
    EditText mArrive,mDeparture;
    Spinner mUnit;
    ArrayAdapter Unitarray,Personarray;
    String Arrive,departure,currentdate;
    Button mCheck;
    Calendar myCalendar = Calendar.getInstance();
    LinearLayout mUnit1,mUnit2,mUnit3;
    Spinner mUnitone,mUnittwo,mUnitthree;

    String mType,mUnitname;
    TextView checkin,checkout,textunit,textperson,unittwo,unitthree,unitperson;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);
        toolbar = (Toolbar) findViewById(R.id.booking_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mArrive = (EditText) findViewById(R.id.arrival_date);
        mDeparture = (EditText) findViewById(R.id.departure_date);
//        mUnitone=(Spinner)findViewById(R.id.spinner_person1);
//        mUnittwo=(Spinner)findViewById(R.id.spinner_person2);
//        mUnitthree=(Spinner)findViewById(R.id.spinner_person3);
//   mFirstunit=(TextView)findViewById(R.id.first_unit);
//        unitperson=(TextView)findViewById(R.id.textPerson);

        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/Raleway-ExtraLight.ttf");

        checkin=(TextView)findViewById(R.id.checkin);
        checkout=(TextView)findViewById(R.id.checkout);
        textunit=(TextView)findViewById(R.id.textUnit);

//        textperson=(TextView)findViewById(R.id.textPerson);
//
//
//        unittwo=(TextView)findViewById(R.id.textunittwo);
//
//        unitthree=(TextView)findViewById(R.id.textunitthree);

        checkin.setTypeface(face);
        checkout.setTypeface(face);
        textunit.setTypeface(face);
//        mFirstunit.setTypeface(face);
//        unittwo.setTypeface(face);
//        unitthree.setTypeface(face);
//        textperson.setTypeface(face);






        mCheck=(Button)findViewById(R.id.check);
        Unitarray = ArrayAdapter.createFromResource(this, R.array.Units, android.R.layout.simple_spinner_item);
        Unitarray.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        mUnit=(Spinner)findViewById(R.id.spinner_unit);
        mUnit.setAdapter(Unitarray);
        mUnittypename=(TextView)findViewById(R.id.unit_name);

       mType=getIntent().getStringExtra("book");
        mUnitname=getIntent().getStringExtra("unit_name");
        if (mUnitname!=null) {


            mUnittypename.setText(mUnitname + " was selected");
        }
        else
        {
            mUnittypename.setVisibility(View.GONE);
        }




        mUnit.setOnItemSelectedListener(this);
//        mUnit1=(LinearLayout)findViewById(R.id.unit1);
//        mUnit2=(LinearLayout)findViewById(R.id.unit2);
//
//        mUnit3=(LinearLayout)findViewById(R.id.unit3);




        Personarray = ArrayAdapter.createFromResource(this, R.array.person, android.R.layout.simple_spinner_item);
        Personarray.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
//        mUnitone.setAdapter(Personarray);
//        mUnittwo.setAdapter(Personarray);
//        mUnitthree.setAdapter(Personarray);








        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        currentdate = sdf.format(new Date());




        final DatePickerDialog.OnDateSetListener ArivalDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                view.setMinDate(System.currentTimeMillis() - 1000);

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateArrival();
            }

        };


        final DatePickerDialog.OnDateSetListener DepartureDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                view.setMinDate(System.currentTimeMillis() - 1000);
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateDeparture();
            }

        };



        mArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 DatePickerDialog mDate= new DatePickerDialog(Booking.this, R.style.DialogTheme, ArivalDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate.getDatePicker().setMinDate(System.currentTimeMillis()- 1000);

                mDate.show();





            }
        });



        mDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatePickerDialog mDate1= new DatePickerDialog(Booking.this, R.style.DialogTheme, DepartureDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                mDate1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDate1.show();

            }
        });








            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mArrive.length()<1)
                    {
                        Toast.makeText(Booking.this, "Please enter arrival date", Toast.LENGTH_SHORT).show();
                    }
                   else if(mDeparture.length()<1)
                    {
                        Toast.makeText(Booking.this, "Please enter departure date", Toast.LENGTH_SHORT).show();
                    }
                    else {






                             if (isOnline()) {


                                 Arrive = mArrive.getText().toString();
                                 departure = mDeparture.getText().toString();
                                 isDateAfter(Arrive, departure, currentdate);
                             }else
                             {

                                 Toast.makeText(Booking.this,"No internet connection", Toast.LENGTH_SHORT).show();
                             }
                }}
            });











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


    void isDateAfter(String arrive,String departure,String current)
    {
        try
        {

            String myFormatString = "dd-MM-yyyy"; // for example
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date date1 = df.parse(departure);
            Date startingDate = df.parse(arrive);
            Date currentdate=df.parse(current);

            if (startingDate.equals(currentdate))
                Toast.makeText(Booking.this, "Booking can only start from 1 day in advance", Toast.LENGTH_SHORT).show();
            else if(date1.after(startingDate))
            {


                Intent i=new Intent(this,Available_rooms.class);

                i.putExtra("mtype",mType);
                i.putExtra("unit_name",mUnitname);
                i.putExtra("arrival",mArrive.getText().toString());
                i.putExtra("departure",mDeparture.getText().toString());
                i.putExtra("unit",Integer.parseInt(mUnit.getSelectedItem().toString()));

//                i.putExtra("u1",mUnitone.getSelectedItem().toString());
//                i.putExtra("u2",mUnittwo.getSelectedItem().toString());
//                i.putExtra("u3",mUnitthree.getSelectedItem().toString());
                startActivity(i);
                String mArrivalDate=mArrive.getText().toString();
                String mDepartureDate=mDeparture.getText().toString();
                String mUnitRoom=mUnit.getSelectedItem().toString();


                SharedPreferences sharedPreferences=getSharedPreferences("booking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("ArrivalDate",mArrivalDate);
                editor.putString("DepartureDate",mDepartureDate);
                editor.putString("UnitRoom",mUnitRoom);
                editor.putString("UnitName",mUnitname);
                editor.commit();

            }
            else
                Toast.makeText(Booking.this, "Departure date should be greater than the arrival date", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {


        }
    }



    private void updateArrival() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);

        mArrive.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateDeparture() {

        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        mDeparture.setText(sdf.format(myCalendar.getTime()));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedItem = parent.getItemAtPosition(position).toString();


//        if (selectedItem.equals("1"))
//        {
//
//            mFirstunit.setVisibility(View.GONE);
//
//            mUnit1.setVisibility(View.VISIBLE);
//            mUnit2.setVisibility(View.GONE);
//            mUnit3.setVisibility(View.GONE);
//
//
//        }
//        else if (selectedItem.equals("2"))
//        {
//            mFirstunit.setVisibility(View.VISIBLE);
//            mUnit1.setVisibility(View.VISIBLE);
//            mUnit2.setVisibility(View.VISIBLE);
//            mUnit3.setVisibility(View.GONE);
//
//
//        }
//        else
//        {
//            mFirstunit.setVisibility(View.VISIBLE);
//            mUnit1.setVisibility(View.VISIBLE);
//            mUnit2.setVisibility(View.VISIBLE);
//            mUnit3.setVisibility(View.VISIBLE);
//
//
//
//        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
