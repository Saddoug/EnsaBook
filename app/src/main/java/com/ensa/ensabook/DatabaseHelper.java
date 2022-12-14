package com.ensa.ensabook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ensa.ensabook.Model;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private Context context;

    public static final String databaseName = "myDatabases.db";
    public static final int databaseVersion = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists books (id integer primary key autoincrement," +
                "title text not null, author text, category text, description text, price real, favorite integer, image integer,reserved integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists books");

        onCreate(sqLiteDatabase);
    }

    public void AddBook(Model data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title",data.getTitle());
        contentValues.put("author",data.getAuthor());
        contentValues.put("category",data.getCategory());
        contentValues.put("description",data.getDescription());
        contentValues.put("price",data.getPrice());
        contentValues.put("favorite",data.isFavorite()==true?1:0);
        contentValues.put("image",data.getBookPhoto());
        contentValues.put("reserved",data.isFavorite()==true?1:0);


        long table = sqLiteDatabase.insert("books", null, contentValues);
        Log.e(TAG,"insertion des donnes: " +table);

    }
    public void addToReservation(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Model book = getBook(id);
        contentValues.put("reserved",1);
        int updatedRow = sqLiteDatabase.update("books", contentValues, "id=?", new String[]{Integer.toString(id)});
        if (updatedRow==-1) Log.e(TAG,"Failed to update book with id "+id);
        else Log.e(TAG,"Book with id "+id+" Succefully updated");
    }

    public void removeFromReservation(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Model book = getBook(id);
        contentValues.put("reserved",0);
        int updatedRow = sqLiteDatabase.update("books", contentValues, "id=?", new String[]{Integer.toString(id)});
        if (updatedRow==-1) Log.e(TAG,"Failed to update book with id "+id);
        else Log.e(TAG,"Book with id "+id+" Succefully updated");
    }

    public ArrayList<Model> readAllReservations(){
        ArrayList<Model> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c;
        c = sqLiteDatabase.rawQuery("select * from books where reserved=1",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Model book = getModel(c);
            book.setReserved(true);
            result.add(book);
            c.moveToNext();
        }
        return result;
    }
    public void deleteAllReservation(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        List<Model> reservelist = readAllReservations();
        for (Model model:reservelist){
            removeFromReservation(model.getId());
        }

         sqLiteDatabase.execSQL("delete from books where reserved=1");

    }
    public void deleteBook(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int isDeleted = sqLiteDatabase.delete("books", "id=?", new String[]{Integer.toString(id)});
        if (isDeleted==-1) Log.e(TAG,"Failed to delete book with id "+id);
        else Log.e(TAG,"Book with id "+id+" Succefully deleted");
    }

    public ArrayList<Model> readAllBooks(){
        ArrayList<Model> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c;
        c = sqLiteDatabase.rawQuery("select * from books",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            result.add(getModel(c));
            c.moveToNext();
        }
        return result;
    }

    public ArrayList<Model> readFavoritesBooks(){
        ArrayList<Model> result = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c;
        c = sqLiteDatabase.rawQuery("select * from books where favorite = 1",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            result.add(getModel(c));
            c.moveToNext();
        }
        return result;
    }

    public Model getBook(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select * from books where id="+id,null);
        if (c!=null) c.moveToFirst();
        return getModel(c);
    }

    @NonNull
    private Model getModel(Cursor c) {
        Model book = new Model();
        book.setId(c.getInt(0));
        book.setTitle(c.getString(1));
        book.setAuthor(c.getString(2));
        book.setCategory(c.getString(3));
        book.setDescription(c.getString(4));
        book.setPrice(c.getInt(5));
        book.setFavorite((c.getInt(6)==0? false:true));
        book.setBookPhoto(c.getInt(7));

        return book;
    }


    public void addToFavorites(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Model book = getBook(id);
        contentValues.put("favorite",1);
        int updatedRow = sqLiteDatabase.update("books", contentValues, "id=?", new String[]{Integer.toString(id)});
        if (updatedRow==-1) Log.e(TAG,"Failed to update book with id "+id);
        else Log.e(TAG,"Book with id "+id+" Succefully updated");
    }
    public void removeFromFavorites(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Model book = getBook(id);
        contentValues.put("favorite",0);
        int updatedRow = sqLiteDatabase.update("books", contentValues, "id=?", new String[]{Integer.toString(id)});
        if (updatedRow==-1) Log.e(TAG,"Failed to update book with id "+id);
        else Log.e(TAG,"Book with id "+id+" Succefully updated");
    }

    public ArrayList<Model> getSearch(String search){
        String searchText = "'%"+search +"%'";
        ArrayList<Model> searchResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from books where title like "+searchText+" or author like "+searchText+" " +
                "or category like "+searchText+" or description like "+searchText+" ;";
        Cursor c = sqLiteDatabase.rawQuery(query,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            searchResult.add(getModel(c));
            c.moveToNext();
        }
        return searchResult;
    }

    public Boolean insertImage(Uri fileLoc){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try{
            FileInputStream fileInputStream = new FileInputStream(new File(String.valueOf(fileLoc)));
            byte[] imgbyte = new byte[fileInputStream.available()];
            ContentValues contentValues = new ContentValues();
            contentValues.put("title","titre");
            contentValues.put("author","auteur");
            contentValues.put("category","caegorie");
            contentValues.put("description","description");
            contentValues.put("price","prix");
            contentValues.put("favorite",0);
            contentValues.put("image",imgbyte);
            sqLiteDatabase.insert("books",null,contentValues);
            fileInputStream.close();
            Log.e(TAG,"insertion d'image : ");
            return true;
        } catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"insertion d'image failed");
            return false;
        }
    }


}


