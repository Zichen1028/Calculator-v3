package com.socialmedia.calculator.bundle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
public class Bundle {

    private Map<String, Map<Integer, BigDecimal>> allProductFormatAndBundleMap;

    public Bundle() {
        this.allProductFormatAndBundleMap = new HashMap<>();
    }

    /**
     * Create the map of bundles for each item format
     * Then add all the bundle maps with the format name to another map
     */
    public void createAndAddBundlesInMap() {
        createAudioBundles();
        createImageBundles();
        createVideoBundles();
    }

    /**
     * Create bundleMap for format 'FLAC'
     */
    private void createAudioBundles() {
        Map<Integer, BigDecimal> bundleMap = new HashMap<>();
        bundleMap.put(3, BigDecimal.valueOf(427.50));
        bundleMap.put(6, BigDecimal.valueOf(810.0));
        bundleMap.put(9, BigDecimal.valueOf(1147.50));

        allProductFormatAndBundleMap.put("FLAC", bundleMap);
    }

    /**
     * Create bundleMap for format 'VID'
     */
    private void createVideoBundles() {
        Map<Integer, BigDecimal> bundleMap = new HashMap<>();
        bundleMap.put(3, BigDecimal.valueOf(570));
        bundleMap.put(5, BigDecimal.valueOf(900));
        bundleMap.put(9, BigDecimal.valueOf(1530));

        allProductFormatAndBundleMap.put("VID", bundleMap);
    }

    /**
     * Create bundleMap for format 'IMG'
     */
    private void createImageBundles() {
        Map<Integer, BigDecimal> bundleMap = new HashMap<>();
        bundleMap.put(5, BigDecimal.valueOf(450));
        bundleMap.put(10, BigDecimal.valueOf(800));

        allProductFormatAndBundleMap.put("IMG", bundleMap);
    }

    /**
     * Extract the number of the bundles and transfer them as a List
     * @param bundlesMap
     * @return
     */
    public List<Integer> getNumbersInOneBundle(Map<Integer, BigDecimal> bundlesMap) {
//        Set<Integer> keys = bundlesMap.keySet();
//        List<Integer> numbersInOneBundle = new ArrayList<>();
//
//        for (Integer key : keys) {
//            numbersInOneBundle.add(key);
//        }
//
//        return numbersInOneBundle;
        return new ArrayList<>(bundlesMap.keySet());
    }
}
