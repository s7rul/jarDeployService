package eu.arrowhead.client.skeleton.provider.jarFileDeployer;

import java.io.File;

public class JarRunner {
    File workingDir;
    File log;
    String jarName;
    Process proc;

    public JarRunner(String workingDir, String logPath, String jarName) {
        this.workingDir = new File(workingDir);
        this.log = new File(logPath);
        this.jarName = jarName;
        this.proc = null;
    }

    public void run() {
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", this.jarName);
        pb.directory(this.workingDir); // set working directory
        pb.redirectErrorStream(true);
        pb.redirectOutput(log); // set log file
        try {
            this.proc = pb.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stop() {
        if (this.proc != null) {
            this.proc.destroy();
        }
    }

    public void forceStop() {
        if (this.proc != null) {
            this.proc.destroyForcibly();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Testing to start jar file.");
        JarRunner t1 = new JarRunner("/home/s7rul/code/InterfaceLightweight/target", "/home/s7rul/tmplog.log", "InterfaceLightweight-1.0.jar");
        t1.run();
        Thread.sleep(4000);
        t1.stop();
    }
}
