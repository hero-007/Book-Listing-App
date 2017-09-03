package com.example.android.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static com.example.android.booklisting.MainActivity.LOG_TAG;

/**
 * Created by HP on 7/20/2017.
 */

public final class QueryUtils {

    private QueryUtils() {
    }

    public static ArrayList<Book> extractBooks(String string) {
        ArrayList<Book> newBooks = new ArrayList<Book>();

        try {
            JSONObject root = new JSONObject(string);
            JSONArray items = root.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {

                JSONObject arrayItem = items.getJSONObject(i);
                JSONObject volInfo = arrayItem.getJSONObject("volumeInfo");
                String title = volInfo.getString("title");
                JSONArray authors = volInfo.getJSONArray("authors");
                String author = authors.getString(0);
                String publisher = volInfo.getString("publisher");
                String description = volInfo.getString("description");
                long pageCount = volInfo.getLong("pageCount");
                JSONObject image = volInfo.getJSONObject("imageLinks");
                String thumbnail = image.getString("smallThumbnail");
                JSONObject saleInfo = arrayItem.getJSONObject("saleInfo");
                JSONObject retailPrice = saleInfo.getJSONObject("retailPrice");
                double amount = retailPrice.getDouble("amount");
                String buyLinks = saleInfo.getString("buyLink");
                double rating = volInfo.getDouble("averageRating");

                newBooks.add(new Book(author, title, publisher, thumbnail, description, buyLinks, pageCount, rating, amount));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error ocuured while parsing JSON Response", e);
        }

        return newBooks;
    }

    public static String fetchBookList(String urls) {
        URL url = createUrl(urls);

        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in makeHttPrequest", e);
        }

        return jsonResponse;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error in String Url which is formed", e);
        }

        return url;
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpsURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setReadTimeout(40000);
            urlConnection.setConnectTimeout(40000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response Code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem in retriving the json response", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    static private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
}

