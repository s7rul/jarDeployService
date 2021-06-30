package eu.arrowhead.client.skeleton.provider.jarFileDeployer;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class JarDeploymentHandler {
    private String jarFilesDirectory;
    private Boolean isDeployed;
    private JarRunner deployment;

    public JarDeploymentHandler(String jarFilesDirectory) {
        this.isDeployed = false;
        this.jarFilesDirectory = jarFilesDirectory;

        File directory = new File(this.jarFilesDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void deploy(MultipartFile jarFile) {
        if (isDeployed) {
            return;
        }
        this.isDeployed = true;
        File f = new File(this.jarFilesDirectory + File.separator + "translator.jar");
        try {
            jarFile.transferTo(f);
            this.deployment = new JarRunner(this.jarFilesDirectory,
                    "/home/s7rul/tmp-log.log",
                    "translator.jar",
                    this);
            Thread thread = new Thread(this.deployment);
            thread.start();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    synchronized void stopped() {
        this.isDeployed = false;
    }
}