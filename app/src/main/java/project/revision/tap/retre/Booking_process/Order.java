package project.revision.tap.retre.Booking_process;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 11/23/2016.
 */
public class Order extends AppCompatActivity {
    Toolbar toolbar;
    TextView mProduct,mPrice,mQuality,mTotal;
    String mArrival,mDeparture;
    TextView mUsername,mEmail,mCountry,mAddress,mCity;
    Button mPayment;
    Double price;


    PayPalConfiguration m_configuration;
//    String mClintId="AZgk2hCu3i968ZBiuXHzPgabxExbdxbeO2q5U4cWObpzMdIb8qgVAlV3CKAT";
    String mClintId="ATnoFubq_B8J-JPnv80MP6XiWfrNl0nZXB_KDtekbfZ8JAKtw_3h1pnzPsW1nco9uBEgCbOGdXCpYezp";
   Intent mServices;
    int m_paypalRequestCode=999;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        toolbar=(Toolbar)findViewById(R.id.order_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mProduct=(TextView)findViewById(R.id.text_product);
        mPrice=(TextView)findViewById(R.id.text_price);
        mQuality=(TextView)findViewById(R.id.text_quantity);
        mTotal=(TextView)findViewById(R.id.text_total);
        mUsername=(TextView)findViewById(R.id.user_name);
        mEmail=(TextView)findViewById(R.id.user_email);
        mCountry=(TextView)findViewById(R.id.user_country);
        mAddress=(TextView)findViewById(R.id.user_address);
        mCity=(TextView)findViewById(R.id.user_city);
        mPayment=(Button)findViewById(R.id.payment_btn);

        m_configuration=new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(mClintId);
        mServices=new Intent(this, PayPalService.class);
        mServices.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        startService(mServices);


        SharedPreferences sharedPreferences=getSharedPreferences("booking", Context.MODE_PRIVATE);
        mArrival=sharedPreferences.getString("ArrivalDate","");
        mDeparture=sharedPreferences.getString("DepartureDate","");


        Intent intent=getIntent();
        mProduct.setText("Product      " +
                "" +
                ": Booking for "+intent.getStringExtra("apt_name")+" from "+mArrival+" to "+mDeparture);
        mPrice.setText("Price           : $"+intent.getStringExtra("apt_price"));
        mQuality.setText("Quantity     : "+intent.getStringExtra("apt_unitno"));
         price=Double.parseDouble(intent.getStringExtra("apt_unitno"))*Double.parseDouble(intent.getStringExtra("apt_price"));
mTotal.setText("Total price : $"+price);

        mUsername.setText(getIntent().getStringExtra("user_name"));
        mEmail.setText(getIntent().getStringExtra("user_email"));
        mCountry.setText(getIntent().getStringExtra("user_country"));
        mAddress.setText(getIntent().getStringExtra("user_address"));
        mCity.setText(getIntent().getStringExtra("user_city"));


    }

    void pay(View view)
    {
        PayPalPayment payment=new PayPalPayment(new BigDecimal(price),"USD","Test paypal",
                PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent=new Intent(this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_configuration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,m_paypalRequestCode);


    }

    void  aprovalDilog()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(
                Order.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Payment Approvel");

        // Setting Dialog Message
        alertDialog.setMessage("Your Payment has been made succesfully");

        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.tick);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String state=confirmation.getProofOfPayment().getState();
                    if (state.equals("approved"))
                       aprovalDilog();
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
}
