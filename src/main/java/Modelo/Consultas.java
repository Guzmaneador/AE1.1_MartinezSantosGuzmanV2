package Modelo;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Vulture
 */
public class Consultas {
    String [] consultas = {"AVDA BENITO PEREZ ARMAS","RAMBLA DE SANTA CRUZ","CALLE MIRAFLORES"};
                            //       carton 8      ||        vidrio  3    ||       envases 2
    CSVReader leerDeCsv;
    RandomAccessFile leerDeDat;
    DocumentBuilderFactory factory;
    DocumentBuilder docBuilder;
    Document documentoDOM;
    int papelCarton, vidrio,envases;

    public Consultas() {
        this.papelCarton = 0;
        this.vidrio = 0;
        this.envases = 0;
    }
    
    
    public void consultaCSV(String rutaCsv) throws FileNotFoundException, IOException{
        System.out.println("-------Consulta CSV------------");
        restaurarContadores();
        
        String[] fila = null;
        int contador=0;
        for (String consulta : consultas) {
            leerDeCsv = new CSVReader(new FileReader(rutaCsv));
            while ((fila = leerDeCsv.readNext()) != null) {
                if (fila[1].equals(consulta)) {
                    contarTipo(fila[6]);
                }
            }
            System.out.println("La calle "+consulta+" tiene: "+papelCarton+" contenedores de papel y carton, "
                    +vidrio+" contenedores de vidiro y "+envases+" contendores de envases");
            restaurarContadores();
            leerDeCsv.close();
        }
        
    }
    
    //En los ficheros impares hay que 
    public void consultaDAT(String rutaDat) throws FileNotFoundException, IOException{
        System.out.println("-------Consulta DAT------------");
        restaurarContadores();
        int tamañoContenedor =420;
        int tamañoCampo=60;
        leerDeDat= new RandomAccessFile(rutaDat,"rw");
        
         int puntero;
        int contador =0;
        char campo[] = new char[tamañoCampo];
       // for (String consulta : consultas) {
            for (int j = 0; j < 1; j++) {
            puntero = contador * tamañoContenedor;
            
            leerDeDat.seek(puntero+(tamañoCampo));//sumamos al puntero el tamaño de campo por dos ya que seria la segunda posicion donde se escuentra el nombre de la calle
            for (int i=0; i<tamañoCampo; i++){
                campo[i]=leerDeDat.readChar();
                
            }
            System.out.println(new String(campo).replace('\0',' '));
            contador++;
         //}
        //return new String(campo).replace('\0',' ');
        } 
        
    }
    
    public void consultaXML(String rutaXml) throws ParserConfigurationException, SAXException, IOException{
        xmlDOM(rutaXml);
    }
    
    
    public void xmlDOM(String rutaXml) throws ParserConfigurationException, SAXException, IOException{
        factory = DocumentBuilderFactory.newInstance();
        docBuilder = factory.newDocumentBuilder();
        for (String consulta : consultas) {
            documentoDOM = docBuilder.parse(new File(rutaXml));
        }
        
    }
    
    
    public void contarTipo(String tipo){
        if(tipo.equals("Papel_Carton")){
            papelCarton++;    
        }else if(tipo.equals("Envases")){
            envases++;
        }else if(tipo.equals("Vidrio")){
            vidrio++;
        }else {
            System.out.println("Parametro introducido no admitido.");
        }
        
    }
    
    private void restaurarContadores(){
        papelCarton=0;
        vidrio=0;
        envases=0;
        
    }
}
