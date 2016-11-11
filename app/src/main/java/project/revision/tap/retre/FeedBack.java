package project.revision.tap.retre;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by prakash on 9/6/2016.
 */
public class FeedBack extends AppCompatActivity {
    Toolbar toolbar;
    EditText mName,mEmail,mMessage;
    Button mSent;
    String possibleEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);
        toolbar=(Toolbar)findViewById(R.id.nav_feedback);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mName=(EditText)findViewById(R.id.feedback_name);
        mEmail=(EditText)findViewById(R.id.feedback_email);

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        android.accounts.Account[] accounts = AccountManager.get(getBaseContext()).getAccounts();
        for (android.accounts.Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                 possibleEmail = account.name;
            }
        }
        mEmail.setText(possibleEmail);
        mMessage=(EditText)findViewById(R.id.feedback_message);
        mSent=(Button)findViewById(R.id.feedback_send);

        mSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });






    }
    public static boolean isValid(String email)
    {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }
    }

    void validation()
    {
        String name=mName.getText().toString();
        String email=mEmail.getText().toString();
        String message=mMessage.getText().toString();

        if (name.length()<1)
        {
            mName.setError("Please enter your name");
        }
        else if (message.length()<1)
        {
            mMessage.setError("Please type feedback");

        }
        else  if(!isValid(email))
        {

            mEmail.setError("Email is not valid");

        }
        else
        {
          if(isOnline())
          {
              Toast.makeText(FeedBack.this, "Validation is accurate", Toast.LENGTH_SHORT).show();


              Intent Email = new Intent(Intent.ACTION_SEND);

              Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "zzruven@gmail.com" });
              Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
              Email.putExtra(Intent.EXTRA_TEXT, message);
              Email.setType("message/rfc822");
              try {
                  startActivity(Intent.createChooser(Email, "Send mail..."));
              } catch (android.content.ActivityNotFoundException ex) {
                  Toast.makeText(FeedBack.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
              }
          }
            else
          {
              Toast.makeText(FeedBack.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
          }
        }


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
