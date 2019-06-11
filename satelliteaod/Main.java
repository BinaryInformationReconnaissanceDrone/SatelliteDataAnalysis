/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

import static com.sun.activation.registries.LogSupport.log;
import java.io.*;
import java.util.*;

/**
 *
 * @author Ethan
 */
public class Main {

    static ArrayList<DataFile> collection;

    public static void sort() {
        int i, j;

        for (i = 1; i < collection.size(); i++) {
            DataFile holder = collection.get(i);
            int holVal = holder.getEval();
            j = i;
            while ((j > 0) && (collection.get(j - 1).getEval() > holVal)) {
                collection.set(j, collection.get(j - 1));
                j--;
            }
            collection.set(j, holder);
        }

        System.out.println("sort complete");
    }

    public static void season() {

    }

    public static void main(String[] args) throws IOException {
        String place = "Addis Ababa";
        Reader.init(new File("/Users/eqiu/Desktop/GMU Summer Project/fwmerra2data/" + place));
        Reader.read();

        collection = DataFile.getCollection();
        System.out.println(collection.size());
        sort();
//        for (DataFile d : collection)
//            System.out.println(d);

        PrintWriter writer = new PrintWriter(new FileWriter(place + ".txt"));
        int counter = 0;
        for (DataFile d : collection) {
            writer.println(d);
            counter++;
        }
        System.out.println(collection.size());
        writer.close();
        Reader.close();
        SeasonalAnalysis.init(collection);
        SeasonalAnalysis.compileSeason();
        SeasonalAnalysis.getResults();

    }

}
