package project.revision.tap.retre.Rooms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.revision.tap.retre.Adapter.Penthouse_swipe_activity;
import project.revision.tap.retre.Booking;
import project.revision.tap.retre.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class PenthouseHimayalaView extends AppCompatActivity {

    ImageButton mLeft,mRight;
    ViewPager mPenthouse;
    WebView mSecond;
    Penthouse_swipe_activity penthouse_swipe_adapter;
    static String PHVurl= Public_Url.PenthouseHimayalaView;
    String mBody;
    String pish = "<html><head><style type=\"text/css\">@font-face {font-family: 'Raleway';" +
            "src: url(\"file:///android_asset/fonts/Raleway-ExtraLight.ttf\")}body {font-family: 'Raleway';font-size: medium;text-align: justify;}</style></head><body>";
    String pas = "</body></html>";
    TextView night,week,month;
    TextView title;
    Button mBookbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penthhousehimalayaview_layout);
        mPenthouse=(ViewPager)findViewById(R.id.penthouse_rooms);
        penthouse_swipe_adapter=new Penthouse_swipe_activity(this);
        mPenthouse.setAdapter(penthouse_swipe_adapter);

        mSecond=(WebView) findViewById(R.id.penthouse_p2);
        night=(TextView)findViewById(R.id.night_p);
        week=(TextView)findViewById(R.id.week_p);
        month=(TextView)findViewById(R.id.month_p);
        mBookbtn=(Button)findViewById(R.id.phv_book);
        mBookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PenthouseHimayalaView.this,Booking.class);
                i.putExtra("book","penthouse_himalaya_view");
                i.putExtra("unit_name","PENTHOUSE HIMALAYA VIEW");
                startActivity(i);
            }
        });
        SharedPreferences preferences=getSharedPreferences("price", Context.MODE_PRIVATE);
        night.setText(preferences.getString("p_night",""));
        week.setText(preferences.getString("p_week",""));
        month.setText(preferences.getString("p_month",""));
        title=(TextView)findViewById(R.id.p_title);
        Typeface myTypeface= Typeface.createFromAsset(getAssets(), "fonts/Raleway-ExtraLight.ttf");
        title.setTypeface(myTypeface);


        getApi();



        mLeft=(ImageButton)findViewById(R.id.left_nav);
        mRight=(ImageButton)findViewById(R.id.right_nav);

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mPenthouse.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mPenthouse.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mPenthouse.setCurrentItem(tab);
                }

            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mPenthouse.getCurrentItem();
                tab++;
                mPenthouse.setCurrentItem(tab);
            }
        });






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    void getApi()
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,PHVurl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {



                            JSONObject bodyObj=response.getJSONObject("body");
                            JSONArray bodyAry=bodyObj.getJSONArray("und");
                            for (int i=0;i<bodyAry.length();i++)
                            {
                                JSONObject subbodyObj=bodyAry.getJSONObject(i);
                                mBody=subbodyObj.getString("value");

                            }
                            SharedPreferences sharedPreferences=getSharedPreferences("phv", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();

                            editor.putString("body",mBody);
                            editor.commit();


                            String myHtmlString = pish + mBody + pas;



                            mSecond.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        SharedPreferences sharedPreferences=getSharedPreferences("phv",Context.MODE_PRIVATE);

                        mBody=sharedPreferences.getString("body","");
                        String myHtmlString = pish + mBody + pas;
                        mSecond.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);


                    }
                }


        );

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

    }
}
