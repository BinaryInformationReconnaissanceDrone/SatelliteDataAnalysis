/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

import static com.sun.activation.registries.LogSupport.log;
import java.io.*;
import ucar.ma2.*;
import ucar.nc2.*;
import java.util.*;

/**
 *
 * @author Ethan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Double> analysis = new ArrayList<>();
        Feeder.init();
        analysis = Feeder.getAOD();
        for (double d : analysis)
            System.out.println(d);
    }

}
