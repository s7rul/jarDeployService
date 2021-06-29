package eu.arrowhead.client.skeleton.provider.jarFileDeployer;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JarDeploymentHandler {
    private String jarFilesDirectory;
    private Integer noDeployments;
    private List<JarRunner> deployments;

    public JarDeploymentHandler(String jarFilesDirectory) {
        this.noDeployments = 0;
        this.jarFilesDirectory = jarFilesDirectory;
        this.deployments = new LinkedList<>();

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

    public void deploy(MultipartFile jarFile) {
    }
}