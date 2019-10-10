package Controlador;

import Modelo.CSV;
import Modelo.Consultas;
import Modelo.DAT;
import Modelo.Modelo;
import Modelo.XML;
import Vista.Vista;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author 2dama
 */
public class ControladorImpl implements Controlador {
        Modelo modelo ;
        Vista vista;
//        CSV csv ;
    
    public ControladorImpl(Modelo modelo, Vista vista){
        
        this.modelo=modelo;
        this.vista=vista;
        
    }
    
    @Override
    public void ejecucion(){
            try {
                pasarUrlAModelo();
                pasarRutaArchivosAModelor();
                
                modelo.descargarFichero("");
                modelo.csv.setRutaCsvEscritura(vista.getArchivoEscritura());
                modelo.csv.introducirCamposColumna(vista.getColumnasCsv());
                modelo.csv.setRutaCsvLectura(vista.getListaArchivos().get(0));
                modelo.csv.leerCsv();
                modelo.csv.selecionarDatosCsv(vista.getColumnasCsv());
                almacenarContenidoCsv(vista.getListaArchivos());
                modelo.csv.escribirCsv();               
                modelo.csv.finalizarLecturaCsv();
                modelo.csv.finalizarEscrituraCsv();
                
                DAT archivoDat = new DAT(vista.getArchivoBinario());
                archivoDat.obtenerContendoresDeCsv(vista.getArchivoEscritura());
                System.out.println(archivoDat.readString());
                
               XML xml = new XML();       
               xml.obtenerContendoresDeCsv(vista.getArchivoEscritura());
               xml.crearXML(vista.getArchivoXML());
               
               Consultas consulta = new Consultas();
               consulta.consultaCSV(vista.getArchivoEscritura());
               consulta.consultaDAT(vista.getArchivoBinario());
               
                
                


            } catch (IOException | ParserConfigurationException | TransformerException ex) {
                Logger.getLogger(ControladorImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    private void pasarUrlAModelo() throws IOException{
        ArrayList <String> listaUrls = vista.getListaUrls();
        for (int i = 0; i < listaUrls.size(); i++) {
            modelo.conectarUrl(listaUrls.get(i));
        }
        
    }
    private void pasarRutaArchivosAModelor() throws IOException{
        ArrayList <String> listaArchivos = vista.getListaArchivos();
        for (int i = 0; i < listaArchivos.size(); i++) {
            modelo.introducirFicheroEnLista(listaArchivos.get(i));
            modelo.crearFichero(listaArchivos.get(i));
        }
    }
    
    private void almacenarContenidoCsv(ArrayList<String> listaArchivos) throws IOException{
        for (int i = 0; i < listaArchivos.size(); i++) {
                modelo.csv.setRutaCsvLectura(vista.getListaArchivos().get(i));
                modelo.csv.obtenerDatosCsv(vista.getColumnasCsv());  
        }
        
    }
    
}
