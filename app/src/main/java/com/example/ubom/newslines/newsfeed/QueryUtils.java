package com.example.ubom.newslines.newsfeed;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

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
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by ubom on 7/24/18.
 */
//public class QueryUtils {
//
//    public static final String LOG_TAG = MainActivity.class.getSimpleName();
//
//    static String createStringUrl() {
//        Uri.Builder builder = new Uri.Builder();
//        builder.scheme("https")
//                .encodedAuthority("content.guardianapis.com")
//                .appendPath("search")
//                .appendQueryParameter("order-by", "oldest")
//                .appendQueryParameter("show-references", "author")
//                .appendQueryParameter("show-tags", "contributor")
//                .appendQueryParameter("q", "Nollywood")
//                .appendQueryParameter("api-key", "896df9d7-548f-41eb-b4a0-475707743f15");
//        String url = builder.build().toString();
//        Log.d(LOG_TAG, "createStringUrl() returned: " + url);
//        return url;
//    }
//
//    public static List<News> fetchNewsData(String requestUrl) {
//        // Create URL object
//        URL url = createUrl(requestUrl);
//
//        // Perform HTTP request to the URL and receive a JSON response back
//        String jsonResponse = null;
//        try {
//            jsonResponse = makeHttpRequest(url);
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "Error closing input stream", e);
//        }
//
//        // Extract relevant fields from the JSON response and create an {@link Event} object
//        List<News> listOfNews = parseJson(jsonResponse);
//
//        // Return the {@link Event}
//        return listOfNews;
//    }
//
//    public static URL createUrl(String stringUrl) {
//        URL url = null;
//        try {
//            url = new URL(stringUrl);
//        } catch (MalformedURLException e) {
//            Log.e(LOG_TAG, "Problem building the URL ", e);
//        }
//        return url;
//    }
//
//
//    //Formats date
//    private static String formatDate(String rawDate) {
//            String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//            SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
//            try {
//                Date parsedJsonDate = jsonFormatter.parse(rawDate);
//                String finalDatePattern = "MMM d, yyy";
//                SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
//                return finalDateFormatter.format(parsedJsonDate);
//            } catch (ParseException e) {
//                Log.e("QueryUtils", "Error parsing JSON date: ", e);
//                return "";
//            }
//    }
//
//    //makes http request
//    static String makeHttpRequest(URL url) throws IOException {
//        String jsonResponse = "";
//
//        if (url == null){
//            return jsonResponse;
//        }
//        HttpURLConnection urlConnection = null;
//        InputStream inputStream = null;
//
//        try {
//            //Create connection
//            urlConnection = (HttpURLConnection) url.openConnection();
//            //Request type
//            urlConnection.setRequestMethod("GET");
//            //max read time
//            urlConnection.setReadTimeout(10000 /* milliseconds */);
//            //max connect time
//            urlConnection.setConnectTimeout(15000 /* milliseconds */);
//            //Start connection
//            urlConnection.connect();
//            //Check if network connection ok
//            if (urlConnection.getResponseCode() == 200){
//            //Read Json
//                inputStream = urlConnection.getInputStream();
//                jsonResponse = readFromStream(inputStream);
//            } else {
//                Log.e("mainActivity", "Error response code: " + urlConnection.getResponseCode());
//            }
//        } catch (IOException e) {
//            Log.e("Queryutils", "Error making HTTP request: ", e);
//        } finally {
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//        return jsonResponse;
//    }
//
//    //reads input stream
//    private static String readFromStream(InputStream inputStream) throws IOException {
//        StringBuilder output = new StringBuilder();
//        if (inputStream != null) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            String line = reader.readLine();
//            while (line != null) {
//                output.append(line);
//                line = reader.readLine();
//            }
//        }
//        return output.toString();
//    }
//
//    //Goes to arraylist with json results
//    static List<News> parseJson(String response) {
//        ArrayList<News> listOfNews = new ArrayList<>();
//        Log.e(LOG_TAG, "Response from url: " + response);
//
//        try {
//            JSONObject jsonResponse = new JSONObject(response);
//
//            JSONObject jsonResults = jsonResponse.getJSONObject("response");
//            JSONArray resultsArray = jsonResults.getJSONArray("results");
//
//            for (int i = 0; i < resultsArray.length(); i++) {
//                JSONObject oneResult = resultsArray.getJSONObject(i);
//                String webTitle = oneResult.getString("webTitle");
//                String url = oneResult.getString("webUrl");
//                String date = oneResult.getString("webPublicationDate");
//                date = formatDate(date);
//                String section = oneResult.getString("sectionName");
//                JSONArray tagsArray = oneResult.getJSONArray("tags");
//                String author = "";
//
//                if (tagsArray.length() == 0) {
//                    author = null;
//                } else {
//                    for (int j = 0; j < tagsArray.length(); j++) {
//                        JSONObject firstObject = tagsArray.getJSONObject(j);
//                        author += firstObject.getString("webTitle") + ". ";
//                    }
//                }
//                listOfNews.add(new News(webTitle, author, url, date, section));
//            }
//        } catch (JSONException e) {
//            Log.e("Queryutils", "Error parsing JSON response", e);
//        }
//        return listOfNews;
//    }
//
//}
/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    public static List<News> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        List<News> listOfNews = parseJson(jsonResponse);

        // Return the {@link Event}
        return listOfNews;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            //Create connection
            urlConnection = (HttpURLConnection) url.openConnection();
            //Request type
            urlConnection.setRequestMethod("GET");
            //max read time
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            //max connect time
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            //Start connection
            urlConnection.connect();
            //Check if network connection ok
            if (urlConnection.getResponseCode() == 200) {
                //Read Json
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("mainActivity", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("Queryutils", "Error making HTTP request: ", e);
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

    //reads input stream
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link News} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<News> parseJson(String newsJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<News> news = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(newsJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            JSONArray newsArray = baseJsonResponse.getJSONArray("features");

            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < newsArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentNews = newsArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject properties = currentNews.getJSONObject("properties");

                String section = currentNews.getString("sectionName");

                String title = currentNews.getString("webTitle");
                if (title.contains("|")) {
                    String[] arrayString = title.split("\\|");
                    title = arrayString[0].trim(); //
                }
                String date = currentNews.getString("webPublicationDate");
                date = formatDate(date);
                String url = currentNews.getString("webUrl");
                JSONArray tags = currentNews.getJSONArray("tags");

                String author;
                if (tags.length() != 0) {
                    JSONObject tagsObject = tags.getJSONObject(0);
                    author = tagsObject.getString("webTitle");
                } else author = "No author, this is just a news";
                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                News newEntry = new News(title, author, url, date, section);


                // Add the new {@link Earthquake} to the list of earthquakes.
                news.add(newEntry);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return news;
    }

    //Formats date
    private static String formatDate(String rawDate) {
        String jsonDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFormatter = new SimpleDateFormat(jsonDatePattern, Locale.US);
        try {
            Date parsedJsonDate = jsonFormatter.parse(rawDate);
            String finalDatePattern = "MMM d, yyy";
            SimpleDateFormat finalDateFormatter = new SimpleDateFormat(finalDatePattern, Locale.US);
            return finalDateFormatter.format(parsedJsonDate);
        } catch (ParseException e) {
            Log.e("QueryUtils", "Error parsing JSON date: ", e);
            return "";
        }
    }
}
