package parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewsAPIFetcher {

    /**
     * Fetches the top headlines for the United States from the NewsAPI.
     * Sends a GET request to the NewsAPI endpoint and returns an InputStream
     * containing the response data if the request is successful.
     *
     * @return an InputStream with the JSON response data from the NewsAPI
     * @throws IOException if an I/O error occurs during the connection or
     *                     if the response code from the server is not 200 (OK)
     */
    public InputStream fetchTopHeadlines(String source) throws IOException {
        String endpoint = source;
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new IOException("Failed to fetch data from NewsAPI: " + connection.getResponseCode());
        }
        return connection.getInputStream();
    }
}
