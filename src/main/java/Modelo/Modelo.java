package Modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author 2dama
 */
public interface Modelo {


    
     public void introducirFicheroEnLista(String nombre);
     
     public void crearFichero(String nombre)throws IOException;
     
     public void conectarUrl(String url) throws MalformedURLException, IOException;
     
     public void descargarFichero(String rutaDescarga) throws IOException;
     
     public void extraerConsulta(String rutaCsv,int numeroConsultas) throws IOException;
     
     public ArrayList<String> getListaConsultas();
     
     public void setRutas(String rutaCsv,String rutaDat,String rutaXml);
     
     public void trabajarFicheroCsv(String[] columnasCsv,ArrayList<String> listaArchivos) throws IOException;
     
     public void trabajarFicheroDat() throws FileNotFoundException, IOException;
     
     public void trabajarFicheroXml() throws ParserConfigurationException, IOException, TransformerException;
     
     public void RealizarConsultas() throws IOException, SAXException, ParserConfigurationException;
}
