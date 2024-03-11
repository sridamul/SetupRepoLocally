package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForkPlugin {
    public String forkPlugin(String pluginName, String token) {
        String url = "https://api.github.com/repos/jenkinsci/" + pluginName + "/forks";
        String data = "{\"name\":\"" + pluginName + "\",\"default_branch_only\":true}";

        StringBuilder output = new StringBuilder();

        try {
            // Create URL object
            URL apiUrl = new URL(url);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Set request method to POST
            connection.setRequestMethod("POST");

            // Set request headers
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Write data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get response code
            int responseCode = connection.getResponseCode();
            output.append("HTTP Response Code: ").append(responseCode).append("\n");

            // Read the response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            // Disconnect
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
