package org.devnotfound.testcontainerscrontask;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Json {

    private static final Logger logger = LoggerFactory.getLogger(Json.class);

    static boolean isFromTestContainers(String id) throws Exception {

        File file = new File(Constant.PATH + id + ".json");

        boolean isTestContainerNative = false;

        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createParser(file);
        JsonToken current;

        boolean nodeSkipped = false;
        if (jp.nextToken() != JsonToken.START_OBJECT) {
            logger.debug("root should be object: ignoring first token.");
            jp.nextToken();
        }


        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            // move from field name to field value
            jp.nextToken();

            if (fieldName == null)
                logger.warn(jp.getCurrentLocation().toString());
            if (fieldName != null && fieldName.equals("Config")) {
                logger.trace("Successfully processed field: " + fieldName);

                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    String subFieldName = jp.getCurrentName();
                    jp.nextToken();

                    if (subFieldName.equals("Labels")) {
                        logger.trace("Successfully processed sub field: " + subFieldName);
                        current = jp.nextToken();
                        while (current != JsonToken.END_OBJECT && !nodeSkipped) {
                            String targetFieldName = jp.getCurrentName();

                            if (targetFieldName.equals("org.testcontainers")) {
                                logger.trace("Successfully processed target field: " + targetFieldName + " " + jp.getCurrentToken());
                                JsonNode node = jp.readValueAsTree();
                                // And now we have random access to everything in the object
                                logger.trace("org.testcontainers: " + node.get("org.testcontainers").toPrettyString());
                                logger.trace("org.testcontainers: " + node.get("org.testcontainers.copied_files.hash").toPrettyString());
                                logger.trace("org.testcontainers: " + node.get("org.testcontainers.hash").toPrettyString());
                                nodeSkipped = true;
                                isTestContainerNative = true;
                            } else {
                                logger.trace("Unprocessed target property: " + targetFieldName);
                                jp.skipChildren();
                                current = jp.nextToken();
                            }
                        }

                    } else {
                        logger.trace("Unprocessed sub property: " + subFieldName);
                        jp.skipChildren();
                    }

                }
            } else {
                logger.trace("Unprocessed property: " + fieldName);
                jp.skipChildren();
            }
        }
        return isTestContainerNative;
    }
}