package project.revision.tap.retre;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prakash on 8/10/2016.
 */
public class Booking extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    Toolbar toolbar;
    TextView mFirstunit,mUnitType,mUnittypename;
    EditText mArrive,mDeparture;
    Spinner mUnit;
    ArrayAdapter Unitarray,Personarray,UnitTypes;
    String Arrive,departure,currentdate;
    Button mCheck;
    Calendar myCalendar = Calendar.getInstance();
    LinearLayout mUnit1,mUnit2,mUnit3,Unit_typelayout;
    Spinner mUnitone,mUnittwo,mUnitthree;

    String mType,mUnitname;

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
        mUnitone=(Spinner)findViewById(R.id.spinner_person1);
        mUnittwo=(Spinner)findViewById(R.id.spinner_person2);
        mUnitthree=(Spinner)findViewById(R.id.spinner_person3);
   mFirstunit=(TextView)findViewById(R.id.first_unit);
//        Unit_typelayout=(LinearLayout)findViewById(R.id.type_layout);

//        mUnitType=(TextView)findViewById(R.id.type_hint);
//        mUnitTypename=(Spinner)findViewById(R.id.Unit_type);

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
//        if (mType=="1_bedroom_executive")
//        {
//            Toast.makeText(Booking.this, "OneBedRoomExecutive", Toast.LENGTH_SHORT).show();
//        }
//        if (mType==null)
//        {
//            mType="All";
//        }
//        if (mType=="2_bedroom_deluxe")
//        {
//            Toast.makeText(Booking.this, "twobedroomdelux", Toast.LENGTH_SHORT).show();
//
//        }
//        if (mType=="penthouse_himalaya_view")
//        {
//            Toast.makeText(Booking.this, "Penthouse", Toast.LENGTH_SHORT).show();
//
//        }
//        if (mType=="3_bedroom_himalaya_view")
//        {
//            Toast.makeText(Booking.this, "three bedroom", Toast.LENGTH_SHORT).show();
//
//        }
//        if (mType=="2_bedroom_standard")
//        {
//            Toast.makeText(Booking.this, "value for money", Toast.LENGTH_SHORT).show();
//
//        }







        mUnit.setOnItemSelectedListener(this);
        mUnit1=(LinearLayout)findViewById(R.id.unit1);
        mUnit2=(LinearLayout)findViewById(R.id.unit2);

        mUnit3=(LinearLayout)findViewById(R.id.unit3);




        Personarray = ArrayAdapter.createFromResource(this, R.array.person, android.R.layout.simple_spinner_item);
        Personarray.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        mUnitone.setAdapter(Personarray);
        mUnittwo.setAdapter(Personarray);
        mUnitthree.setAdapter(Personarray);
//        UnitTypes = ArrayAdapter.createFromResource(this, R.array.UnitType, android.R.layout.simple_spinner_item);
//        UnitTypes.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
//        mUnitTypename.setAdapter(UnitTypes);







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









                        Arrive = mArrive.getText().toString();
                    departure = mDeparture.getText().toString();
                   isDateAfter(Arrive,departure,currentdate);
                }}
            });











    }

//    void chooseUnit()
//    {
//
//        if (mUnitTypename.getSelectedItem()=="---- Any ----")
//        {
//
//            mType="All";
//        }
//        else if (mUnitTypename.getSelectedItem()=="1 Bedroom Executive")
//        {
//            mType="1_bedroom_executive";
//            mUnitname="1 BEDROOM EXECUTIVE";
//
//        }
//        else if (mUnitTypename.getSelectedItem()=="Two Bedroom Standard")
//        {
//            mType="2_bedroom_standard";
//            mUnitname="2 BEDROOM STANDARD";
//        }
//
//        else if (mUnitTypename.getSelectedItem()=="Penthouse Himalaya View")
//        {
//            mType="penthouse_himalaya_view";
//            mUnitname="PENTHOUSE HIMALAYA VIEW";
//        }
//
//        else if (mUnitTypename.getSelectedItem()=="Three Bedroom Himalaya View")
//        {
//            mType="3_bedroom_himalaya_view";
//            mUnitname="3 BEDROOM HIMALAYA VIEW";
//        }
//
//        else if (mUnitTypename.getSelectedItem()=="Two Bedroom Deluxe")
//        {
//            mType="2_bedroom_deluxe";
//            mUnitname="2 BEDROOM DELUXE";
//        }
//
//    }







    void isDateAfter(String arrive,String departure,String current)
    {
        try
        {

            String myFormatString = "dd-MM-yyyy"; // for example
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date date1 = df.parse(departure);
            Date startingDate = df.parse(arrive);
            Date currentdate=df.parse(current);

            if (startingDate.before(currentdate))
                Toast.makeText(Booking.this, "Check your arrival date correctly", Toast.LENGTH_SHORT).show();
            else if(date1.after(startingDate))
            {
//                Toast.makeText(Booking.this, "Date is accurate", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(this,Available_rooms.class);

                i.putExtra("mtype",mType);
                i.putExtra("unit_name",mUnitname);
                i.putExtra("arrival",mArrive.getText().toString());
                i.putExtra("departure",mDeparture.getText().toString());
                i.putExtra("unit",Integer.parseInt(mUnit.getSelectedItem().toString()));

                i.putExtra("u1",mUnitone.getSelectedItem().toString());
                i.putExtra("u2",mUnittwo.getSelectedItem().toString());
                i.putExtra("u3",mUnitthree.getSelectedItem().toString());
                startActivity(i);
                String mArrivalDate=mArrive.getText().toString();
                String mDepartureDate=mDeparture.getText().toString();
                String mUnitRoom=mUnit.getSelectedItem().toString();


                SharedPreferences sharedPreferences=getSharedPreferences("booking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("ArrivalDate",mArrivalDate);
                editor.putString("DepartureDate",mDepartureDate);
                editor.putString("UnitRoom",mUnitRoom);
                editor.commit();

            }
            else
                Toast.makeText(Booking.this, "Departure should be grater than arrival date", Toast.LENGTH_SHORT).show();
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


        if (selectedItem.equals("1"))
        {

            mFirstunit.setVisibility(View.GONE);

            mUnit1.setVisibility(View.VISIBLE);
            mUnit2.setVisibility(View.GONE);
            mUnit3.setVisibility(View.GONE);


        }
        else if (selectedItem.equals("2"))
        {
            mFirstunit.setVisibility(View.VISIBLE);
            mUnit1.setVisibility(View.VISIBLE);
            mUnit2.setVisibility(View.VISIBLE);
            mUnit3.setVisibility(View.GONE);


        }
        else
        {
            mFirstunit.setVisibility(View.VISIBLE);
            mUnit1.setVisibility(View.VISIBLE);
            mUnit2.setVisibility(View.VISIBLE);
            mUnit3.setVisibility(View.VISIBLE);



        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
