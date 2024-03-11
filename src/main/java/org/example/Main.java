package org.example;

public class Main {
    public static void main(String[] args) {

        String username = System.getenv("USERNAME");
        // Plugin Name fetched from the Command Line argument
        String pluginName = "git";
        // Fetches the last part of the scm url from Update-Center.json
        String plugin = FetchPluginURL.extractLastPartOfUrl(pluginName);

        String token = System.getenv("TOKEN");

        boolean forked = false;
        // Fork the plugin
        if(plugin != null) {
            ForkPlugin forkPlugin = new ForkPlugin();
            System.out.println("Forking...");
            String forkResult = forkPlugin.forkPlugin(plugin, token);
             System.out.println(forkResult);
            System.out.println("Forked Successfully");
            forked = true;

        }
        else {
            System.out.println("Plugin Doesn't Exist. Please check the plugin name");
        }
        if(forked) {
            String repoUrl = "https://github.com/" + username + "/" + plugin + ".git";
            String tempFolder = "temp";

            System.out.println("Cloning...");
            GitCloner.cloneRepository(repoUrl, tempFolder);
        }
        else {
            System.out.println("Couldn't clone the plugin.");
        }

    }

}
