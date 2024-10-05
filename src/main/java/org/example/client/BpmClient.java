package org.example.client;

import java.io.InputStream;

public interface BpmClient {
    String getBpmModelAsString();
    BpmModel getBpmModel();
}
