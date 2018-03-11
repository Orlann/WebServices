package edu.epam.webservices.endpoint;

import edu.epam.webservices.CalculatorServiceImpl;

import javax.xml.ws.Endpoint;

public class CalculatorServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:1986/calculator", new CalculatorServiceImpl());
    }
}
