package org.devnotfound.testcontainerscrontask;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CronTask {

    static int start() throws Exception {
        List<String> ids = CLI.getContainersId();

        for (String id :
                ids) {
            if (id != null)
                CLI.inspect(id);

        }

        for (String id :
                ids) {
            if (Json.isFromTestContainers(id)) {
                CLI.kill(id);
            }
        }

        return 0;
    }

}
