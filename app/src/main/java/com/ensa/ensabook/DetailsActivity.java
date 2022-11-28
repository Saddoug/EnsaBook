package com.ensa.ensabook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.security.SecureRandom;
import java.time.LocalDate;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{
    TextView price;
    TextView bookTitle;
    TextView bookAuthor;
    TextView description;
    ImageView bookImage;
    Model modelFromMainActivity;
    Button reserveButton;
    ToggleButton toggleButton;
    ImageView leftIcon ;
    private Toolbar toolbar;
    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        databaseHelper= new DatabaseHelper(getApplicationContext());
        toolbar=findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        toggleButton=findViewById(R.id.togglefavorite);
        price = findViewById(R.id.bookPrice);
        bookTitle = findViewById(R.id.detailTitle);
        bookAuthor = findViewById(R.id.authorDetail);
        description = findViewById(R.id.descriptiondetail);
        bookImage=findViewById(R.id.imageDetail);
        leftIcon= findViewById(R.id.left_icon);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        reserveButton=findViewById(R.id.RESERVE);
        reserveButton.setOnClickListener(this);
         modelFromMainActivity =getIntent().getParcelableExtra("model");
         if (modelFromMainActivity.isFavorite()){
             toggleButton.setChecked(true);

         }else {toggleButton.setChecked(false);}


//        String categoryofbook = getIntent().getStringExtra("category");
//        String authorofbook = getIntent().getStringExtra("author");
//        String descriptionofbook = getIntent().getStringExtra("description");
//        String titleofbook = getIntent().getStringExtra("title");
//        Double priceofbook = getIntent().getDoubleExtra("price",0.0);
//        int imageofbook = getIntent().getIntExtra("image",R.id.bookImage);
        String categoryofbook = modelFromMainActivity.getCategory();
        String authorofbook = modelFromMainActivity.getAuthor();
        String descriptionofbook = modelFromMainActivity.getDescription();
        String titleofbook = modelFromMainActivity.getTitle();
        Double priceofbook = modelFromMainActivity.getPrice();
        int imageofbook = modelFromMainActivity.getBookPhoto();
        price.setText(priceofbook.toString());
        bookTitle.setText(titleofbook);
        bookAuthor.setText(authorofbook);
        description.setText(descriptionofbook);
        bookImage.setImageResource(imageofbook);




    }
    public void makeFavorite(View view){
        Toast.makeText(DetailsActivity.this,"Added to favorite list",Toast.LENGTH_SHORT).show();
        afficheNotif("your favorite list is updated. see your favorite books ");
        if (modelFromMainActivity.isFavorite()){
            databaseHelper.removeFromFavorites(modelFromMainActivity.getId());

        }else{
            databaseHelper.addToFavorites(modelFromMainActivity.getId());
        }



    }
    public void afficheNotif(String msg){

        String CHANNEEL_ID = getString(R.string.channel_id);
        final int NOTIFICATION_ID =001;
        Intent notifIntent = new Intent(this,FavoriteActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,notifIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        //creation de notification
        NotificationCompat.Builder buildernotif = new NotificationCompat.Builder(this,CHANNEEL_ID);
        buildernotif.setContentTitle("Notification ");
        buildernotif.setContentText(msg);
        buildernotif.setSmallIcon(R.drawable.ic_baseline_logo_book_24);
        buildernotif.setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_launcher_background));
        buildernotif.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        buildernotif.setContentIntent(pi);
        buildernotif.setAutoCancel(true);//eliminer la notif apres clic
        //service de notofication
        NotificationManager notifManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //pour les api level >= 26
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence CHANNEL_NAME = getString(R.string.channel_name);
            int importants = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel myChannel = new NotificationChannel(CHANNEEL_ID,CHANNEL_NAME,importants);
            notifManager.createNotificationChannel(myChannel);

        }
        //affiche notif
        notifManager.notify(NOTIFICATION_ID,buildernotif.build());

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        String letterUpper="ABCDEFGHIJKLMNOPQRSTVUWYZ";
        String ref ="";

        SecureRandom random = new SecureRandom();
        for (int i=0;i<3;i++){
            ref+=letterUpper.charAt(random.nextInt(letterUpper.length()));

        }
        ref+=String.valueOf(System.currentTimeMillis());


        dialog.setTitle("RESERVATION");
        dialog.setMessage("Here's your Reservation Number :"+ref+" \n Deliver this number to the librarian to get your book ");
        dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(DetailsActivity.this,"your Reservation is done",Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();

    }
}