package com.ensa.ensabook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddbookActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    ImageView logoutIcon ;
    EditText titel;
    EditText category;
    EditText author;
    EditText price;
    EditText description;
    Button buttonSubmit;
    Button buttonReset;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);
        databaseHelper=new DatabaseHelper(getApplicationContext());
        titel=findViewById(R.id.bookTitleInput);
        category=findViewById(R.id.bookCategoryInput);
        author=findViewById(R.id.bookAuthorInput);
        price=findViewById(R.id.bookPriceInput);
        description=findViewById(R.id.bookDescriptionInput);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonReset=findViewById(R.id.buttonRAZ);
        buttonSubmit.setOnClickListener(this);



        logoutIcon= findViewById(R.id.logout_icon);
        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddbookActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
    public void reset(View v){
        titel.getText().clear();
        category.getText().clear();
        author.getText().clear();
        price.getText().clear();
        description.getText().clear();


}

    @Override
    public void onClick(View view) {
        String bTitle,bCategory,bPrice,bAuthor,bDescription;
        bTitle=titel.getText().toString();
        bCategory=category.getText().toString();
        bPrice=price.getText().toString();
        bAuthor=author.getText().toString();
        bDescription=description.getText().toString();
        if(bAuthor.isEmpty() || bTitle.isEmpty()|| bCategory.isEmpty()|| bPrice.isEmpty()|| bDescription.isEmpty()){
            Toast.makeText(this,"you have to fill blanks",Toast.LENGTH_LONG).show();
        }else {

            Model model = new Model(null,bTitle,bAuthor,bCategory,bDescription,Double.valueOf(bPrice),false,R.drawable.cover1);
            databaseHelper.AddBook(model);
            Intent intent = new Intent(AddbookActivity.this,MainActivity.class);
//            intent.putExtra("modeladded",model);


            startActivity(intent);

        }

    }
}