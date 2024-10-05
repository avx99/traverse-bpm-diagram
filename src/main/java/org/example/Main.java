package org.example;

import org.example.client.BpmHttpClient;
import org.example.converter.BpmConverter;
import org.example.io.BpmFileSaver;
import org.example.utils.GlobalConstants;
import org.example.validation.InputValidator;

import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InputValidator.validateInput(args);
        final String startNode = args[0];
        final String endNode = args[1];
        var client = new BpmHttpClient(
                GlobalConstants.BASE_BPM_URL,
                Map.of()
        );
        var fileSaver = new BpmFileSaver();
        var converter = new BpmConverter();
        var responseAsModel = client.getBpmModel();
        fileSaver.saveToBpmFile(responseAsModel, Path.of(GlobalConstants.BPM_FILE_LOCATION));
        var dataStructure = converter.convertBpmToGraph(startNode);
        System.out.println(dataStructure.findPath(startNode, endNode));
    }
}