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

    public DAT(String ruta) throws FileNotFoundException {
        this.ficheroBinario = new File(ruta);
        this.randomBinario = new RandomAccessFile(ficheroBinario,"rw");

        
    }
    /*
    Se encarga de leer el fichero csv con todos los contendores y una vez hecho esto 
    crea un ubjeto contendor para cada linia del csv usando cada campo como atrivuto 
    */
//    public void obtenerContendoresDeCsv(String rutaCsv) throws IOException{
//        CSVReader csvReader = new CSVReader(new FileReader(rutaCsv));
//        String[] fila = null;
//        while((fila = csvReader.readNext()) != null) {
//            listaContenedores.add(new Contenedor(
//                     Float.parseFloat(fila[0]),
//                    fila[1],
//                     Long.parseLong(fila[2]),
//                     Long.parseLong(fila[3]),
//                     Long.parseLong(fila[4]),
//                    fila[5],fila[6]));
//            }
//
//            csvReader.close();
//    }
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
    //Cada contendor pesa 448bait
    public void write (String datoContendor) throws IOException   {
//        
//             writeString (randomBinario, getNombre(),32);
//             writeString (randomBinario, getTelefono(),16);
//             writeString (randomBinario, getEmail(),32);
             writeString (randomBinario, datoContendor, 64);
      } 
    private void writeString (RandomAccessFile file, String str, int dim)throws IOException { 
        StringBuffer buffer = new StringBuffer();
        if (str!=null) 
            buffer.append(str);
        buffer.setLength(dim);
        file.writeChars(buffer.toString());
    } 
    //LECTURA//
//    public void read (RandomAccessFile file) throws IOException, java.text.ParseException {
//        DateFormat df;
//        setNombre( readString (file, 32) );
//        setTelefono( readString (file, 16) );
//        setEmail ( readString (file, 32) );     setDireccion ( readString (file, 64) );     df = DateFormat.getDateInstance(DateFormat.LONG);     setNacimiento ( df.parse(readString(file,32)) );     setGrupo(file.readInt());     setDeuda(file.readDouble()); 
//        }   
    private String readString (RandomAccessFile file, int dim) throws IOException {
        char campo[] = new char[dim];
        for (int i=0; i<dim; i++)
        campo[i] = file.readChar();
        return new String(campo).replace('\0',' ');   
    }
}
//Usar el sheck para colocar el puntero antes de scribir un registro