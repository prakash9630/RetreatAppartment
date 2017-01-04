package project.revision.tap.retre.Restaurant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.revision.tap.retre.Adapter.Restaurant_adapter;
import project.revision.tap.retre.R;
import project.revision.tap.retre.Helper.RecyclerInformation;

/**
 * Created by prakash on 9/21/2016.
 */
public class Restaurant_activity extends Fragment {
    RecyclerView recyclerView;
    Restaurant_adapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.restaurant_layout,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.restaurant_id);
        adapter=new Restaurant_adapter(getContext(),getRestorents());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public List<RecyclerInformation> getRestorents()
    {
        List<RecyclerInformation> data=new ArrayList<>();
        int[] icon={R.drawable.gaia_restaurant,R.drawable.le_sherpa,R.drawable.roadhouse_cafe,R.drawable.fire,R.drawable.gardenofdream,R.drawable.bamboo};
        String[] name={"GAIA RESTAURANT & COFFEE SHOP","LE-SHERPA","ROADHOUSE CAFÉ","FIRE AND ICE PIZZERIA","GARDEN OF DREAMS","BAMBOO"};
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
