package org.devnotfound.removecontainers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Scheduled(fixedRate = 2, timeUnit = TimeUnit.HOURS)
    public void run() throws Exception {
        Task t = new Task();
        int status = t.start();
        logger.info("Deleted file(s) number : " + t.deletedFiles);
        logger.debug("Tasks ended with status {}", status);
    }
}
