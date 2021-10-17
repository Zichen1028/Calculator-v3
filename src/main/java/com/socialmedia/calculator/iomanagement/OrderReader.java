package com.socialmedia.calculator.iomanagement;

import com.socialmedia.calculator.model.Item;
import com.socialmedia.calculator.model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderReader {

    /**
     * Read order's information from Order.txt
     * @param fileName          the input file's name
     * @return  listOfLines     a list contains each line of the text file
     */
    public List<String> readLines(String fileName) {
        List<String> listOfLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                listOfLines.add(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            Logger.getLogger("Reading file exception!");
        }

        return listOfLines;
    }

    /**
     * Extract the content of the text file
     * Write the format and the quantity for each item
     * Push each item to a list of the Order
     * @param fileName      The name of input file
     * @return order        The object 'order'
     */
    public Order readFileToCreateOrder(String fileName) {

        Order order = new Order();

        List<String> listOfLines = readLines(fileName);

        for (String line : listOfLines) {
            List<String> splitLine;
            splitLine = List.of(line.split(" "));
            int quantity = Integer.parseInt(splitLine.get(0));
            String format = splitLine.get(1);
            Item item = new Item(quantity, format, null);
            order.addItem(item);
        }

        return order;
    }
}
