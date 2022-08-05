package org.devnotfound.testcontainerscrontask;


import org.springframework.stereotype.Component;

import java.io.File;
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
            Json.parse(new File("src/main/resources/container-" + id + ".json"));
        }


        return 0;
    }

}
