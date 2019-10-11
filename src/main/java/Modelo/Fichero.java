package Modelo;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Vulture
 */
public class Fichero {
    ArrayList<String> listaConsultas = new ArrayList<>(); 
    String [] consultas = {"AVDA BENITO PEREZ ARMAS","RAMBLA DE SANTA CRUZ","CALLE MIRAFLORES"};
                            //       carton 8      ||        vidrio  3    ||       envases 2
    Random aleatorio = new Random();
    CSVReader leerDeCsv;
    RandomAccessFile leerDeDat;
    DocumentBuilderFactory factory;
    DocumentBuilder docBuilder;
    Document documentoDOM;
    XMLReader procesadorXML ;    
    List<Contenedor> listaContenedores ;
    
    int papelCarton, vidrio,envases;
    TiempoDeEjecucion tiempo = new TiempoDeEjecucion();

    public Fichero() {
        this.papelCarton = 0;
        this.vidrio = 0;
        this.envases = 0;
    }
     public void consultaCSV(String rutaCsv)throws FileNotFoundException, IOException{
         
     }
     public void consultaXML(String rutaXml) throws ParserConfigurationException, SAXException, IOException{
     }
     public void xmlDOM(String rutaXml) throws ParserConfigurationException, SAXException, IOException{
     }
     public void xmlSAX(String rutaXml) throws SAXException, IOException{
         
     }
     public void consultaDAT(String rutaDat) throws IOException{      
     }
    public void examinarDAT(String rutaDat,String consulta) throws FileNotFoundException, IOException{    
    }

//    
//    public void consultaDAT(String rutaDat) throws IOException{
//        System.out.println("-------Consulta DAT------------");
//        tiempo.start(System.currentTimeMillis());
//        for (String consulta : consultas) {
//            examinarDAT(rutaDat,consulta);
//        }
//        System.out.println(tiempo.stop(System.currentTimeMillis()));
//        leerDeDat.close();
//    }
//    
//    public void examinarDAT(String rutaDat,String consulta) throws FileNotFoundException, IOException{
//
//        restaurarContadores();
//        int tamañoContenedor =420;
//        int tamañoCampo=60;
//        leerDeDat= new RandomAccessFile(rutaDat,"rw");
//        
//        int puntero=0;
//        int contador =0;
//        char campo[] = new char[tamañoCampo];
//        char campo2[] = new char[tamañoContenedor];
//        campo[0]= 1;
//        String calle =" ";
//        String tipo =" ";
//         
//          while((!calle.equals("CALLE DONIS")) || (!tipo.equals("Envases"))){
//               
//
//            puntero = contador * (tamañoContenedor*2);
//            
//            leerDeDat.seek(puntero+(tamañoCampo*2));//sumamos al puntero el tamaño de campo por dos ya que seria la segunda posicion donde se escuentra el nombre de la calle
//            for (int i=0; i<tamañoCampo; i++){
//                campo[i]=leerDeDat.readChar();      
//            }
//            String[] soloCalle= new String(campo).replace('\0','1').split("1");
//
//                calle=soloCalle[0];
//            ////////////////////////
//            puntero = contador * (tamañoContenedor*2);
//           leerDeDat.seek(puntero);//sumamos al puntero el tamaño de campo por dos ya que seria la segunda posicion donde se escuentra el nombre de la calle
//            for (int i=0; i<tamañoContenedor; i++){
//                campo2[i]=leerDeDat.readChar();      
//            }
//            String[] soloTexto= new String(campo2).replace('\0','1').split("1");
//
//                tipo=soloTexto[soloTexto.length-1];
//                
//                if(calle.equals(consulta)){
//                    contarTipo(tipo);
//                }
//            contador++;
//         }
//            System.out.println("La calle "+consulta+" tiene: "+papelCarton+" contenedores de papel y carton, "
//            +vidrio+" contenedores de vidiro y "+envases+" contendores de envases");
//            restaurarContadores();
//
//        //return new String(campo).replace('\0',' ');
////        } 
//        
//    }

    
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
    
    public void restaurarContadores(){
        papelCarton=0;
        vidrio=0;
        envases=0;
        
    }
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
}
