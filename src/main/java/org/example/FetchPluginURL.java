package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FetchPluginURL {
    private static String getSCMLink(String pluginName) {
        String filePath = "src/main/resources/Update-center.json";
        JSONParser parser = new JSONParser();
        try {
            // Parse JSON file
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;

            // Extract SCM link based on plugin name
            JSONObject plugins = (JSONObject) jsonObject.get("plugins");
            JSONObject pluginInfo = (JSONObject) plugins.get(pluginName);
            String url;
            if (pluginInfo != null) {
                return (String) pluginInfo.get("scm");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String extractLastPartOfUrl(String pluginName) {
        String url = getSCMLink(pluginName);
        if (url != null) {
            try {
                URL u = new URL(url);
                String path = u.getPath();
                String[] segments = path.split("/");
                return segments[segments.length - 1];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
