package com.ensa.ensabook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    List<Model> modelList;
    Context context;
    public BookAdapter(Context context,List<Model> modelList) {
        this.context=context;
        this.modelList=modelList;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        String categoryofbook = modelList.get(position).getCategory();
        String descriptionofbook = modelList.get(position).getDescription();
        String titleofbook = modelList.get(position).getTitle();
        int imageofbook = modelList.get(position).getBookPhoto();

        holder.bookcategory.setText(categoryofbook);
        holder.bookTitle.setText(titleofbook);
        holder.bookDescription.setText(descriptionofbook);
        holder.bookImage.setImageResource(imageofbook);


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView bookcategory;
        TextView bookTitle;
        TextView bookDescription;
        ImageView bookImage;

        public ViewHolder( View itemView) {
            super(itemView);
            bookcategory= itemView.findViewById(R.id.category);
            bookTitle=itemView.findViewById(R.id.titleBook);
            bookDescription=itemView.findViewById(R.id.description);
            bookImage=itemView.findViewById(R.id.bookImage);
        }
    }
}
