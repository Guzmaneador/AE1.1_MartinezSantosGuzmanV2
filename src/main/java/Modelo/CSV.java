package Modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vulture
 */
public class CSV {
    String rutaCsvEscritura;
    List<String[]> datosCsv;

    CSVWriter escribirEnCsv;
    CSVReader leerDeCsv;

    public CSV(String rutaCsvLectura, String rutaCsvEscritura) {
        this.rutaCsvEscritura = rutaCsvEscritura;
        
        try {
            leerDeCsv = new CSVReader(new FileReader(rutaCsvLectura));
            escribirEnCsv = new CSVWriter(new FileWriter(rutaCsvEscritura));
        } catch (IOException ex) { System.out.println(ex);}
        
        
    }
    
    
    public void getCsv(String ruta) throws IOException{
        escribirEnCsv = new CSVWriter(new FileWriter(ruta));
        
    }
    
    public void introducirCamposColumna(String[] columnasCsv){
        
        escribirEnCsv.writeNext(columnasCsv);
    }
    
    public void leerCsv() throws IOException{
      datosCsv = leerDeCsv.readAll();

    }
    
    public void selecionarDatosCsv(String[] columnasCsv){
        /*Creamos un array con tantos espacion como culumnas vamos a tener en el nuevo 
        fichero csv de esta froma en el alamcenaremos la posicion de la columna que
        nos interesa en el archvio q vamos a copiar, le restamos uno por la columna 
        "TIPO" que vamos a agregar nosotros*/
        
        int[] numeroColumna = new int[columnasCsv.length-1];
        //solo nos interesa el primer array que contien los tipos de datos
        for (int i = 0; i < datosCsv.get(0).length; i++) {
            for (int j = 0; j < columnasCsv.length-1; j++) {
            
                if (datosCsv.get(0)[i].equals(columnasCsv[j])){
                    numeroColumna[j]=i;
                    break;
                }
            }//2ºFor 
        }//1ºFor
        for (int i = 0; i < numeroColumna.length; i++) {
            System.out.println(numeroColumna[i]);
            
        }
    }
    public void decirHola(){
        System.out.println("Hola");
    }
    
}
