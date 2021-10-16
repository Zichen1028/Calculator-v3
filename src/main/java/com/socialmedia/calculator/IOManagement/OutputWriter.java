package com.socialmedia.calculator.IOManagement;

import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.order.Item;
import com.socialmedia.calculator.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Setter
@Getter
@AllArgsConstructor
public class OutputWriter {

    private Order order;
    private Bundle bundle;

    /**
     * Calculate the output for one item
     * Record the output in a string
     * @param item  an item in the order list
     * @return outputOfOneItem  A string contains the output for one item
     */
    private String calculateOneItemOutput(Item item) {
        String outputOfOneItem = "";
        outputOfOneItem += item.getQuantity() + " " + item.getFormat() + "\r\n";
        Map<Integer, Long> frequencyMap = item.getResultMap();
        Map<Integer, BigDecimal> bundles = bundle.getAllProductFormatAndBundleMap().get(item.getFormat());
        BigDecimal subTotal = new BigDecimal(0);
        for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
            BigDecimal priceOfOneBundle = new BigDecimal(entry.getValue()).multiply(bundles.get(entry.getKey()));
            subTotal = subTotal.add(priceOfOneBundle);
            outputOfOneItem += "  " + entry.getValue() + "*" + entry.getKey() + " $" + priceOfOneBundle + "\r\n";
        }
        outputOfOneItem += "subTotal: " + "$" + subTotal + "\r\n";
        return outputOfOneItem;
    }

    /**
     * calculate the output for all items
     * record the output content in a string for later writing
     * @return      a string to be written in the output file
     */
    private String processOutputContent() {
        List<Item> itemList = order.getOrderList();
        String outputContent = "";
        for (Item item : itemList) {
            String outputOfOneItem = calculateOneItemOutput(item);
            outputContent += outputOfOneItem;
        }
        return outputContent;
    }

    /**
     * Process the result of each item
     * Then process and write the result to the output file
     * @param outputFileName    The name of the output file
     */
    public void writeResult(String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String outputContent = processOutputContent();
            writer.write(outputContent);
        } catch (IOException ex) {
            Logger.getLogger("Writing file exception!");
        }
    }
}
