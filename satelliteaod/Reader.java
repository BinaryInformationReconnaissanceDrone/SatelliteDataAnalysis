/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

import java.io.*;
import ucar.nc2.*;

/**
 *
 * @author eqiu
 */
public class Reader {

    static final File DIRECTORY = new File("/Users/eqiu/Desktop/GMU Summer Project/fwmerra2data/Addis Ababa");
    static String[] fileList = DIRECTORY.list();
    static NetcdfFile[] ncfileList;
    static String fileName;
    static int placeHolder;

    public static String[] getList() {
        return fileList;
    }

    public static NetcdfFile[] readMyFile() {
        try {
            System.out.println(fileList[0]);
            ncfileList = new NetcdfFile[fileList.length];

            for (int i = 0; i < fileList.length; i++) {
                fileName = DIRECTORY + "/" + fileList[i];
                ncfileList[i] = NetcdfFile.open(fileName);
            }
            //process( ncfile);
        } catch (IOException ioe) {
            System.out.println("trying to open " + fileName);
        }
        System.out.println(ncfileList.length);
        return ncfileList;
    }

    public static void closeMyFile() {
        for (int i = 0; i < fileList.length; i++)
            if (null != ncfileList[i])
                try {
                    ncfileList[i].close();
                } catch (IOException ioe) {
                    System.out.println("trying to close " + fileList[0] + ioe);
                }
    }
}
