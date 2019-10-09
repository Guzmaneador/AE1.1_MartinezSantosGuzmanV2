package Modelo;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author 2dama
 */
public class XML {
    DocumentBuilder docBuilder;
    DOMImplementation implementation ;
    Document documentoXML;

    public XML() throws ParserConfigurationException {
        docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        implementation = docBuilder.getDOMImplementation();
        documentoXML = implementation.createDocument(null, "Contendores", null);
        documentoXML.setXmlVersion("1.0");
        documentoXML.setXmlStandalone(true);
    }
    
     public void obtenerContendoresDeCsv(String rutaCsv) throws IOException{
        CSVReader csvReader = new CSVReader(new FileReader(rutaCsv));
        String[] fila = null;
        while((fila = csvReader.readNext()) != null) {
            Element contenedor = documentoXML.createElement("contendor");
            contenedor.setAttribute("tipo=", fila[6]);
            documentoXML.getDocumentElement().appendChild(contenedor);
            
            //Creamos las etiquetas
            Element codCalle = documentoXML.createElement("codcalle");
            Text valorCodCalle = documentoXML.createTextNode(fila[0]);
            contenedor.appendChild(codCalle);
            codCalle.appendChild(valorCodCalle);
            
            Element nombreCalle = documentoXML.createElement("nombreCalle");
            Text valorNombreCalle = documentoXML.createTextNode(fila[1]);
            contenedor.appendChild(nombreCalle);
            nombreCalle.appendChild(valorNombreCalle);
            
            Element descripcion = documentoXML.createElement("descripcion");
            Text valorDescripcion = documentoXML.createTextNode(fila[5]);
            contenedor.appendChild(descripcion);
            descripcion.appendChild(valorDescripcion);
            
            Element utmX = documentoXML.createElement("utm_X");
            Text valorUtmX = documentoXML.createTextNode(fila[3]);
            contenedor.appendChild(utmX);
            utmX.appendChild(valorUtmX);
            
            
            Element utmY = documentoXML.createElement("utm_Y");
            Text valorUtmY = documentoXML.createTextNode(fila[4]);
            contenedor.appendChild(utmY);
            utmY.appendChild(valorUtmY);
            
            //Introducimos las etiqueta
            
            
            
            
            
        }

            csvReader.close();
    }
    
    
}
