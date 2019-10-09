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
        tamañoContenedor =322;
        tamañoCampo=46;

        
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
    
    //ESCRITURA///
    public void write (String datoContendor) throws IOException   {
             writeString (randomBinario, datoContendor, 46);
      } 
    private void writeString (RandomAccessFile file, String str, int dim)throws IOException { 
        StringBuffer buffer = new StringBuffer();
        if (str!=null) 
            buffer.append(str);
        buffer.setLength(dim);
        file.writeChars(buffer.toString());
    } 
    //LECTURA//

//    public String readString () throws IOException {
//        int puntero;
//        int contador =0;
//        char campo[] = new char[tamañoCampo];
//        //for (Integer numeroCon : posicionesContenedor) {
//                listaContenedores.add(new Contenedor());
//            //puntero = numeroCon * tamañoContenedor;
//            puntero = 10 * tamañoContenedor;
//            randomBinario.seek(puntero+tamañoCampo);
//            for (int i=0; i<tamañoContenedor; i++){
//                campo[i]=randomBinario.readChar();
//                
//            }
//            contador++;
//         //}
//        return new String(campo).replace('\0',' ');
//    }
    public String readString () throws IOException {
        int puntero;
        char campo[] = new char[tamañoContenedor];
        //for (Integer numeroCon : posicionesContenedor) {
            //puntero = numeroCon * tamañoContenedor;
            puntero = 10 * tamañoContenedor;
            randomBinario.seek(puntero);
            for (int i=0; i<tamañoContenedor; i++){
                campo[i]=randomBinario.readChar();
            }              
         //}
        return new String(campo).replace('\0',' ');
    }
}
//Usar el sheck para colocar el puntero antes de scribir un registro