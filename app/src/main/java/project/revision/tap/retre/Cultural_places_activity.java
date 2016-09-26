package project.revision.tap.retre;

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

import project.revision.tap.retre.Adapter.RecyclerAdapter;

/**
 * Created by prakash on 9/21/2016.
 */
public class Cultural_places_activity extends Fragment {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.cultural_layout,container,false);
recyclerView =(RecyclerView)view.findViewById(R.id.travel_id);

        adapter=new RecyclerAdapter(getContext(),getInformation());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }



    public List<RecyclerInformation> getInformation()
    {
        List<RecyclerInformation> data=new ArrayList<>();
        int[] icon={R.drawable.swyambhu1,R.drawable.bhairab,R.drawable.seto_gumba,R.drawable.ichangu_narayan_temple};
        String[] name={"SWAYAMBHUNATH-THE MONKEY TEMPLE","KATHMANDU DURBAR SQUARE:","SETO GUMBA (WHITE MONASTERY):","ICHANGU NARAYAN:"};
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
