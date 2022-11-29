package com.ensa.ensabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ReservationActivity extends AppCompatActivity   {
    ImageView leftIcon ;
    RecyclerView recyclerView;
   List<Model> models;
   DatabaseHelper databaseHelper;
   Button clear;
    public ReservationAdapter mAdapter;
    public static final int LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        recyclerView= findViewById(R.id.listViewReservation);

        databaseHelper =new DatabaseHelper(getApplicationContext());
        //creation des donnees
//        models = databaseHelper.readAllReservations();
        //creation de l'adapter
//        ReservationAdapter reservationAdapter =new ReservationAdapter(this,models);
//        mylist.setAdapter(reservationAdapter);
        leftIcon= findViewById(R.id.left_icon);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        clear=findViewById(R.id.clearthedatabase);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteAllReservation();
                Toast.makeText(ReservationActivity.this,"ALL RESERVATIONS DELETED",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReservationActivity.this,ReservationActivity.class);
                startActivity(intent);

            }
        });
//        getLoaderManager().initLoader(LOADER, null, this);


  mAdapter = new ReservationAdapter(getApplicationContext(), databaseHelper.readAllReservations());
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
       recyclerView.setAdapter(mAdapter);





    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        String[] projection = {"title",
//                "author",
//                "category",
//                "price"
//        };
//
////        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
////                projection,
////                null,
////                null,
////                null);
//        return  new CursorLoader(this);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {mAdapter.swapCursor(cursor);
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        mAdapter.swapCursor(null);
//
//    }
}