package project.revision.tap.retre.Credentials;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import project.revision.tap.retre.Main_LandigPage_class.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 11/16/2016.
 */
public class ResetPassword extends AppCompatActivity {
    Toolbar toolbar;
    EditText mReset;
    Button mResetbtn;
    static  String url= Public_Url.PasswordReset;
    ProgressDialog pDilog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword_layout);
        toolbar=(Toolbar)findViewById(R.id.reset_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mReset=(EditText)findViewById(R.id.resetusername_id);
        mResetbtn=(Button)findViewById(R.id.resetbtn_id);

        pDilog=new ProgressDialog(this);
        pDilog.setMessage("In progress .....");
        pDilog.setCancelable(false);

        mResetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mReset.getText().toString();
                if (name.length()<1)
                {
                    mReset.setError("Please enter Username or Email");
                }
                else {

                if (isOnline()) {
                    passwordReset();
                    pDilog.show();
                }
                else
                {
                    Toast.makeText(ResetPassword.this, "No internet connection", Toast.LENGTH_SHORT).show();

                }



            }}
        });


    }



    void passwordReset()
    {

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response!=null) {
                    Toast.makeText(ResetPassword.this, "Your password has been reset check your email", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ResetPassword.this, Login_form.class);
                    startActivity(intent);
                    pDilog.dismiss();
                }
                else
                {
                    Toast.makeText(ResetPassword.this, "Connection problem", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ResetPassword.this,"Username not matched", Toast.LENGTH_SHORT).show();
                pDilog.dismiss();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parms=new HashMap<>();
                parms.put("name",mReset.getText().toString());
                return parms;


            }

        };
        RequestQueue que= Volley.newRequestQueue(this);
        que.add(request);


    request.setRetryPolicy(new RetryPolicy() {
        @Override
        public int getCurrentTimeout() {
            return 50000;
        }

        @Override
        public int getCurrentRetryCount() {
            return 50000;
        }

        @Override
        public void retry(VolleyError error) throws VolleyError {

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

}
