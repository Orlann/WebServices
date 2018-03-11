package edu.epam.orlannrestservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import edu.epam.domain.Data;
import org.apache.log4j.Logger;
import edu.epam.utils.BadParameterException;
import edu.epam.utils.CalculatorService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("/calculator")
public class Controller {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);
    private static String START_MESSAGE = "Start working with calculator!";
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public String getMessage() {
        LOGGER.info(START_MESSAGE);
        return START_MESSAGE;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{a}{operation: add|subtract|multiple|divide|percent}{b}")
    public Response calculate(@PathParam("a") String a, @PathParam("operation") String operation,
                            @PathParam("b") String b) {
        LOGGER.info(String.format("Request: %s, type: %s", uriInfo.getAbsolutePath(), "GET"));
        LOGGER.info(String.format("Invoked operation: %s , operands: %s, %s", operation, a, b));

        return generateResponse(a, b, operation);
    }


    @POST
    @Path("/calculatePost")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response calculatePost(Data data) {

        String a = data.getA();
        String b = data.getB();
        String operation = data.getOperation();

        LOGGER.info(String.format("Request: %s, type: %s", uriInfo.getAbsolutePath(), "POST"));
        LOGGER.info(String.format("Invoked operation: %s , operands: %s, %s", operation, a, b));

        return generateResponse(a, b, operation);
    }

    private Response generateResponse(String a, String b, String operation) {
        Gson gson = new GsonBuilder().create();
        JsonObject jsonResult = new JsonObject();

        try {
            Double aDouble = Double.parseDouble(a);
            Double bDouble = Double.parseDouble(b);
            jsonResult.addProperty("operand1", a);
            jsonResult.addProperty("operator", operation);
            jsonResult.addProperty("operand2", b);
            jsonResult.addProperty("result",
                    CalculatorService.calculation(aDouble, bDouble, operation));
            LOGGER.info(String.format("Result calculated successfully"));
        } catch (NumberFormatException e) {
            LOGGER.error("Illegal parameters format.");
            jsonResult.addProperty("error", "Illegal parameters format.");
        } catch (BadParameterException e) {
            LOGGER.error(e.getMessage());
            jsonResult.addProperty("error", e.getMessage());
        }
        return Response.status(200).entity(gson.toJson(jsonResult)).build();
    }
}
