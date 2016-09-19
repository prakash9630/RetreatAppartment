package project.revision.tap.retre;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prakash on 8/10/2016.
 */
public class Booking extends AppCompatActivity {
    Toolbar toolbar;
    EditText mArrive,mDeparture;
    Spinner mUnit,mPerson;
    ArrayAdapter Unitarray,Personarray;
    String Arrive,departure,currentdate;
    Button mCheck;
    Calendar myCalendar = Calendar.getInstance();
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
        mUnit = (Spinner) findViewById(R.id.spinner_unit);
        mPerson = (Spinner) findViewById(R.id.spinner_person);
        mCheck=(Button)findViewById(R.id.check);
        Unitarray = ArrayAdapter.createFromResource(this, R.array.Units, android.R.layout.simple_spinner_item);
        Unitarray.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        mUnit.setAdapter(Unitarray);

        Personarray = ArrayAdapter.createFromResource(this, R.array.person, android.R.layout.simple_spinner_item);
        Personarray.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        mPerson.setAdapter(Personarray);


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        currentdate = sdf.format(new Date());


        final DatePickerDialog.OnDateSetListener ArivalDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
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
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDeparture();
            }

        };



        mArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Booking.this, R.style.DialogTheme, ArivalDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        mDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Booking.this, R.style.DialogTheme, DepartureDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Arrive = mArrive.getText().toString();
                    departure = mDeparture.getText().toString();
                   isDateAfter(Arrive,departure,currentdate);
                }
            });











    }

    void isDateAfter(String arrive,String departure,String current)
    {
        try
        {

            String myFormatString = "MM/dd/yyyy"; // for example
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date date1 = df.parse(departure);
            Date startingDate = df.parse(arrive);
            Date currentdate=df.parse(current);

            if (startingDate.before(currentdate))
                Toast.makeText(Booking.this, "Check your arrival date correctly", Toast.LENGTH_SHORT).show();
            else if(date1.after(startingDate))
            {
                Toast.makeText(Booking.this, "Date is accurate", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(Booking.this, "Departure should be grater than arrival date", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {


        }
    }


    private void updateArrival() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);

        mArrive.setText(sdf.format(myCalendar.getTime()));
    }
    private void updateDeparture() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        mDeparture.setText(sdf.format(myCalendar.getTime()));
    }






}
