package project.revision.tap.retre.Main_LandigPage_class;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Toast;

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

import project.revision.tap.retre.R;

/**
 * Created by prakash on 9/12/2016.
 */
public class Facilities_activity extends AppCompatActivity{
    Toolbar toolbar;
    WebView webView;
    String url=Public_Url.Services;
    String value;
    String pish = "<html><head><style type=\"text/css\">@font-face {font-family: 'Raleway';" +
            "src: url(\"file:///android_asset/fonts/Raleway-ExtraLight.ttf\")}body {font-family: 'Raleway';font-size: medium;text-align: justify;}</style></head><body>";
    String pas = "</body></html>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amenities_layout);

        toolbar=(Toolbar)findViewById(R.id.amenities_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView=(WebView)findViewById(R.id.services_id);


getServices();
    }

    void getServices()
    {

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONObject body=response.getJSONObject("body");

                    JSONArray  undarray=body.getJSONArray("und");



                    for (int i=0;i<undarray.length();i++)
                    {

                        JSONObject services=undarray.getJSONObject(i);
                        value=services.getString("value");






                    }
                    SharedPreferences sharedPreferences=getSharedPreferences("services", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("vslue",value);
                    editor.commit();
                    String myHtmlString1 = pish + value + pas;

                    webView.loadDataWithBaseURL(null, myHtmlString1, "text/html", "UTF-8", null);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                SharedPreferences sharedPreferences=getSharedPreferences("services",Context.MODE_PRIVATE);
                value=sharedPreferences.getString("value","");

                String myHtmlString1 = pish + value+ pas;

                webView.loadDataWithBaseURL(null, myHtmlString1, "text/html", "UTF-8", null);



            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }



}
