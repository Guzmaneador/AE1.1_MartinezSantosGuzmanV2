package Modelo;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author 2dama
 */
public class FicheroCSV extends Fichero{

    public FicheroCSV() {
        super();
    }
    
//    super.
  
    @Override
        public void consultaCSV(String rutaCsv) throws FileNotFoundException, IOException{
        System.out.println("-------Consulta CSV------------");
        tiempo.start(System.currentTimeMillis());
        restaurarContadores();
        
        String[] fila = null;
        
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
        System.out.println(tiempo.stop(System.currentTimeMillis()));
        
    }  
    
    
    
    
}
