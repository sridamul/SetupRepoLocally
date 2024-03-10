package org.example;

import org.eclipse.jgit.api.Git;

public class Main {
    public static void main(String[] args) {

        String username = "sridamul";
        FetchPluginURL fetchPluginURL = new FetchPluginURL();
        // Plugin Name fetched from the Command Line argument
        String pluginName = "42crunch-security-audit";
        // Fetches the last part of the scm url from Update-Center.json
        String plugin = fetchPluginURL.extractLastPartOfUrl(pluginName);

        boolean forked = false;
        // Fork the plugin
        if(plugin != null) {
            ForkPlugin forkPlugin = new ForkPlugin();
            System.out.println("Forking...");
            String forkResult = forkPlugin.forkPlugin(plugin);
            // System.out.println(forkResult);
            System.out.println("Forked Successfully");
            forked = true;

        }
        else {
            System.out.println("Plugin Doesn't Exist. Please check the plugin name");
        }
        if(forked) {
            String repoUrl = "https://github.com/" + username + "/" + plugin + ".git";
            String tempFolder = "temp";

            GitCloner gitCloner = new GitCloner();
            System.out.println("Cloning...");
            gitCloner.cloneRepository(repoUrl, tempFolder);
        }
        else {
            System.out.println("Couldn't clone the plugin.");
        }

    }

}
