package project.revision.tap.retre.Booking_process;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.revision.tap.retre.Main_LandigPage_class.Complet_paymetnt;
import project.revision.tap.retre.Main_LandigPage_class.MainActivity;
import project.revision.tap.retre.Main_LandigPage_class.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 11/4/2016.
 */
public class Checkout extends AppCompatActivity {
    static Spinner mCountry;
    String country;
    String mArrival, mDeparture, mUnit;
    Button mCheck;
    EditText mFullname, mEmail, mAddress, mCity,mPostalcode;
    String name, email, address, city;

    String mUnitType, mUnitPrice, mUnitno,mUnitMachineName;
    static String countryCode;

    String url = Public_Url.Orderapi;

    String sendArival, sendDeparture;
    String mPrice;
    Double price;
    Integer created;
    Integer order_id;

    TextView mProduct,mPriceconform,mQuality,mTotal;


    Locale[] locale = Locale.getAvailableLocales();
    ArrayList<String> countries = new ArrayList<String>();

    PayPalConfiguration m_configuration;
//        String mClintId="AZgk2hCu3i968ZBiuXHzPgabxExbdxbeO2q5U4cWObpzMdIb8qgVAlV3CKAT";    // this is for live account
    String mClintId="AbMfQTit8DAiwk_VUgJUJFHPtbTRWi5s-vanrqcI1ruXbfwDEmu3ysYk9UyVTLCB2bVBqp7ljP37zZAZ";  //this is sandbox account
    Intent mServices;
    int m_paypalRequestCode=1;

    int result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_layout);
        mFullname = (EditText) findViewById(R.id.check_fullname);
        mEmail = (EditText) findViewById(R.id.check_email);
        mAddress = (EditText) findViewById(R.id.check_address);
        mCity = (EditText) findViewById(R.id.check_city);
        mPostalcode=(EditText)findViewById(R.id.check_postalcode);

        Intent intent = getIntent();
        mUnitType = intent.getStringExtra("Unit_Type");
        mUnitPrice = intent.getStringExtra("Unit_Price");
        mUnitno = intent.getStringExtra("Unit_no");
        mUnitMachineName=intent.getStringExtra("Unit_machinename");

//        mPrice=String.valueOf(mUnitPrice);
        result = Integer.parseInt(mUnitPrice)*Integer.parseInt(mUnitno);

        mPrice=Integer.toString(result);






        mProduct=(TextView)findViewById(R.id.text_product);
        mPriceconform=(TextView)findViewById(R.id.text_price);
        mQuality=(TextView)findViewById(R.id.text_quantity);
        mTotal=(TextView)findViewById(R.id.text_total);


        m_configuration=new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(mClintId);
        mServices=new Intent(this, PayPalService.class);
        mServices.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        startService(mServices);



        SharedPreferences sharedPreferences=getSharedPreferences("booking", Context.MODE_PRIVATE);
        mArrival=sharedPreferences.getString("ArrivalDate","");
        mDeparture=sharedPreferences.getString("DepartureDate","");




        mProduct.setText("Product      " +
                "" +
                ": Booking for "+mUnitType+" from "+mArrival+" to "+mDeparture);
        mPriceconform.setText("Price           : $"+mUnitPrice);
        mQuality.setText("Quantity     : "+mUnitno);
        price=Double.parseDouble(mUnitno)*Double.parseDouble(mUnitPrice);
        mTotal.setText("Total price : $"+price);










        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }








        sendArival = parseDateToddMMyyyy(mArrival);
        sendDeparture = parseDateToddMMyyyy(mDeparture);



        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        mCountry = (Spinner) findViewById(R.id.checkout_spinner);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        mCountry.setAdapter(adapter);
        mCheck = (Button) findViewById(R.id.check_button);
        final Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }




        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countryCode=countries.get(mCountry.getSelectedItem());
                name = mFullname.getText().toString();
                email = mEmail.getText().toString();
                address = mAddress.getText().toString();
                city = mCity.getText().toString();


                if (name.length() < 1) {

                    mFullname.setError("Please fill you name");
                } else if (address.length() < 1) {
                    mAddress.setError("Please fill your address");
                } else if (city.length() < 1) {
                    mCity.setError("Please enter your city");
                } else if (!isValid(email)) {
                    mEmail.setError("Set valid email address");
                } else {

                    if (isOnline()) {




                        hitOrder();


                    } else {
                        Toast.makeText(Checkout.this, "No internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }
            }

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

    public static boolean isValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "dd-MM-yyyy";
        String outputPattern = "yyyy-MMM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    void hitOrder()
    {
        final StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject obj=new JSONObject(response);

//                    String profile_id=obj.getString("profile_id");
//                    String totalamount=obj.getString("total_payment_amount");
                    JSONObject order=obj.getJSONObject("order_id");
                    order_id=order.getInt("order_id");
                    created=order.getInt("created");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pay();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Checkout.this,"Error occur", Toast.LENGTH_SHORT).show();
            }
        })
        {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("country",countryCode);
                params.put("locality",mAddress.getText().toString());
                params.put("postal_code",mPostalcode.getText().toString());
                params.put("thoroughfare",mCity.getText().toString());
                params.put("start_date",sendArival);
                params.put("end_date",sendDeparture);
                params.put("group_size","3");
                params.put("mail",mEmail.getText().toString());
                params.put("rooms_unit_type",mUnitMachineName);
                params.put("total_price_per_unit",mUnitPrice);
                params.put("quantity",mUnitno);
                params.put("name_line",name);
                return params;






            }


        };
        RequestQueue queue=Volley.newRequestQueue(this);
        queue.add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }





    void  aprovalDilog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Checkout.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Payment approval");

        // Setting Dialog Message
        alertDialog.setMessage("Your payment has been made successfully");

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i=new Intent(Checkout.this,Complet_paymetnt.class);
                i.putExtra("order_id",""+order_id);
                i.putExtra("unitType",mUnitType);
                i.putExtra("mEmail",mEmail.getText().toString());
                startActivity(i);


            }
        });

        // Showing Alert Message
        alertDialog.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == m_paypalRequestCode)
        {

            if (resultCode == Activity.RESULT_OK)
            {

                PaymentConfirmation confirmation=data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null)
                {
                    try {
                        Log.i("paymentExample", confirmation.toJSONObject().toString(4));
                        Log.i("paymentExample", confirmation.getPayment().toJSONObject()
                                .toString(4));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String state=confirmation.getProofOfPayment().getState();
                    if (state.equals("approved")) {
                        aprovalDilog();


                        mFullname.setText("");
                        mEmail.setText("");
                        mAddress.setText("");
                        mCity.setText("");
                        mPostalcode.setText("");

                    }
                    else
                    {
                        Toast.makeText(this, "error in payment please try again", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(this, "conformation is null", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    void pay()
    {
        PayPalPayment payment=new PayPalPayment(new BigDecimal(price),"USD","Order "+ order_id +" at Retreat Serviced Apartments Kathmandu,Nepal",
                PayPalPayment.PAYMENT_INTENT_SALE);
        payment.invoiceNumber(order_id+"-"+created);
        Intent intent=new Intent(this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,m_paypalRequestCode);


    }


}