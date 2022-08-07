package org.devnotfound.testcontainerscrontask;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Runner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Scheduled(fixedRate = 10000)
    public void run() throws Exception {
        CronTask ct = new CronTask();
        int status = ct.start();
        logger.info("Deleted file(s) number : " + ct.deletedFiles);
        logger.debug("Tasks ended with status {}", status);
    }
}
