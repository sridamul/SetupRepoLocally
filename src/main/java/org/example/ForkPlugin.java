package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ForkPlugin {
    public String forkPlugin(String pluginName) {
        String url = "https://api.github.com/repos/jenkinsci/" + pluginName + "/forks";
        String token = "<Token>";
        String data = "{\"name\":\"" + pluginName + "\",\"default_branch_only\":true}";

        data = data.replace("\"", "\\\"");

        // Build the curl command
        String[] command = {"curl", "-L", "-X", "POST",
                "-H", "Accept: application/vnd.github.v3+json",
                "-H", "Authorization: Bearer " + token,
                "-H", "Content-Type: application/json",
                "-d", data, url};

        StringBuilder output = new StringBuilder();
        try {
            // Create process builder
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect error stream to output
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();
            output.append("\nExited with error code: ").append(exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
