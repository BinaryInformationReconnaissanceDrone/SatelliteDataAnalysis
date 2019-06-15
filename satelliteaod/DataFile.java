/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

/**
 *
 * @author eqiu
 */
import java.io.IOException;
import java.util.ArrayList;
import ucar.ma2.Array;
import ucar.nc2.*;

public class DataFile {

    private String fileString, actualString;
    private int evaluatingNum, year, month;
    private static ArrayList<DataFile> collection = new ArrayList<>();
    private static String[] varName = {"lat", "lon", "AODANA"};

    public DataFile() {

    }

    public DataFile(NetcdfFile file, String name) {
        fileString = file.toString();
        evaluatingNum = Integer.parseInt(name.substring(27, 33));
        year = evaluatingNum / 100;
        month = evaluatingNum - year * 100;

        Variable v = file.findVariable(varName[2]);
        Array data = null;
        try {
            data = v.read();
        } catch (IOException ioe) {
            System.out.println("trying to read " + varName + ioe);
        }

        fileString = data.toString();
        add();
    }

    public ArrayList<Double> convertArray(String toBeProcessed) {
        ArrayList<Double> buffer = new ArrayList<>();
        while (toBeProcessed.contains(" ")) {
//          System.out.println(toBeProcessed + toBeProcessed.indexOf(" "));
            buffer.add(Double.parseDouble(toBeProcessed.substring(0, toBeProcessed.indexOf(" "))));
            toBeProcessed = toBeProcessed.substring(toBeProcessed.indexOf(" ") + 1);
        }
        return buffer;
    }

    public static ArrayList<DataFile> getCollection() {
        return collection;
    }

    public int getEval() {
        return evaluatingNum;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    private void add() {
        collection.add(this);
    }

    public static void reset() {
        collection = new ArrayList<>();
    }

    @Override
    public String toString() {
        ArrayList<Double> holder = convertArray(fileString);

        double sum = 0;
        for (double d : holder)
            sum += d;
        return "" + (sum / holder.size());
    }

}
