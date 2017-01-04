package project.revision.tap.retre.Credentials;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import project.revision.tap.retre.R;

/**
 * Created by prakash on 10/17/2016.
 */
public class Sign_up extends AppCompatActivity {
    EditText mUsername,mPassword,mConfirm,mEmail;
    Button mSign_up;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);


        mSign_up=(Button)findViewById(R.id.signup_page_id);

        mSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
