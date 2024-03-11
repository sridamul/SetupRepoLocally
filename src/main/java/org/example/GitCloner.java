package org.example;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.TextProgressMonitor;

import java.io.File;

public class GitCloner {

    public static void cloneRepository(String repoURL, String folderName) {
        try {
            // Create temporary directory
            File tempDir = new File(folderName);
            if (!tempDir.exists()) {
                tempDir.mkdir();
            }

            // Clone repository
            CloneCommand cloneCommand = Git.cloneRepository();
            cloneCommand.setURI(repoURL);
            cloneCommand.setDirectory(tempDir);
            cloneCommand.setProgressMonitor(new TextProgressMonitor());
            Git git = cloneCommand.call();

            System.out.println("Repository cloned successfully to: " + tempDir.getAbsolutePath());

            // Cleanup: Delete temporary directory after Executing the recipe
            // deleteTempFolder(tempDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Optional: Method to delete the temporary folder
    private static void deleteTempFolder(File tempDir) {
        if (tempDir.exists()) {
            File[] contents = tempDir.listFiles();
            if (contents != null) {
                for (File f : contents) {
                    f.delete();
                }
            }
            tempDir.delete();
            System.out.println("Temporary folder deleted.");
        }
    }
}
