package Modelo;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Vulture
 */
public class Consultas {
    String [] consultas = {"AVDA BENITO PEREZ ARMAS","RAMBLA DE SANTA CRUZ","CALLE MIRAFLORES"};
                            //       carton 8      ||        vidrio  3    ||       envases 2
    CSVReader leerDeCsv;
    int papelCarton, vidrio,envases;

    public Consultas() {
        this.papelCarton = 0;
        this.vidrio = 0;
        this.envases = 0;
    }
    
    
    public void consultaCSV(String rutaCsv) throws FileNotFoundException, IOException{
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
    public void consultaDAT(){
        
    }
    
    public void consultaXML(){

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
