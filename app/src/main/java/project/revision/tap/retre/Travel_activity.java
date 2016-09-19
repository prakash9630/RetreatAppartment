package project.revision.tap.retre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import project.revision.tap.retre.Adapter.RecyclerAdapter;

/**
 * Created by prakash on 9/16/2016.
 */
public class Travel_activity extends AppCompatActivity {
    RecyclerView recyclerView;
  LinearLayoutManager mLayoutmanager;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_layout);

        recyclerView=(RecyclerView)findViewById(R.id.travel_id);
        RecyclerAdapter adapter=new RecyclerAdapter(this,getInformation());
    toolbar=(Toolbar)findViewById(R.id.travel_toolbar);
        recyclerView.setAdapter(adapter);
        mLayoutmanager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutmanager);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }

    public List<RecyclerInformation> getInformation()
    {
        List<RecyclerInformation> data=new ArrayList<>();
        int[] icon={R.drawable.swyambhu1,R.drawable.bhairab,R.drawable.seto_gumba,R.drawable.ichangu_narayan_temple,R.drawable.durbar_square_dathmandu};
        String[] name={"Swyambhu nath temple","Kal Bhirab","Seto gumbh","Ichangu","Kathmandu Durbar square"};
        for(int i=0;i<icon.length && i<name.length;i++)
        {

            RecyclerInformation current=new RecyclerInformation();
            current.img=icon[i];
            current.name=name[i];
            data.add(current);
        }
        return data;

    }
}
