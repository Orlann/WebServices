package edu.epam.webservices;

import edu.epam.utils.BadParameterException;
import org.apache.commons.lang3.StringUtils;

import javax.jws.WebService;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebService(endpointInterface = "edu.epam.webservices.ICalculatorService")
public class CalculatorServiceImpl implements ICalculatorService {

    public double calculation(double number1, double number2, String operation) throws BadParameterException {
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
}
