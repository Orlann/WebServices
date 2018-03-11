package edu.epam;

import org.testng.annotations.DataProvider;

public class ServiceTestData {
    private static String A = "10.5";
    private static String B = "20.35";


    @DataProvider(name = "dataForCalculation")
    public static Object[][] getDataForCalculator() {
        return new Object[][]{
                {A, B}
        };
    }
}
