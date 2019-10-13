package Modelo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author 2dama
 */
public interface Modelo {

    /**
     *
     */
     public  CSV csv  =new CSV();
    
     public void introducirFicheroEnLista(String nombre);
     
     public void crearFichero(String nombre)throws IOException;
     
     public void conectarUrl(String url) throws MalformedURLException, IOException;
     
     public void descargarFichero(String rutaDescarga) throws IOException;
     
     public void extraerConsulta(String rutaCsv,int numeroConsultas) throws IOException;
     
     public ArrayList<String> getListaConsultas();
     
     public void setRutas(String rutaCsv,String rutaDat,String rutaXml);
}
