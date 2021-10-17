package com.socialmedia.calculator;

import com.socialmedia.calculator.IOManagement.OrderReader;
import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.computation.SolutionAlgorithm;
import com.socialmedia.calculator.model.Order;
import com.socialmedia.calculator.processor.Processor;
import com.socialmedia.calculator.IOManagement.OutputWriter;

public class Application {

    public static void main(String[] args) {

        OrderReader orderReader = new OrderReader();
        Order order = orderReader.readFileToCreateOrder("Order.txt");

        Bundle bundle = new Bundle();
        bundle.createAndAddBundlesInMap();

        SolutionAlgorithm solutionAlgorithm = new SolutionAlgorithm();

        Processor processor = new Processor(bundle);
        processor.transferResultToFrequencyMap(order, solutionAlgorithm);

        OutputWriter writer = new OutputWriter(order, bundle);
        writer.writeResult("Output.txt");
    }
}
