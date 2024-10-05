package org.example.io;

import org.example.client.BpmModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class BpmFileSaver {
    public void saveToBpmFile(BpmModel model, Path targetPath) {
        InputStream inputStream = new ByteArrayInputStream(model.bpmn20Xml().getBytes(StandardCharsets.UTF_8));;
        try {
            saveInputStreamToBpmFile(inputStream, targetPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveInputStreamToBpmFile(InputStream inputStream, Path targetPath) throws IOException {
        Files.createDirectories(targetPath.getParent());
        Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
    }
}
