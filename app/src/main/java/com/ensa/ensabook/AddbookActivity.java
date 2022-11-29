package com.ensa.ensabook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
    Button buttonUpload;
    DatabaseHelper databaseHelper;
    public static final int PICK_IMAGE = 100;

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
        buttonUpload=findViewById(R.id.buttonUpload);
        buttonSubmit.setOnClickListener(this);


        logoutIcon= findViewById(R.id.logout_icon);
        logoutIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddbookActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(
                        "content://media/internal/images/media"
                ));
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && resultCode==PICK_IMAGE){
            Uri uri = data.getData();
            String x = getPath(uri);
            if (databaseHelper.insertImage(uri)){
                Toast.makeText(getApplicationContext(), "Succeful", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String getPath(Uri uri){
        if (uri==null) return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,projection,null,null,null);
        if (cursor!=null){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
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

            Model model = new Model(null,bTitle,bAuthor,bCategory,bDescription,Double.valueOf(bPrice),false,R.drawable.cover1,false);
            databaseHelper.AddBook(model);
            Intent intent = new Intent(AddbookActivity.this,MainActivity.class);
//            intent.putExtra("modeladded",model);


            startActivity(intent);

        }

    }
}