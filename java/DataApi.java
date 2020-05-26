// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// Requires Apache's HttpClient 4.5
// http://mirrors.advancedhosters.com/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.12-bin.zip

// javac -classpath "jars/*:." DataApi.java
// java -classpath "jars/*:." DataApi

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.BasicResponseHandler;

public class DataApi {

    private static String sid = "mySID";

    public static void main(String[] args) throws Exception {

        String payload = "{\"sid\": \"" + sid + "\", \"metrics\": {\"2020.01.01\": {\"10\": 3.605422555523507e-05, \"11\": 35.7, \"12\": 0.04579250190473688}}}";

        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://sofia.conexo.vc/api");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
    }
}