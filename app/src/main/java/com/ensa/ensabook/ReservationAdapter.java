package com.ensa.ensabook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{
    List<Model> modelList;
    Context context;

    private  String[] listBook;
    public ReservationAdapter(Context context,List<Model> modelList) {
        this.context=context;
        this.modelList=modelList;

    }



    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.reserveitem,parent,false);
        return new ReservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReservationAdapter.ViewHolder holder, int position) {
        String categoryofbook = modelList.get(position).getCategory();
        String Authorofbook = modelList.get(position).getAuthor();
        String titleofbook = modelList.get(position).getTitle();
        String priceofbook = String.valueOf(modelList.get(position).getPrice());

        holder.category.setText(categoryofbook);
        holder.bookTitle.setText(titleofbook);
        holder.bookAuthor.setText(Authorofbook);
        holder.bookprice.setText(priceofbook);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookprice;

        public ViewHolder( View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.reservationCategory);
            bookTitle=itemView.findViewById(R.id.reservationTitle);
            bookAuthor=itemView.findViewById(R.id.reservationAuthor);
            bookprice=itemView.findViewById(R.id.reservationPrice);

        }


    }
}

