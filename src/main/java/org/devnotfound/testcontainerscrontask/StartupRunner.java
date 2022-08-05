package org.devnotfound.testcontainerscrontask;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import static org.devnotfound.testcontainerscrontask.CronTask.start;

@Component
public class StartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        int status = start();
        logger.debug("application closed with status {}", status);
    }
}
