/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package satelliteaod;

import static com.sun.activation.registries.LogSupport.log;
import java.io.IOException;
import ucar.ma2.Array;
import ucar.ma2.Index;
import ucar.ma2.InvalidRangeException;
import ucar.nc2.NCdumpW;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

/**
 *
 * @author Ethan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String filename = "D:\\Downloads\\fwmerra2data\\Nairobi\\Nairobi\\MERRA2_100.instM_2d_gas_Nx.198001.SUB.nc";
        NetcdfFile ncfile = null;
        try {
            ncfile = NetcdfFile.open(filename);
        //process( ncfile);
        } catch (IOException ioe) {
            System.out.println("trying to open " + filename+ ioe);
        } finally { 
            if (null != ncfile) 
                try {
                    ncfile.close();
                } catch (IOException ioe) {
                System.out.println("trying to close " + filename+ ioe);
                }
        }
        
        
        String varName = "time"; 
        Variable v = ncfile.findVariable("time");
        if (null == v) return;
        System.out.println("f");
        try {
            Array data = v.read("0:2:1, 0:19:1");
            NCdumpW.printArray(data, varName,
            System.out, null);
        } catch (IOException ioe) {
            System.out.println("trying to read " + varName+ ioe);

        } catch (InvalidRangeException e) {
            log("invalid Range for " + varName, e);
        }
        try{
            Array data = v.read("2,0:499,0:719");
            System.out.println(data.getByte(Index.scalarIndexImmutable));
        }
        catch(Exception e){System.out.println("you caught me");}
    }
    
}
