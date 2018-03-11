package edu.epam;

import com.google.gson.Gson;
import edu.epam.orlannrestclient.RequestSender;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculatorServiceTest {
    private static final String GET_REQUEST_BASE_URL = "http://localhost:9090/calculator/";
    private static final String ADD = "add";
    private static final String SUBTRACT = "subtract";
    private static final String MULTIPLE = "multiple";
    private static final String DIVIDE = "divide";
    private static final String PERCENT = "percent";
    private static final String POST_REQUEST_URL = "http://localhost:9090/calculator/calculatePost";

    private static final RequestSender SENDER = new RequestSender();
    private static final Gson GSON = new Gson();

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void addGetTest(String firstValue, String secondValue) {
        String url = GET_REQUEST_BASE_URL + firstValue + ADD + secondValue;
        String expectedResult = "30.85";
        RequestSender sender = new RequestSender();
        String jsonResult = sender.doGet(url);
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void subtractGetTest(String firstValue, String secondValue) {
        String url = GET_REQUEST_BASE_URL + firstValue + SUBTRACT + secondValue;
        String expectedResult = "-9.85";
        RequestSender sender = new RequestSender();
        String jsonResult = sender.doGet(url);
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void multipleGetTest(String firstValue, String secondValue) {
        String url = GET_REQUEST_BASE_URL + firstValue + MULTIPLE + secondValue;
        String expectedResult = "213.675";
        RequestSender sender = new RequestSender();
        String jsonResult = sender.doGet(url);
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void divideGetTest(String firstValue, String secondValue) {
        String url = GET_REQUEST_BASE_URL + firstValue + DIVIDE + secondValue;
        String expectedResult = "0.516";
        RequestSender sender = new RequestSender();
        String jsonResult = sender.doGet(url);
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void percentGetTest(String firstValue, String secondValue) {
        String url = GET_REQUEST_BASE_URL + firstValue + PERCENT + secondValue;
        String expectedResult = "2.1368";
        RequestSender sender = new RequestSender();
        String jsonResult = sender.doGet(url);
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void addPostTest(String firstValue, String secondValue) {

        String expectedResult = "30.85";
        String jsonResult = SENDER.doPost(POST_REQUEST_URL, SENDER.setRequestBody(firstValue, secondValue, ADD));
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void subtractPostTest(String firstValue, String secondValue) {

        String expectedResult = "-9.85";
        String jsonResult = SENDER.doPost(POST_REQUEST_URL, SENDER.setRequestBody(firstValue, secondValue, SUBTRACT));
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void multiplePostTest(String firstValue, String secondValue) {

        String expectedResult = "213.675";
        String jsonResult = SENDER.doPost(POST_REQUEST_URL, SENDER.setRequestBody(firstValue, secondValue, MULTIPLE));
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void dividePostTest(String firstValue, String secondValue) {

        String expectedResult = "0.516";
        String jsonResult = SENDER.doPost(POST_REQUEST_URL, SENDER.setRequestBody(firstValue, secondValue, DIVIDE));
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }

    @Test(dataProvider = "dataForCalculation",
            dataProviderClass = ServiceTestData.class)
    public void percentPostTest(String firstValue, String secondValue) {

        String expectedResult = "2.1368";
        String jsonResult = SENDER.doPost(POST_REQUEST_URL, SENDER.setRequestBody(firstValue, secondValue, PERCENT));
        ResultData data = GSON.fromJson(jsonResult, ResultData.class);

        assertEquals(data.getResult(), expectedResult);
    }
}
