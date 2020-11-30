package com.teamten.sizzle.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.teamten.sizzle.model.Tag;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    String[] namesToFilter = {"recipe", "plate", "dish", "meat", "meal", "white", "black", "red", "yellow",
            "table", "food", "sauce", "topped", "hot", "sitting", "side", "filled", "covered", "fast food",
            "indoor"};
    String jsonString = "Did not get anything";
    //JSONObject json = null;
    JsonObject json = null;
    private String subscriptionKey = "c239da7f647f418288b7cd97cf05656c";
    private String endpoint = "https://sizzlecomputervision.cognitiveservices.azure.com/";
    //private String subscriptionKey = System.getenv("COMPUTER_VISION_SUBSCRIPTION_KEY"); //TODO: put our key
    //private String endpoint = System.getenv("COMPUTER_VISION_ENDPOINT");// TODO: get that

    private final String uriBase = endpoint + "vision/v3.1/tag";
    private final String imageToAnalyze =
            "https://upload.wikimedia.org/wikipedia/commons/" +
                    "1/12/Broadway_and_Times_Square_by_night.jpg"; //TODO: Get that image

    //public String analyse(final MultipartFile image) {
    public List<Tag> analyse(final File image) {

        // **********************************************
        // *** Update or verify the following values. ***
        // **********************************************

        // Add your Computer Vision subscription key and endpoint to your environment variables.
        // After setting, close and then re-open your command shell or project for the changes to take effect.

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            URIBuilder builder = new URIBuilder(uriBase);

            // Request parameters. All of them are optional.
            builder.setParameter("language", "en");

            // Prepare the URI for the REST API method.
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            //File tmpFile = new File("image.tmp");
            // image.transferTo(tmpFile);

            //request.setEntity(new FileEntity(tmpFile));
            request.setEntity(new FileEntity(image));
//            HttpEntity imageToProcess = MultipartEntityBuilder.create().addPart("")
//            StringEntity requestEntity =
//                    MultipartEntityBuilder.create().addPart();
//            request.setEntity(requestEntity);

            // Call the REST API method and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                // Format and display the JSON response.
                jsonString = EntityUtils.toString(entity);
                json = new JsonObject();
                json = new JsonParser().parse(jsonString).getAsJsonObject();
                //json = new JSONObject(jsonString);
//                System.out.println("REST Response:\n");
//                System.out.println(json.toString(2));
                return extractResult(json);
            }
        } catch (Exception e) {
            // Display error message.
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Tag> extractResult(JsonObject json) {
        Gson gson = new Gson();
        JsonArray array = json.getAsJsonArray("tags");
        Type type = new TypeToken<List<Tag>>() {
        }.getType();
        List<Tag> tags = gson.fromJson(array, type);
//        List<Tag> filteredTags = new ArrayList<>();
//        for (Tag tag: tags){
//            if(checkName(tag.getName()) && tag.getHint() == null && tag.getConfidence() )
//        }
        tags = tags.parallelStream().filter(tag -> (tag.getConfidence() > 0.80
                //&& tag.getHint() != null
                && checkName(tag.getName())
        )).collect(Collectors.toList());
        return tags;
    }

    private boolean checkName(String nameToCheck) {
        for (String name : namesToFilter) {
            if (nameToCheck.equals(name))
                return false;
        }
        return true;
    }
}
