package com.example.streange.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.streange.R;
import com.example.streange.domain.Book;
import com.example.streange.fragment.ChangeBookBlankFragment;
import com.example.streange.nodb.NoDb;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String BOOK_KEY = "Book";
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bookList = bookList;
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvAuthor, tvGenre;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_book_name);
            tvAuthor = itemView.findViewById(R.id.tv_author_name);
            tvGenre = itemView.findViewById(R.id.tv_genre_name);


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.book_item, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Book book = NoDb.BOOK_LIST.get(position);


        ((MyHolder) holder).tvName.setText(book.getName());
        ((MyHolder) holder).tvAuthor.setText(book.getAuthor().getName());
        ((MyHolder) holder).tvGenre.setText(book.getGenre().getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ChangeBookBlankFragment changeBookBlankFragment = new ChangeBookBlankFragment();

                Bundle bundle = new Bundle();
                bundle.putSerializable(BOOK_KEY, book);

                changeBookBlankFragment.setArguments(bundle);

                ((AppCompatActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_main, changeBookBlankFragment)
                        .commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return NoDb.BOOK_LIST.size();
    }
}
