package Modelo;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vulture
 */
public class CSV {
    String rutaCsv;

    CSVWriter escribirEnCsv;

    public CSV(String rutaCsv) {
        this.rutaCsv = rutaCsv;
        try {
            escribirEnCsv = new CSVWriter(new FileWriter(rutaCsv));
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    public void getCsv(String ruta) throws IOException{
        escribirEnCsv = new CSVWriter(new FileWriter(ruta));
        
    }
    
    public void introducirCampos(String[] columnasCsv){
        
        escribirEnCsv.writeNext(columnasCsv);
    }
    
    
}
