package org.example.utils;

import java.time.Duration;

public class GlobalConstants {
    private GlobalConstants() {

    }

    public static final String BASE_BPM_URL = "https://n35ro2ic4d.execute-api.eu-central-1.amazonaws.com/prod/engine-rest/process-definition/key/invoice/xml";
    public static final String BPM_FILE_LOCATION = "src/main/resources/bpm/model.bpmn";
    public static final String NODE_ID_KEY = "id";
    public static final Duration DEFAULT_TIMEOUT_DELAY = Duration.ofSeconds(10);
}
