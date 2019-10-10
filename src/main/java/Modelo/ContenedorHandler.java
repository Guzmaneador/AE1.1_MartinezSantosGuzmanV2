package Modelo;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Vulture
 */
public class ContenedorHandler extends DefaultHandler{
    private ArrayList<Contenedor> listadoContendores;
    private Contenedor contenedorActual;
    private StringBuilder textoEtiqueta;
    private String campo;
    
    @Override
    public void startDocument() throws SAXException{
        listadoContendores = new ArrayList<>();
        textoEtiqueta = new StringBuilder();
        
    }
    
    @Override
    public void startElement(String uri, String localName, String name, Attributes attributes)throws SAXException {
        if(localName.equals("contendor")){
            contenedorActual=new Contenedor();
            contenedorActual.setNota("xd");
        }
    };
    
    @Override
    public void endElement(String uri, String localName, String name)throws SAXException {
        if (this.contenedorActual != null) {

            if(localName.equals("nombreCalle")){
                contenedorActual.setTexto(textoEtiqueta.toString());
            }
            if(localName.equals("tipo")){
                contenedorActual.setTipo(textoEtiqueta.toString());
            }
            if(localName.equals("contendor")){
                listadoContendores.add(contenedorActual);
            }
        }
    };
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException{
        textoEtiqueta.setLength(0);
        if(this.contenedorActual !=null)
            textoEtiqueta.append(ch,start,length);
        
    }

    public ArrayList<Contenedor> getListadoContendores() {
        return listadoContendores;
    }
    
    
}
