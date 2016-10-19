package project.revision.tap.retre;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prakash on 9/8/2016.
 */
public class Contact_Activity extends AppCompatActivity {
    Toolbar toolbar;
    TextView phone,mobile,info;
    Button Call,Email;
    ImageView fb,twitter,instagram,gplus,youtube,p;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        toolbar=(Toolbar)findViewById(R.id.contact_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        phone=(TextView)findViewById(R.id.ph_no);
        mobile=(TextView)findViewById(R.id.mobile_no);
        Call=(Button)findViewById(R.id.call_btn);
        Email=(Button)findViewById(R.id.email_btn);
        info=(TextView)findViewById(R.id.link);
        fb=(ImageView) findViewById(R.id.fb_icon);
        twitter=(ImageView) findViewById(R.id.twiter_icon);
        gplus=(ImageView) findViewById(R.id.gplus_icon);
        youtube=(ImageView) findViewById(R.id.youtube_icon);
        p=(ImageView) findViewById(R.id.p_icond);
        instagram=(ImageView)findViewById(R.id.insta_icon);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/retreatapartment/?fref=ts"));
                startActivity(intent);
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/follow?original_referer=https%3A%2F%2Ffbapp.us%2Fapps%2Ftwittertab%2F&ref_src=twsrc%5Etfw&screen_name=RetreatKTM&tw_p=followbutton"));
                startActivity(intent);

            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/retreatsa/"));
                startActivity(intent);

            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.pinterest.com/retreatserviced/"));
                startActivity(intent);

            }
        });
        gplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/+RetreatKathmandu/"));
                startActivity(intent);

            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC2Wh76L9BkMWzKjw9KfAjJg"));
                startActivity(intent);

            }
        });



        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:014274837"));
                startActivity(i);
            }
        });
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:9801098941"));
                startActivity(i);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://info@retreatapartment.com/"));
                startActivity(viewIntent);
            }
        });

Email.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL,
                new String[] {"retreatservicedapartments@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject");
        i.putExtra(Intent.EXTRA_TEXT, "message");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Contact_Activity.this,
                    "There are no email clients installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
});
        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:9801098941"));
                startActivity(i);
            }
        });
        phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    phone.setPaintFlags(phone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    phone.setPaintFlags(phone.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));


                }return false;
            }
        });
        mobile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    mobile.setPaintFlags(mobile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mobile.setPaintFlags(mobile.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));


                }return false;
            }
        });
        info.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    info.setPaintFlags(info.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    info.setPaintFlags(info.getPaintFlags() & (~ Paint.UNDERLINE_TEXT_FLAG));


                }return false;
            }
        });


    }
}
