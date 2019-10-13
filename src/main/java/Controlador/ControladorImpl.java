package Controlador;

import Modelo.CSV;
import Modelo.Fichero;
import Modelo.DAT;
import Modelo.FicheroCSV;
import Modelo.FicheroDat;
import Modelo.FicheroXML;
import Modelo.Modelo;
import Modelo.XML;
import Vista.Vista;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

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
                modelo.setRutas(vista.getRutaArchivoCsv(),vista.getArchivoBinario(),vista.getArchivoXML());
                modelo.trabajarFicheroCsv(vista.getColumnasCsv(), vista.getListaArchivos());
                modelo.trabajarFicheroDat();
                modelo.trabajarFicheroXml();
                modelo.extraerConsulta(vista.getRutaArchivoCsv(), 10);
                modelo.RealizarConsultas();
            } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
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
    

        
    
    
}
