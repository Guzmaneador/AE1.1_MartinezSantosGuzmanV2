/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;



/**
 *
 * @author 2dama
 */
public class FicheroXML extends Fichero {
       
      @Override
    public void xmlDOM(String rutaXml) throws ParserConfigurationException, SAXException, IOException{
        factory = DocumentBuilderFactory.newInstance();
        docBuilder = factory.newDocumentBuilder();
        restaurarContadores();
        
        System.out.println("-------Consulta DOM-XML------------");
        tiempo.start(System.currentTimeMillis());

        for (String consulta : consultas) {
            documentoDOM = docBuilder.parse(new File(rutaXml));
            
            NodeList listaContendorNode =documentoDOM.getElementsByTagName("contendor");
            //obtnemos el numero de contendores y creamos un bucleo q los recorra todos
            for (int i = 0; i <listaContendorNode.getLength(); i++) {
                Node contendorNode = listaContendorNode.item(i);
                if(contendorNode.getNodeType() == Node.ELEMENT_NODE){
                    Element contendorElement = (Element) contendorNode;
                    if(contendorElement.getElementsByTagName("nombreCalle").item(0).getTextContent().equals(consulta)){
                        contarTipo(contendorElement.getElementsByTagName("tipo").item(0).getTextContent());
                    }
                }
            }
            System.out.println("La calle "+consulta+" tiene: "+papelCarton+" contenedores de papel y carton, "
                    +vidrio+" contenedores de vidiro , "+envases+" contendores de envases"
                    + ", "+electricos+" contendores de Electricos y "+solidos+" contendores de solidos");
            restaurarContadores();
        }//fin cunsulta
        System.out.println(tiempo.stop(System.currentTimeMillis()));

        
    }
    
      @Override
    public void xmlSAX(String rutaXml) throws SAXException, IOException{
        procesadorXML = XMLReaderFactory.createXMLReader();
        ContenedorHandler miContenedorHandler = new ContenedorHandler();
        procesadorXML.setContentHandler(miContenedorHandler);
        
        procesadorXML.parse(new InputSource(rutaXml));
        listaContenedores = miContenedorHandler.getListadoContendores();
        
        System.out.println("-------Consulta DOM-SAX------------");
        restaurarContadores();

        tiempo.start(System.currentTimeMillis());

        for (String consulta : consultas) {
            for (Contenedor Contendor : listaContenedores) {         
                if(Contendor.getTexto().equals(consulta)){
                    contarTipo(Contendor.getTipo());
                }
            }
            System.out.println("La calle "+consulta+" tiene: "+papelCarton+" contenedores de papel y carton, "
                    +vidrio+" contenedores de vidiro , "+envases+" contendores de envases"
                    + ", "+electricos+" contendores de Electricos y "+solidos+" contendores de solidos");
            restaurarContadores();
        }
        System.out.println(tiempo.stop(System.currentTimeMillis()));

        
    }
    
}
