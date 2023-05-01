package com.example.fitpeak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReadRecyclerAdapter extends RecyclerView.Adapter<ReadRecyclerAdapter.ReadViewHolder> {

    private Context context;
    private ArrayList<Book> bookList;

    public ReadRecyclerAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public class ReadViewHolder extends RecyclerView.ViewHolder {
        TextView bookName, bookAuthor, bookPrice;

        public ReadViewHolder(View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.txtName);
            bookAuthor = itemView.findViewById(R.id.txtBookAuthor);
            bookPrice = itemView.findViewById(R.id.txtQuantity);
            //bookRating = itemView.findViewById(R.id.txtBookRating);
        }
    }

    @Override
    public ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_book_item, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadViewHolder holder, int position) {
        holder.bookName.setText(bookList.get(position).getBookName());
        holder.bookAuthor.setText(bookList.get(position).getBookAuthor());
        holder.bookPrice.setText(String.valueOf(bookList.get(position).getBookPrice()));
        //holder.bookRating.setText(bookList.get(position).getBookRating());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
