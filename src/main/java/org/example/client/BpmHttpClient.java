package org.example.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.utils.GlobalConstants;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class BpmHttpClient implements BpmClient {
    private final String url;
    private final Map<String, String> params;
    private final HttpClient client = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(GlobalConstants.DEFAULT_TIMEOUT_DELAY)
            .build();

    public BpmHttpClient(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }


    @Override
    public String getBpmModelAsString() {
        var request = buildRequest();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.err.println("error occurred while exchanging an io stream");
        }
        return null;
    }

    @Override
    public BpmModel getBpmModel() {
        var responseAsString = getBpmModelAsString();
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(responseAsString, BpmModel.class);
        } catch (JsonProcessingException e) {
            System.err.println("error occurred while converting the model");
        }
        return null;
    }

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .uri(URI.create(url + buildParams(params)))
                .GET()
                .build();
    }

    private String buildParams(Map<String, String> params) {
        var paramsAsString = params.entrySet().stream()
                .map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
                .collect(Collectors.joining("&"));
        if (paramsAsString.isEmpty()) {
            return "";
        }

        return "?" + paramsAsString;
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
