package Modelo;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vulture
 */
public class DAT {
    File ficheroBinario;
    List<Contenedor> listaContenedores = new ArrayList<>();
    RandomAccessFile randomBinario;
    int tamañoContenedor;
    int tamañoCampo;
    ArrayList<Integer> posicionesContenedor;

    public DAT(String ruta) throws FileNotFoundException {
        this.posicionesContenedor = new ArrayList<>();
        this.ficheroBinario = new File(ruta);
        this.randomBinario = new RandomAccessFile(ficheroBinario,"rw");
        tamañoContenedor =420;
        tamañoCampo=60;

        
    }

    public void obtenerContendoresDeCsv(String rutaCsv) throws IOException{
        CSVReader csvReader = new CSVReader(new FileReader(rutaCsv));
        String[] fila = null;
        while((fila = csvReader.readNext()) != null) {
            for (int i = 0; i < fila.length; i++) {
             
                write(fila[i]);
             
            }
        }

            csvReader.close();
    }
    

    public void write (String datoContendor) throws IOException   {
             writeString (randomBinario, datoContendor,tamañoCampo);
      } 
    private void writeString (RandomAccessFile file, String str, int dim)throws IOException { 
        StringBuffer buffer = new StringBuffer();
        if (str!=null) 
            buffer.append(str);
        buffer.setLength(dim);
        file.writeChars(buffer.toString());
    } 



    public String readString (int posicion) throws IOException {
        int puntero;
        char campo[] = new char[tamañoContenedor];

            puntero = posicion * (tamañoContenedor*2);
            randomBinario.seek(puntero);
            for (int i=0; i<tamañoContenedor; i++){
                campo[i]=randomBinario.readChar();
            }              
     
        return new String(campo).replace('\0',' ');
    }
}
//Usar el sheck para colocar el puntero antes de scribir un registro