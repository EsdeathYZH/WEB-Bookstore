package com.esdeath.androidbookstore.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esdeath.androidbookstore.R;
import com.esdeath.androidbookstore.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private static final String TAG = BookAdapter.class.getSimpleName();

    private Context mContext;

    private List<Book> mList = new ArrayList<>();

    public BookAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<Book> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        // Img
        Picasso.with(mContext).load(mList.get(position).getImage_path()).into(holder.book_image);
        // Name
        holder.bookname.setText(mList.get(position).getBookname());
        // Price
        holder.bookprice.setText(String.format(mContext.getResources().getString(R.string.price_string),
                mList.get(position).getPrice()));
        //Sale amount
        holder.booksale.setText(String.format(mContext.getResources().getString(R.string.sale_string),
                mList.get(position).getSale_amount(),mList.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView book_image;
        TextView bookname, bookprice,booksale;

        public BookViewHolder(View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.book_image);
            bookname = itemView.findViewById(R.id.book_name);
            bookprice = itemView.findViewById(R.id.book_price);
            booksale = itemView.findViewById(R.id.book_sale);
        }
    }
}
