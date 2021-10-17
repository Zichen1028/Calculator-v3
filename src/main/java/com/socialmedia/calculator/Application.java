package com.socialmedia.calculator;

import com.socialmedia.calculator.iomanagement.OrderReader;
import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.computation.SolutionAlgorithm;
import com.socialmedia.calculator.model.Order;
import com.socialmedia.calculator.calculatedresultprocessor.calculatedResultProcessor;
import com.socialmedia.calculator.iomanagement.OutputWriter;

public class Application {

    public static void main(String[] args) {

        OrderReader orderReader = new OrderReader();
        Order order = orderReader.readFileToCreateOrder("Order.txt");

        Bundle bundle = new Bundle();
        bundle.createAndAddBundlesInMap();

        SolutionAlgorithm solutionAlgorithm = new SolutionAlgorithm();

        calculatedResultProcessor calculatedResultProcessor = new calculatedResultProcessor(bundle);
        calculatedResultProcessor.transferResultToFrequencyMap(order, solutionAlgorithm);

        OutputWriter writer = new OutputWriter(order, bundle);
        writer.writeResult("Output.txt");
    }
}
