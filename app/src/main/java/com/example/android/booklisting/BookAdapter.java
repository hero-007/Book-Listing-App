package com.example.android.booklisting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.android.booklisting.MainActivity.LOG_TAG;

/**
 * Created by HP on 7/20/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    BookAdapter(Activity ert, ArrayList<Book> books) {
        super(ert, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View bookItemView = convertView;
        if (bookItemView == null) {
            bookItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Book book_Current = getItem(position);

        String author = book_Current.getB_author();
        TextView tv1 = (TextView) bookItemView.findViewById(R.id.book_author);
        tv1.setText(author);

        String title = book_Current.getB_title();
        TextView tv2 = (TextView) bookItemView.findViewById(R.id.book_title);
        tv2.setText(title);

        double price = book_Current.getB_retailPrice();
        TextView tv3 = (TextView) bookItemView.findViewById(R.id.book_price);
        tv3.setText("" + price);

        double rating = book_Current.getB_ratings();
        RatingBar rb = (RatingBar) bookItemView.findViewById(R.id.book_ratings);
        rb.setRating((float)rating);

        String img = book_Current.getB_image();
        ImageView imageView = (ImageView) bookItemView.findViewById(R.id.book_image);
        try {
            URL url = new URL(img);
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        }catch(MalformedURLException e)
        {
            Log.e(LOG_TAG,"Image Url malformed",e);
        }
        catch(IOException e)
        {
            Log.e(LOG_TAG,"IO exceotion in url",e);
        }



        return bookItemView;
    }

}
