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
public class Writer {

    private Order order;
    private Bundle bundle;

    /**
     * Process the result of each item
     * Then process and write the result to the output file
     * @param outputFileName    The name of the output file
     */
    public void writeResult(String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            List<Item> itemList = order.getOrderList();
            for (Item item: itemList) {
                int quantity = item.getQuantity();
                String format = item.getFormat();
                writer.write(quantity + " " + format + "\n");
                Map<Integer, Long> frequencyMap = item.getResultMap();
                Map<Integer, BigDecimal> bundles = bundle.getAllProductFormatAndBundleMap().get(format);
                BigDecimal subTotal = new BigDecimal(0);
                for (Map.Entry<Integer, Long> entry: frequencyMap.entrySet()) {
                    BigDecimal priceOfOneBundle = new BigDecimal(entry.getValue()).multiply(bundles.get(entry.getKey()));
                    subTotal = subTotal.add(priceOfOneBundle);
                    writer.write("  " + entry.getValue() + "*" + entry.getKey() + " $" + priceOfOneBundle + "\n");
                }
                writer.write("subTotal: " + "$" + subTotal + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger("Writing file exception!");
        }
    }
}
