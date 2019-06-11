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
public final class SeasonalAnalysis {

    static ArrayList<DataFile> list;
    static ArrayList<Double> result = new ArrayList<>();

    public static void init(ArrayList<DataFile> d) {
        list = d;
    }

    public static void compileSeason() {
        ArrayList<Double>[] holder = new ArrayList[40];//MIGHT NEED TO CHANGE THIS IF YEAR IS LATER THAN 2020

        for (int i = 0; i < 40; i++)
            holder[i] = new ArrayList<>();

        for (DataFile d : list) {
            System.out.println(d.getYear() - 1980 + "LOOK AT ME" + Double.parseDouble(d.toString()));
            holder[d.getYear() - 1980].add(Double.parseDouble(d.toString()));
        }

        for (ArrayList<Double> arr : holder) {

            Double[] average = new Double[4];
            try {
                average[0] = (arr.get(2) + arr.get(3) + arr.get(4)) / 3;
                average[1] = (arr.get(5) + arr.get(6) + arr.get(7)) / 3;
                average[2] = (arr.get(8) + arr.get(9) + arr.get(10)) / 3;
                average[3] = (arr.get(11) + arr.get(0) + arr.get(1)) / 3;
            } catch (IndexOutOfBoundsException e) {
                System.err.println("incomplete year");
            }
            List a = Arrays.asList(average);
            result.addAll(Arrays.asList(average));
        }

    }

    public static ArrayList<Double> getResults() {
        for (Double d : result)
            System.out.println(d);
        return result;
    }

}
