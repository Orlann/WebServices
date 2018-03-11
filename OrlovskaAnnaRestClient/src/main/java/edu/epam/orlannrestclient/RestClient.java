package edu.epam.orlannrestclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import edu.epam.domain.Data;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

public class RestClient {

    public static void main(String[] args) {
        RequestSender sender = new RequestSender();

        String outputFirst = sender.doGet("http://localhost:9090/calculator/18divide5");
        System.out.println(outputFirst);

        String outputSecond = sender.doGet("http://localhost:9090/calculator/3.20percent5.12");
        System.out.println(outputSecond);

        String outputThird = sender.doPost("http://localhost:9090/calculator/calculatePost",
                sender.setRequestBody("2.45", "4.35", "divide"));
        System.out.println(outputThird);

        String outputForth = sender.doPost("http://localhost:9090/calculator/calculatePost",
                sender.setRequestBody("145sdvsdv45", "15.5", "percend"));
        System.out.println(outputForth);
    }
}