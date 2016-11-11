package project.revision.tap.retre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prakash on 10/17/2016.
 */
public class Login_form extends AppCompatActivity {
    EditText mUsername,mPassword;
    Button mLogin,mSignup;
    TextView mForgot;
    String username,password;

    static  String Loginurl=Public_Url.LoginUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mUsername= (EditText) findViewById(R.id.username_id);
        mPassword= (EditText) findViewById(R.id.password_id);
        mLogin= (Button) findViewById(R.id.login_id);
        mSignup=(Button)findViewById(R.id.signup_id);
        mForgot=(TextView)findViewById(R.id.forgot_id);
        login();


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


                }
            }
        });



    }



    void login()
    {
        username=mUsername.getText().toString();
        password=mPassword.getText().toString();

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, Loginurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (response==null)
                {
                    Toast.makeText(Login_form.this, "there is nothing to display", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(Login_form.this,response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Login_form.this,error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<String, String>();
                params.put("username","guestuser");
                params.put("password","password");

                return  params;

            }



        };

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

//    void Login()
//    {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Loginurl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(Login_form.this, response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Login_form.this, error.toString(), Toast.LENGTH_SHORT).show();                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("username",mUsername.getText().toString());
//                params.put("password",mPassword.getText().toString());
//
//                return params;
//            }
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }


}
