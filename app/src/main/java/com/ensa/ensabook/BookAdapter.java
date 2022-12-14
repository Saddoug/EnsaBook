package com.ensa.ensabook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    List<Model> modelList;
    Context context;
        private  final RecyclerViewInterface recyclerViewInterface;
    private  String[] listBook;
    public BookAdapter(Context context,List<Model> modelList,RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.modelList=modelList;
        this.recyclerViewInterface=recyclerViewInterface;
    }
    public void  setFiltredList(List<Model> filtredList){
        this.modelList=filtredList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String categoryofbook = modelList.get(position).getCategory();
        String Authorofbook = modelList.get(position).getAuthor();
        String titleofbook = modelList.get(position).getTitle();
        int imageofbook = modelList.get(position).getBookPhoto();

        holder.category.setText(categoryofbook);
        holder.bookTitle.setText(titleofbook);
        holder.bookAuthor.setText(Authorofbook);
        holder.bookImage.setImageResource(imageofbook);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView bookTitle;
        TextView bookAuthor;
        ImageView bookImage;

        public ViewHolder( View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            bookTitle=itemView.findViewById(R.id.titleBook);
            bookAuthor=itemView.findViewById(R.id.author);
            bookImage=itemView.findViewById(R.id.bookImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface!= null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }


    }
}
