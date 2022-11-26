package com.ensa.ensabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
   private ImageView leftIcon ;
   ImageView rightIcon ;
   TextView title ;
   List<Model> modelList;
   RecyclerView recyclerView;
   BookAdapter bookAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        leftIcon= findViewById(R.id.left_icon);
        rightIcon= findViewById(R.id.right_icon);
        title=findViewById(R.id.toolbar_title);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"you clicked left icon",Toast.LENGTH_SHORT).show();
            }
        });
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"you clicked right icon",Toast.LENGTH_SHORT).show();
            }
        });
        //creating an arraylist
        modelList=new ArrayList<>();
        modelList.add(new Model("title1","author1","category1","description1",55.0,false,R.drawable.cover1));
        modelList.add(new Model("title2","author2","category2","description2",55.0,false,R.drawable.cover2));
        modelList.add(new Model("title3","author3","category3","description3",55.0,false,R.drawable.cover3));
        modelList.add(new Model("title4","author4","category4","description4",55.0,false,R.drawable.cover4));
        modelList.add(new Model("title5","author5","category5","description5",55.0,false,R.drawable.cover5));
        //recyclerView
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        //adapter
        bookAdapter = new BookAdapter(this,modelList);
        recyclerView.setAdapter(bookAdapter);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menusearch,menu);
//        MenuItem menuItem= menu.findItem(R.id.action_search);
//        SearchView searchView=(SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Type here to search");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }

}