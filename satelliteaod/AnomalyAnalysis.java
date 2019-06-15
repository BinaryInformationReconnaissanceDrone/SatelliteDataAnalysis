/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author eqiu
 */
public class AnomalyAnalysis {

    static ArrayList<DataFile> list;
    static double[][] collection;
    static ArrayList<Double> retArr = new ArrayList<>();
    static double[] average = new double[12];

    public static void init(ArrayList<DataFile> d) {
        list = d;
        System.out.println("LIST LENGTH: " + list.size());
    }

    public static void compileAnomaly() {
        collection = new double[40][12];
        int counter = 0;
        int[] divCount = new int[12];
        for (DataFile d : list)

            if (d.getYear() > 1979 && d.getYear() < 2020) {
                collection[d.getYear() - 1980][counter % 12] = Double.parseDouble(d.toString());
//                System.out.println(d.getYear() + " " + counter);
                counter++;
            }

        for (double[] d : collection)
            for (int i = 0; i < 12; i++) {
                average[i] += d[i];
                if (d[i] != 0)
                    divCount[i]++;
            }

        for (int i = 0; i < 12; i++) {
            average[i] /= divCount[i];
            System.out.println("MONTH" + i + " " + divCount[i]);
        }

        for (double[] d : collection)
            for (int i = 0; i < 12; i++)
//                System.out.println(d[i] + " " + average[i] + " " + (d[i] - average[i]));
                retArr.add(d[i] - average[i]);
    }

    public static ArrayList<Double> getAnomaly() {
        return retArr;
    }

    public static double[] getAverage() {
        return average;
    }

    public static void reset() {
        retArr = new ArrayList<>();
        average = new double[12];
    }

}
