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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.revision.tap.retre.Adapter.ThreeBedroomHimalayaView_adapter;
import project.revision.tap.retre.Booking_process.Booking;
import project.revision.tap.retre.Main_LandigPage_class.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class ThreeBedRoomHimayalaView extends AppCompatActivity {
    ViewPager mThreebedhimalayaview;
    ThreeBedroomHimalayaView_adapter adapter;
    ImageButton mRight,mLeft;
    WebView mFirst,mSecond;
    String subHeader_body;
    String body;
    String pish = "<html><head><style type=\"text/css\">@font-face {font-family: 'Raleway';" +
            "src: url(\"file:///android_asset/fonts/Raleway-ExtraLight.ttf\")}body {font-family: 'Raleway';font-size: medium;text-align: justify;}</style></head><body>";
    String pas = "</body></html>";
    static String TBHVurl= Public_Url.ThreeBedroomHimayalaView;
    TextView night,week,month;
    TextView title;
    Button mBookbtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threebedroomhimalayaview_layout);
        mThreebedhimalayaview=(ViewPager)findViewById(R.id.threebedroomhimalayaview_rooms);
        adapter=new ThreeBedroomHimalayaView_adapter(this);
        mThreebedhimalayaview.setAdapter(adapter);

        mRight=(ImageButton)findViewById(R.id.right_nav);
        mLeft=(ImageButton)findViewById(R.id.left_nav);
        mFirst=(WebView) findViewById(R.id.threeBedroom_paragraph1);
        mSecond=(WebView) findViewById(R.id.threeBedroom_paragraph2);
        night=(TextView)findViewById(R.id.night_tbhv);
        week=(TextView)findViewById(R.id.week_tbhv);
        month=(TextView)findViewById(R.id.month_tbhv);
        mBookbtn=(Button)findViewById(R.id.tbhv_book);
        mBookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ThreeBedRoomHimayalaView.this,Booking.class);
                i.putExtra("book","3_bedroom_himalaya_view");
                i.putExtra("unit_name","3 BEDROOM HIMALAYA VIEW");
                startActivity(i);
            }
        });
        SharedPreferences preferences=getSharedPreferences("price", Context.MODE_PRIVATE);
        night.setText(preferences.getString("tbhv_night",""));
        week.setText(preferences.getString("tbhv_week",""));
        month.setText(preferences.getString("tbhv_month",""));

        title=(TextView)findViewById(R.id.tbhv_title);


        Typeface myTypeface= Typeface.createFromAsset(getAssets(), "fonts/Raleway-ExtraLight.ttf");
        title.setTypeface(myTypeface);
        getApi();



        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mThreebedhimalayaview.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mThreebedhimalayaview.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mThreebedhimalayaview.setCurrentItem(tab);
                }
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mThreebedhimalayaview.getCurrentItem();
                tab++;
                mThreebedhimalayaview.setCurrentItem(tab);

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
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,TBHVurl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject p1=response.getJSONObject("field_subheader_body");
                            JSONArray array1=p1.getJSONArray("und");
                            for (int i=0;i<array1.length();i++)
                            {

                                JSONObject paragraph0=array1.getJSONObject(i);
                                subHeader_body=paragraph0.getString("value");
                            }

                            JSONObject p2=response.getJSONObject("body");
                            JSONArray array=p2.getJSONArray("und");
                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject paragraph=array.getJSONObject(i);
                                body=paragraph.getString("value");


                            }

                            SharedPreferences sharedPreferences=getSharedPreferences("tbhv", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("subheader",subHeader_body);
                            editor.putString("body",body);
                            editor.commit();

                            String myHtmlString1 = pish + subHeader_body + pas;
                            String myHtmlString = pish + body + pas;

//                            mFirst.loadData(mSubheader,"text/html","UTF-8");
//                            mSecond.loadData(mBody, "text/html", "UTF-8");
                            mFirst.loadDataWithBaseURL(null, myHtmlString1, "text/html", "UTF-8", null);
                            mSecond.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        SharedPreferences sharedPreferences=getSharedPreferences("tbhv", Context.MODE_PRIVATE);
                        subHeader_body=sharedPreferences.getString("subheader","");
                        body=sharedPreferences.getString("body","");

                        String myHtmlString1 = pish + subHeader_body + pas;
                        String myHtmlString = pish + body + pas;

                        mFirst.loadDataWithBaseURL(null, myHtmlString1, "text/html", "UTF-8", null);
                        mSecond.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);

                    }
                });


        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

    }
}
