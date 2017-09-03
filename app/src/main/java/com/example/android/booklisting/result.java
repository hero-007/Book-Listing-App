package com.example.android.booklisting;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class result extends AppCompatActivity {
    ListView bookItemListView;
    String Book_Title;
    ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Book_Title = getIntent().getStringExtra("book_Name");
        BookAsyncTask bookAsyncTask = new BookAsyncTask();
        bookAsyncTask.execute();


    }

    private class BookAsyncTask extends AsyncTask<String,Void,ArrayList<Book>>{
        @Override
        protected ArrayList<Book> doInBackground(String... params) {
            String returnedString = QueryUtils.fetchBookList("https://www.googleapis.com/books/v1/volumes?q="+Book_Title);
            books = QueryUtils.extractBooks(returnedString);
            return books;
        }

        @Override
        protected void onPostExecute(final ArrayList<Book> books) {
            bookItemListView = (ListView)findViewById(R.id.activity_result);
            BookAdapter adapter = new BookAdapter(result.this,books);
            bookItemListView.setAdapter(adapter);
        }
    }
}
