package edu.epam.webservices;

import edu.epam.utils.BadParameterException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public interface ICalculatorService {
@WebMethod
    public double calculation(double number1, double number2, String operation) throws BadParameterException;
}
