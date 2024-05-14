package com.example.travel_app_srilanka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app_srilanka.Adapters.CategoryAdapter;
import com.example.travel_app_srilanka.Adapters.PopularAdapter;
import com.example.travel_app_srilanka.Domains.CategoryDomain;
import com.example.travel_app_srilanka.Domains.PopularDomain;
import com.example.travel_app_srilanka.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPopular,adapterCat;
    private RecyclerView recyclerViewPopular,recyclerViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerview();
    }


    private void initRecyclerview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Ruwanweli Maha Seya","Anuradhapura","The Ruwanwelisaya in Anuradhapura is a majestic stupa built in the 2nd century BCE by King Dutugemunu,"+
                "symbolizing profound Buddhist devotion and Sri Lanka's rich cultural heritage."  ,2,true,4.8,"pic1_1",true,1000));
        items.add(new PopularDomain("Unawatuna Beach","Galle"," Unawatuna is a coastal town in Galle district of Sri Lanka.,"+
                "    Unawatuna is a major tourist attraction in Sri Lanka and known for its beach and corals." ,1,false,5,"pic2_2",false,2500));
        items.add(new PopularDomain("Yala National Park","Hambantota, Monaragala ","Yala National Park (also known as Ruhuna National Park) is located in the south eastern region of Sri Lanka," +
                "and extends over two provinces of Hambantota district of southern province and Monaragala district in Uva province."  ,3,true,4.1,"pic3_3",true,30000));


        recyclerViewPopular = findViewById(R.id.view_pop);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPopular = new PopularAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);


        ArrayList<CategoryDomain> catsList = new ArrayList<>();
        catsList.add(new CategoryDomain("Beaches","cat1"));
        catsList.add(new CategoryDomain("Camps","cat2"));
        catsList.add(new CategoryDomain("Forest","cat3"));
        catsList.add(new CategoryDomain("Places of \nworship","cat4"));
        catsList.add(new CategoryDomain("Mountain","cat5"));


        recyclerViewCategory = findViewById(R.id.view_cat);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterCat = new CategoryAdapter(catsList);
        recyclerViewCategory.setAdapter(adapterCat);



    }
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ActivityProfile.class);
        startActivity(intent);
    }


}
