package Modelo;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2dama
 */
public class ModeloImpl implements Modelo{


    
  
    ArrayList <File> listaFicheros;
    ArrayList <URLConnection> conexionesUrl;
    InputStream miInputStram;
    FileOutputStream miFileOutputStream;
        Random aleatorio = new Random();
        ArrayList<String> listaConsultas = new ArrayList<>(); 
        CSVReader leerDeCsv;

    File directorioDescargas=new File("src\\main\\java\\datosDescargados");
    File directorioDatosTratados=new File("src\\main\\java\\datosTratados");
    
    public CSV csv ;
    private String rutaCsv, rutaDat, rutaXml;
   

    public ModeloImpl() {
        csv =new CSV();
        directorioDescargas.mkdir();
        directorioDatosTratados.mkdir();
        listaFicheros= new ArrayList<>();
        conexionesUrl = new ArrayList<>();

    }
    
    
    @Override
    public void conectarUrl(String url) throws MalformedURLException, IOException{
        conexionesUrl.add((new URL (url)).openConnection());
    }
    
    @Override
    public void introducirFicheroEnLista(String nombre){
        listaFicheros.add(new File(nombre));
    }
    
    @Override
    public void crearFichero(String nombre) throws IOException{
            File fichero = new File (nombre);
            if (!fichero.exists()) 
                fichero.createNewFile();
    }
    
      
    @Override
    public void descargarFichero(String rutaDescarga) throws IOException{
        for (int i = 0; i < listaFicheros.size(); i++) {
 
            miInputStram = conexionesUrl.get(i).getInputStream();
            miFileOutputStream =  new FileOutputStream(listaFicheros.get(i).getAbsolutePath());//rutaDescarga
        
            byte [] array = new byte[1000];
            // Primera lectura y bucle hasta el final
            int leido = miInputStram.read(array);
            while (leido > 0) {
                miFileOutputStream.write(array,0,leido);
                leido=miInputStram.read(array);
            }
            // Cierre de conexion y fichero.
            miInputStram.close();
            miFileOutputStream.close();
        }
    }

    @Override
    public void extraerConsulta(String rutaCsv,int numeroConsultas) throws IOException {
            System.out.println("------Consultas-----");
                for (int i = 0; i < numeroConsultas; i++) {
                    obtenerConsulta(rutaCsv);
                }
    }
       
    public void obtenerConsulta(String rutaCsv) throws FileNotFoundException, IOException{

               
        int contador=0;
        String[] fila = null;
        
            int poscion = aleatorio.nextInt(1142)+2;
        
         leerDeCsv = new CSVReader(new FileReader(rutaCsv));
            while ((fila = leerDeCsv.readNext()) != null) {
                if (poscion==contador) {
                    System.out.println(fila[1]);
                    listaConsultas.add(fila[1]);
                    break;
                }
                contador++;
            }
            leerDeCsv.close();
        
    }

    public ArrayList<String> getListaConsultas() {
        return listaConsultas;
    }
    
    @Override
    public void setRutas(String rutaCsv,String rutaDat,String rutaXml){
        this.rutaCsv=rutaCsv;
        this.rutaDat=rutaDat;
        this.rutaXml=rutaXml;
    }

    public void trabajarFicheroCsv(String[] columnasCsv,ArrayList<String> listaArchivos) throws IOException{
                csv.setRutaCsvEscritura(rutaCsv);
                csv.introducirCamposColumna(columnasCsv);
                csv.setRutaCsvLectura(listaArchivos.get(0));
                csv.leerCsv();
                csv.selecionarDatosCsv(columnasCsv);
                almacenarContenidoCsv(columnasCsv,listaArchivos);
                csv.escribirCsv();               
                csv.finalizarLecturaCsv();
                csv.finalizarEscrituraCsv();
        
    }
        private void almacenarContenidoCsv(String[] columnasCsv,ArrayList<String> listaArchivos) throws IOException{
        for (int i = 0; i < listaArchivos.size(); i++) {
                csv.setRutaCsvLectura(listaArchivos.get(i));
                csv.obtenerDatosCsv(columnasCsv);  
        }
        
    }

   
}
