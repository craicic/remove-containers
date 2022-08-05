package org.devnotfound.testcontainerscrontask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CLI {
    private static final Logger logger = LoggerFactory.getLogger(CronTask.class);

    static List<String> getContainersId() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("docker", "container", "ls", "--quiet");

        File containers = new File(Constant.PATH + "id");

        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.to(containers));

        Process p = pb.start();
        p.waitFor();

        String s = Files.readString(Path.of("src/main/resources/containers-id"));

        return List.of(s.trim().split("\\n"));

    }

    static void inspect(String id) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("docker", "inspect", "--format={{json .}}" ,  id);

        File file = new File(Constant.PATH + id + ".json");
        pb.redirectErrorStream(true);
        pb.redirectOutput(ProcessBuilder.Redirect.to(file));

        Process p = pb.start();
        p.waitFor();
    }

    public static void kill(String id) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("docker","rm", "-f", id);


        pb.redirectErrorStream(true);

        Process p= pb.start();
        String result = new String(p.getInputStream().readAllBytes());

        p.waitFor();

        logger.warn(result);
    }
}
