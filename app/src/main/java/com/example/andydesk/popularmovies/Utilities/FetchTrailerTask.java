package com.example.andydesk.popularmovies.Utilities;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.andydesk.popularmovies.BuildConfig;
import com.example.andydesk.popularmovies.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by owner on 28/02/2017.
 */

public class FetchTrailerTask extends AsyncTask<String, Void, String> {

    String youtubeTrailerUrl;
    String trailerUrl;
    Uri urlUri;
    HttpURLConnection connection = null;
    BufferedReader bufferedReader = null;
    String rawJsonString = null;
    InputStream inputStream;
    StringBuffer buffer;
    private Context context = null;
    //String youtubeBaseUrl = "https://www.youtube.com/watch?v=";

    public FetchTrailerTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        trailerUrl = params[0];

        //Build url uri that is used to create the url (1)
        urlUri = Uri.parse(trailerUrl).buildUpon()
                .appendQueryParameter("api_key", BuildConfig.OPEN_MOVIE_API_KEY)
                .build();

        //Start connection by creating URL object using the uri (2)
        try {
            URL url  = new URL(urlUri.toString());
            connection = (HttpURLConnection) url.openConnection();
            // Sets the operation that you would like to do with the connection
            connection.setRequestMethod("GET");
            connection.connect();

            // Obtain the input stream (3)
            inputStream = connection.getInputStream();
            buffer = new StringBuffer();

            //Handles the case in which the connection is null (4)
            if(inputStream == null) {
                rawJsonString = null;
            }

            // Read the input stream into a buffer (5)
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                buffer.append(line += "\n");
            }

            rawJsonString = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return rawJsonString;
    }

    /**
     * Obtains the youtube link of the trailer for current movie
     * @param rawJsonString
     * @return youtube trailer link
     */
    public String getTrailerUrl(String rawJsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(rawJsonString);
        JSONArray trailerArray = jsonObject.getJSONArray("results");
        String youtubeKey = trailerArray.getString(0);
        youtubeTrailerUrl =  + youtubeKey;
        return youtubeTrailerUrl;
    }
}
