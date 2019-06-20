/**
 *This class reads from the list of files and returns it to main for analysis and processing
 */
package satelliteaod;

import java.io.*;
import ucar.nc2.*;

/**
 *
 * @author eqiu
 */
public class Reader {

    /**
     *
     * @deprecated: the directory is now loaded from the main class
     */
    @Deprecated
    static final File DIRECTORY = new File("/Users/eqiu/Desktop/GMU Summer Project/fwmerra2data/Addis Ababa");

    static File newDirectory;
    static String[] fileList;
    static NetcdfFile[] ncfileList;
    static String fileName;
    static int placeHolder;

    public static void init(File directory) {
        fileList = directory.list();
        newDirectory = directory;
        //fileName = directory;
    }

    public static void read() {
        NetcdfFile holder;
        ncfileList = new NetcdfFile[fileList.length];
        int counter = 0;
        try {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].contains(".nc")) {
                    fileName = newDirectory + "/" + fileList[i];
//                    System.out.println(fileName);
                    holder = NetcdfFile.open(fileName);
                    ncfileList[i] = holder;
                    new DataFile(holder, fileList[i]);
                    counter++;
                }
            }
        } catch (IOException ioe) {
            System.out.println("trying to open " + fileName);
        }
        System.out.println("Total items: " + counter);
    }

    public static void close() {
        for (int i = 0; i < fileList.length; i++) {
            if (null != ncfileList[i]) {
                try {
                    ncfileList[i].close();
                } catch (IOException ioe) {
                    System.out.println("trying to close " + fileList[0] + ioe);
                }
            }
        }
    }

    /**
     *
     * @return a list of Netcdf files
     * @deprecated use read() after putting in the directory with init(File
     * directory)
     */
    @Deprecated
    public static NetcdfFile[] readMyFile() {
        try {
            ncfileList = new NetcdfFile[fileList.length];
            for (int i = 0; i < fileList.length; i++) {
                fileName = DIRECTORY + "/" + fileList[i];
                System.out.println(fileName);
                ncfileList[i] = NetcdfFile.open(fileName);
//              System.out.println(ncfileList[i]);
            }
//            process();
        } catch (IOException ioe) {
            System.out.println("trying to open " + fileName);
        }
        System.out.println(ncfileList.length);
        return ncfileList;
    }

}
