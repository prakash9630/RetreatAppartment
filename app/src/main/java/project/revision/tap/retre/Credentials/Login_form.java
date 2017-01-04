package project.revision.tap.retre.Credentials;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import project.revision.tap.retre.Main_LandigPage_class.MainActivity;
import project.revision.tap.retre.Main_LandigPage_class.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 10/17/2016.
 */
public class Login_form extends AppCompatActivity {
    EditText mUsername,mPassword;
    Button mLogin,mSignup;
    TextView mForgot;
    String username;
    Toolbar login_toolbar;
    String sessionid,sessionname,token,Uname,mail,mCookie;



    static  String Loginurl= Public_Url.LoginUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mUsername= (EditText) findViewById(R.id.username_id);
        mPassword= (EditText) findViewById(R.id.password_id);
        mLogin= (Button) findViewById(R.id.login_id);

        mForgot=(TextView)findViewById(R.id.forgot_id);
        login_toolbar=(Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(login_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_form.this,ResetPassword.class);
                startActivity(intent);
            }
        });

        mForgot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mForgot.setPaintFlags(mForgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mForgot.setPaintFlags(mForgot.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));


                }return false;
            }
        });




        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.length()<1)
                {
                    mUsername.setError("Please enter your username");
                }
                if(mPassword.length()<1)
                {
                    mPassword.setError("Please enter your password");
                }
                else
                {
                    if(isOnline())
                    {

                        Login();

                    }
                    else {
                        Toast.makeText(Login_form.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }



    void Login()
    {

        StringRequest request=new StringRequest(Request.Method.POST, Loginurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                    try {

                        if (response!=null)
                        {

                            JSONObject obj = new JSONObject(response);
                            sessionid = obj.getString("sessid");
                            sessionname = obj.getString("session_name");
                            token = obj.getString("token");


                            JSONObject user = obj.getJSONObject("user");

                            Uname = user.getString("name");
                            mail = user.getString("mail");


                            mCookie = sessionname+ "=" + sessionid;
                        }

                        else
                        {
                            Toast.makeText(Login_form.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }




                Intent i=new Intent(Login_form.this,MainActivity.class);
finish();



SharedPreferences preferences=getSharedPreferences("Authentication", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("sessionid",sessionid);
                editor.putString("sessionname",sessionname);
                editor.putString("token",token);
                editor.putString("username",username);
                editor.putString("email",mail);
                editor.putString("cookies",mCookie);
                editor.commit();




                startActivity(i);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login_form.this,"wrong username or password", Toast.LENGTH_LONG).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parms=new HashMap<>();
                parms.put("username", mUsername.getText().toString());
                parms.put("password", mPassword.getText().toString());


                return parms;
            }


        };
        RequestQueue q=Volley.newRequestQueue(this);
        q.add(request);
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
