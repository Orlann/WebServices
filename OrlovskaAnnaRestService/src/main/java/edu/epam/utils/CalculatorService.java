package edu.epam.utils;


import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorService {

    public static double calculation(double number1, double number2, String operation) throws BadParameterException {
        if (StringUtils.isBlank(operation)) {
            throw new BadParameterException("Unsupported operation.");
        }
        double result = 0.0;

        switch (operation) {
            case "add": {
                result = number1 + number2;
                break;
            }
            case "subtract": {
                result = number1 - number2;
                break;
            }
            case "multiple": {
                result = number1 * number2;
                break;
            }
            case "divide": {
                result = number1 / number2;
                break;
            }
            case "percent": {
                result = number1 * number2 / 100;
                break;
            }
            default: {
                throw new BadParameterException("Unsupported operation.");
            }
        }
        return new BigDecimal(result).setScale(4, RoundingMode.HALF_DOWN).doubleValue();
    }

    public static String extractOperation(final String path, String firstParam, String secondParam) {
        Integer indexLastDigitOfFirstParam = path.indexOf(firstParam) + firstParam.length();
        Integer indexFirstDigitOfSecondParam = path.indexOf(secondParam);
        return path.substring(indexLastDigitOfFirstParam, indexFirstDigitOfSecondParam);
    }
}
