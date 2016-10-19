package project.revision.tap.retre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prakash on 10/17/2016.
 */
public class Login_form extends AppCompatActivity {
    EditText mUsername,mPassword;
    Button mLogin,mSignup;
    TextView mForgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mUsername= (EditText) findViewById(R.id.username_id);
        mPassword= (EditText) findViewById(R.id.password_id);
        mLogin= (Button) findViewById(R.id.login_id);
        mSignup=(Button)findViewById(R.id.signup_id);
        mForgot=(TextView)findViewById(R.id.forgot_id);


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
                    Toast.makeText(Login_form.this, "Credencials are acurated", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    void login()
    {


    }
}
