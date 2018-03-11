package edu.epam.orlannrestclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import edu.epam.domain.Data;
import org.apache.log4j.Logger;

public class RequestSender {
    private static final Logger LOGGER = Logger.getLogger(RequestSender.class);

    public String doGet(String url) {
        LOGGER.info(String.format("Request: %s, type: %s", url, "GET"));
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            LOGGER.error(String.format("Failed : HTTP error code : %d", response.getStatus()));
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String output = response.getEntity(String.class);

        LOGGER.info("Output from Server .... \n");
        LOGGER.info(output);
        return output;
    }

    public String doPost(String url, Data requestBody) {
        LOGGER.info(String.format("Request: %s, type: %s", url, "POST"));
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(clientConfig);
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.accept("application/json").type("application/json")
                .post(ClientResponse.class, requestBody);
        if (response.getStatus() != 200) {
            LOGGER.error(String.format("Failed : HTTP error code : %d", response.getStatus()));
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        String output = response.getEntity(String.class);
        LOGGER.info("Output from Server .... \n");
        LOGGER.info(output);
        return output;
    }

    public Data setRequestBody(String a, String b, String operation) {
        Data data = new Data();
        data.setA(a);
        data.setB(b);
        data.setOperation(operation);
        return data;
    }
}
