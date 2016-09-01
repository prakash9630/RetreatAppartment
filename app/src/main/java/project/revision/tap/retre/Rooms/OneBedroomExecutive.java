package project.revision.tap.retre.Rooms;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import project.revision.tap.retre.Adapter.OneBedroomExecutive_adaptor;
import project.revision.tap.retre.Public_Url;
import project.revision.tap.retre.R;

/**
 * Created by prakash on 8/16/2016.
 */
public class OneBedroomExecutive extends AppCompatActivity {
    ViewPager mOneroomexecutive;
    OneBedroomExecutive_adaptor adaptor;
    ImageButton leftNav,rightNav;
    TextView mSecond;
    WebView mFirst;
static  String url= Public_Url.OneBedroomExecutive;
    String value,body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onebedroomexecutive_layout);
        mOneroomexecutive=(ViewPager)findViewById(R.id.oneroomexecutive_rooms);
        leftNav = (ImageButton)findViewById(R.id.left_nav);
        rightNav = (ImageButton)findViewById(R.id.right_nav);
        mFirst=(WebView) findViewById(R.id.onebedroomexecutive_paragraph1);
        mSecond=(TextView)findViewById(R.id.onebedroomexecutive_p2);




        Typeface myTypeface=Typeface.createFromAsset(getAssets(),"Raleway-ExtraLight.ttf");

        mSecond.setTypeface(myTypeface);
        adaptor=new OneBedroomExecutive_adaptor(this);
        mOneroomexecutive.setAdapter(adaptor);
        getParagraph();



        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mOneroomexecutive.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mOneroomexecutive.setCurrentItem(tab);
                }
                else if (tab == 0) {
                    mOneroomexecutive.setCurrentItem(tab);
                }

            }
        });

        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tab = mOneroomexecutive.getCurrentItem();
                tab++;
                mOneroomexecutive.setCurrentItem(tab);
            }
        });


    }



void getParagraph()
{
    JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {


                        JSONObject p2=response.getJSONObject("body");
                        JSONArray array=p2.getJSONArray("und");
                        for (int i=0;i<array.length();i++)
                        {
                            JSONObject paragraph=array.getJSONObject(i);
                            value=paragraph.getString("value");


                        }

                        body=response.getString("vid");
                        Toast.makeText(OneBedroomExecutive.this,value, Toast.LENGTH_SHORT).show();
//                        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/Raleway-ExtraLight.ttf\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>";
//                        String pas = "</body></html>";
//                        String myHtmlString = pish + value + pas;


                        mFirst.loadData(value, "text/html", "UTF-8");








                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }

    );
         RequestQueue queue= Volley.newRequestQueue(this);
            queue.add(request);



}


}
