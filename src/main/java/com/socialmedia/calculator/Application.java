package com.socialmedia.calculator;

import com.socialmedia.calculator.reader.ReadFile;
import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.order.Order;
import com.socialmedia.calculator.processor.Processor;
import com.socialmedia.calculator.writer.Writer;

public class Application {

    public static void main(String[] args) {

        ReadFile readFile = new ReadFile();
        Order order = readFile.readFileToCreateOrder("Order.txt");

        Bundle bundle = new Bundle();
        bundle.createAndAddBundlesInMap();

        Processor processor = new Processor(order, bundle);
        processor.processing();

        Writer writer = new Writer(order, bundle);
        writer.writeResult("Output.txt");
    }
}
