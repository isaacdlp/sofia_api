// Copyright (C) 2020 by Algonaut USA (http://algonaut.us).
// Please see the LICENSE file that should have been included as part of this package.
// Written by Isaac de la Pena <isaac@algonaut.us>

// Requires Apache's HttpClient 4.5
// http://mirrors.advancedhosters.com/apache//httpcomponents/httpclient/binary/httpcomponents-client-4.5.12-bin.zip

// javac -classpath "jars/*:." FileApi.java
// java -classpath "jars/*:." FileApi

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import java.io.File;

public class FileApi {

    private static String sid = "mySID";

    public static void main(String[] args) throws Exception {

        File file = new File("../Sofia_Metrics_Template.xlsx");

        MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();
        entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        entitybuilder.addBinaryBody("xls", file);
        HttpEntity entity = entitybuilder.build();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://sofia.conexo.vc/xls/" + sid);
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);

        System.out.println(response.getStatusLine().getStatusCode());
        String responseString = new BasicResponseHandler().handleResponse(response);
        System.out.println(responseString);
    }
}