package com.example.android.booklisting;

/**
 * Created by HP on 7/20/2017.
 */

public class Book {
    private String B_author, B_title, B_publisher, B_image, B_description, B_playStoreBuy;
    private long B_pageCount;
    private double B_retailPrice;
    private double B_ratings;

    Book(String A, String B, String C, String D, String E, String F, long G, double H, double I){
        B_author = A;
        B_title = B;
        B_publisher = C;
        B_image = D;
        B_description = E;
        B_playStoreBuy = F;
        B_pageCount = G;
        B_ratings = H;
        B_retailPrice = I;
    }

    public String getB_author(){
        return B_author;
    }

    public String getB_title(){
        return B_title;
    }

    public String getB_publisher(){
        return B_publisher;
    }

    public String getB_image(){
        return B_image;
    }

    public String getB_description(){
        return B_description;
    }

    public String getB_playStoreBuy(){
        return B_playStoreBuy;
    }

    public long getB_pageCount(){
        return B_pageCount;
    }

    public double getB_ratings(){
        return B_ratings;
    }

    public double getB_retailPrice(){
        return B_retailPrice;
    }
}
