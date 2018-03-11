package edu.epam.calculatorclient;

        import edu.epam.webservices.ICalculatorService;

        import javax.xml.namespace.QName;
        import javax.xml.ws.Service;
        import java.net.MalformedURLException;
        import java.net.URL;

public class CalculatorWebClient {
    static ICalculatorService calculatorService;

    public static void main(String[] args) throws MalformedURLException {

        try {
            URL url = new URL("http://localhost:1986/calculator?wsdl");
            QName qName = new QName("http://webservices.epam.edu/", "CalculatorServiceImplService");
            Service service = Service.create(url, qName);
            calculatorService = service.getPort(ICalculatorService.class);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println(calculatorService.calculation(5, 6, "add"));
        System.out.println(calculatorService.calculation(7, 4, "subtract"));
        System.out.println(calculatorService.calculation(3, 6, "multiple"));
        System.out.println(calculatorService.calculation(24, 4, "divide"));
        System.out.println(calculatorService.calculation(4, 100, "percent"));
    }
}
