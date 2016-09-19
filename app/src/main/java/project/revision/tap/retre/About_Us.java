package project.revision.tap.retre;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prakash on 8/23/2016.
 */
public class About_Us extends AppCompatActivity {
    WebView mFirst,mSecond;
    Toolbar toolbar;
    String body;
    String pish = "<html><head><style type=\"text/css\">@font-face {font-family: 'Raleway';" +
            "src: url(\"file:///android_asset/fonts/Raleway-ExtraLight.ttf\")}body {font-family: 'Raleway';font-size: medium;text-align: justify;}</style></head><body>";
    String pas = "</body></html>";
    static String url=Public_Url.Aboutus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_layout);
        toolbar=(Toolbar)findViewById(R.id.aboutus_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mFirst=(WebView)findViewById(R.id.aboutus_p1);
        mSecond=(WebView)findViewById(R.id.aboutus_p2);
        getApi();
    }

    void getApi()
    {

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject bodyobj=response.getJSONObject("body");
                            JSONArray undarray=bodyobj.getJSONArray("und");
                            for (int i = 0; i < undarray.length(); i++) {
                                JSONObject paragraph = undarray.getJSONObject(i);
                                body = paragraph.getString("value");
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        SharedPreferences sharedPreferences=getSharedPreferences("aboutus", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();


                        editor.putString("body",body);
                        editor.commit();

                        String myHtmlString = pish + body + pas;



                        mFirst.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        SharedPreferences sharedPreferences=getSharedPreferences("aboutus",Context.MODE_PRIVATE);
                        body=sharedPreferences.getString("body","");
                        String myHtmlString = pish + body + pas;



                        mFirst.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);


                    }
                });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
