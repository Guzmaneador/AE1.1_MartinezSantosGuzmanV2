package Modelo;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Vulture
 */
public class CSV {
    String rutaCsvEscritura;
    String rutaCsvLectura;
    
    List<String[]> datosCsv;
    List<String[]> datosCsvEscribir;

    CSVWriter escribirEnCsv;
    CSVReader leerDeCsv;
    

    int[] posicionColumna ;

    public CSV() {
        datosCsvEscribir =new  ArrayList<>();
    }

    public CSV(String rutaCsvLectura, String rutaCsvEscritura) {
        this.rutaCsvEscritura = rutaCsvEscritura;
        this.rutaCsvLectura = rutaCsvLectura;
        
        try {
            
            leerDeCsv = new CSVReader(new FileReader(rutaCsvLectura));
            escribirEnCsv = new CSVWriter(new FileWriter(rutaCsvEscritura));
        } catch (IOException ex) { System.out.println(ex);}
        
        
    }
    
    
////////////////////
    public void setRutaCsvEscritura(String rutaCsvEscritura) throws IOException{
        this.rutaCsvEscritura = rutaCsvEscritura ;
        escribirEnCsv = new CSVWriter(new FileWriter(rutaCsvEscritura, true));
    }
    
    public void setRutaCsvLectura(String rutaCsvLectura) throws IOException{
        this.rutaCsvLectura = rutaCsvLectura;
        leerDeCsv = new CSVReader(new FileReader(rutaCsvLectura));

       
        
    }
/////////////////
    public void introducirCamposColumna(String[] columnasCsv){
          datosCsvEscribir.add(columnasCsv);
//        escribirEnCsv.writeNext(columnasCsv,true);
//        System.out.println("hola");
    }
    

         /*Creamos un array con tantos espacion como culumnas vamos a tener en el nuevo 
        fichero csv de esta froma en el alamcenaremos la posicion de la columna que
        nos interesa en el archvio q vamos a copiar, le restamos uno por la columna 
        "TIPO" que vamos a agregar nosotros*/
    
    public void selecionarDatosCsv(String[] columnasCsv){
        
        posicionColumna = new int[columnasCsv.length-2];
        //solo nos interesa el primer array que contien los tipos de datos
        for (int i = 0; i < datosCsv.get(0).length; i++) {
            for (int j = 0; j < columnasCsv.length-2; j++) {
            
                if (datosCsv.get(0)[i].equals(columnasCsv[j])){
                    posicionColumna[j]=i;
                    break;
                }
            }//2ºFor 
        }//1ºFor
        for (int i = 0; i < posicionColumna.length; i++) {
            System.out.println(posicionColumna[i]);
            
        }
    }
    
    public void finalizarLecturaCsv() throws IOException{
        leerDeCsv.close();
    }
    public void finalizarEscrituraCsv() throws IOException{
        escribirEnCsv.close();
    }
    //LECTURA Y ESCRITURA 
    public void leerCsv() throws IOException{
      datosCsv = leerDeCsv.readAll();
      

    }
    public void obtenerDatosCsv(String[] columnasCsv) throws IOException{
        String[] fila = null;
        String[] contenido = new String[columnasCsv.length];
        int contador=0;
        while((fila = leerDeCsv.readNext()) != null) {
            if(contador!=0){
                for (int i = 0; i < posicionColumna.length; i++) {
                    contenido[i] = fila[posicionColumna[i]];
                   contenido[contenido.length-1]=optenerTipo();
                   //System.out.println(contenido[i]);
                }
                 System.out.println(contenido[1]);
                //datosCsvEscribir.add(contenido);
                escribirEnCsv.writeNext(contenido,true);

            }
            contador++;
        }
    }
    
    
    public void escribirCsv() throws IOException{     
        //escribirEnCsv.writeAll(datosCsvEscribir);     
    }
    
    public String optenerTipo(){
        
        String[] fichero = rutaCsvLectura.split(Pattern.quote("\\"));
        String[] ficheroSinExtension = fichero[fichero.length-1].split(Pattern.quote("."));
        return ficheroSinExtension[0];
    }
    
}
