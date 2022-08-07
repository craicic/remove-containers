package org.devnotfound.testcontainerscrontask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class CLI {
    private static final Logger logger = LoggerFactory.getLogger(CronTask.class);

    private final ProcessBuilder pb;

    public CLI() {
        this.pb = new ProcessBuilder();
    }

    List<String> getContainersId() throws IOException, InterruptedException {
        pb.command("docker", "container", "ls", "--quiet");
        pb.redirectErrorStream(true);
        Process p = pb.start();

        String ids = new String(p.getInputStream().readAllBytes());
        p.waitFor();

        if (ids.isEmpty()) {
            throw new RuntimeException("Id array is empty : No container are currently executed by Docker");

        }

        return List.of(ids.trim().split("\\n"));
    }

    String inspect(String id) throws IOException, InterruptedException {
        pb.command("docker", "inspect", "--format={{json .}}", id);

        pb.redirectErrorStream(true);
        Process p = pb.start();

        String json = new String(p.getInputStream().readAllBytes());

        p.waitFor();

        return json;
    }

    void kill(String id) throws IOException, InterruptedException {
        pb.command("docker", "rm", "-f", id);

        pb.redirectErrorStream(true);

        Process p = pb.start();
        String in = new String(p.getInputStream().readAllBytes());
        p.waitFor();

        if (in.isEmpty()) {
            logger.info("Docker successfully removed container of id: " + id);
        } else {
            logger.info(in);
        }
    }
}
