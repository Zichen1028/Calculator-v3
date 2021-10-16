package com.socialmedia.calculator;

import com.socialmedia.calculator.IOManagement.OrderReader;
import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.computation.Calculator;
import com.socialmedia.calculator.order.Order;
import com.socialmedia.calculator.processor.Processor;
import com.socialmedia.calculator.IOManagement.Writer;

public class Application {

    public static void main(String[] args) {

        OrderReader orderReader = new OrderReader();
        Order order = orderReader.readFileToCreateOrder("Order.txt");

        Bundle bundle = new Bundle();
        bundle.createAndAddBundlesInMap();

        Calculator calculator = new Calculator();

        Processor processor = new Processor(bundle);
        processor.processing(order, calculator);

        Writer writer = new Writer(order, bundle);
        writer.writeResult("Output.txt");
    }
}
