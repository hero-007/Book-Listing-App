package com.example.android.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();

    EditText editText;
    String bookEntered="quilting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.Enter_Search);

    }
    public void moveToNext(View view){
       bookEntered=editText.getText().toString();
        Intent i = new Intent(MainActivity.this,result.class);
        i.putExtra("book_Name",bookEntered);
        startActivity(i);
    }
}
