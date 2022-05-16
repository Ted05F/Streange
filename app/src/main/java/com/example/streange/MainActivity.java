package com.example.streange;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.streange.adapter.BookAdapter;
import com.example.streange.domain.Author;
import com.example.streange.domain.Book;
import com.example.streange.domain.Genre;
import com.example.streange.nodb.NoDb;
import com.example.streange.rest.LibraryApiVolley;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvBook;
    private BookAdapter bookAdapter;
    private LibraryApiVolley libraryApiVolley;

    private ItemTouchHelper.SimpleCallback simpleCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        libraryApiVolley = new LibraryApiVolley(this);
        libraryApiVolley.fillBook();

        rvBook = findViewById(R.id.rv_book);
        bookAdapter = new BookAdapter(this, NoDb.BOOK_LIST);
        rvBook.setAdapter(bookAdapter);

        simpleCallback = new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
        ){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            Book book = NoDb.BOOK_LIST.get(viewHolder.getAdapterPosition());

            if (direction == ItemTouchHelper.LEFT){

                libraryApiVolley.deleteBook(book.getId());

            }

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvBook);

    }

    public void updateAdapter(){

        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {

        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();

        int size = fragmentList.size();
        if (size > 0){

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragmentList.get(size - 1))
                    .commit();
        } else {
            finish();
        }

        super.onBackPressed();
    }
}