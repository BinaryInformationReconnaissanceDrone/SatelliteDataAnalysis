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
        String[] place = {"Addis Ababa", "Casablanca", "Gaborone", "Nairobi"};
        for (int i = 0; i < 4; i++) {
            Reader.init(new File("/Users/eqiu/Desktop/GMU Summer Project/fwmerra2data/" + place[i]));
            Reader.read();

            collection = DataFile.getCollection();
            System.out.println(collection.size());
            sort();
//            for (DataFile d : collection)
//                System.out.println(d);

            PrintWriter rawWrite = new PrintWriter(new FileWriter("RAW" + place[i] + ".txt"));
//            PrintWriter seasonWrite = new PrintWriter(new FileWriter("SEASON" + place + ".txt"));
            PrintWriter monthWrite = new PrintWriter(new FileWriter("MONTH" + place[i] + ".txt"));
            PrintWriter anomalyWrite = new PrintWriter(new FileWriter("ANOMALY" + place[i] + ".txt"));
            for (DataFile d : collection)
                rawWrite.println(d);
//            System.out.println(collection.size());
            rawWrite.close();

//            SeasonalAnalysis.init(collection);
//            SeasonalAnalysis.compileSeason();
//
//            for (Double d : SeasonalAnalysis.getResults())
//                if (d != null)
//                    seasonWrite.println(d);
//
//            seasonWrite.close();
            AnomalyAnalysis.init(collection);
            AnomalyAnalysis.compileAnomaly();
            for (Double d : AnomalyAnalysis.getAnomaly())
                if (d != null)
                    anomalyWrite.println(d);
            anomalyWrite.close();

            for (double f : AnomalyAnalysis.getAverage())
                monthWrite.println(f);

            monthWrite.close();
            Reader.close();
            DataFile.reset();
            AnomalyAnalysis.reset();
        }
    }

}
