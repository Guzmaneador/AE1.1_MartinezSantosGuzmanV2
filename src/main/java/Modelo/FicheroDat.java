package Modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author 2dama
 */
public class FicheroDat extends Fichero{
    
    
    
    @Override
    public void consultaDAT(String rutaDat) throws IOException{
        System.out.println("-------Consulta DAT------------");
        tiempo.start(System.currentTimeMillis());
        for (String consulta : consultas) {
            examinarDAT(rutaDat,consulta);
        }
        System.out.println(tiempo.stop(System.currentTimeMillis()));
        leerDeDat.close();
    }
    
    @Override
    public void examinarDAT(String rutaDat,String consulta) throws FileNotFoundException, IOException{

        restaurarContadores();
        int tamañoContenedor =420;
        int tamañoCampo=60;
        leerDeDat= new RandomAccessFile(rutaDat,"rw");
        
        int puntero=0;
        int contador =0;
        char campo[] = new char[tamañoCampo];
        char campo2[] = new char[tamañoContenedor];
        campo[0]= 1;
        String calle =" ";
        String tipo =" ";
         
//          while((!calle.equals("CALLE DONIS")) || (!tipo.equals("Envases"))){
          while(contador != 4902){
               

            puntero = contador * (tamañoContenedor*2);
            
            leerDeDat.seek(puntero+(tamañoCampo*2));//sumamos al puntero el tamaño de campo por dos ya que seria la segunda posicion donde se escuentra el nombre de la calle
            for (int i=0; i<tamañoCampo; i++){
                campo[i]=leerDeDat.readChar();      
            }
            String[] soloCalle= new String(campo).replace('\0','1').split("1");

                calle=soloCalle[0];
            ////////////////////////
            puntero = contador * (tamañoContenedor*2);
           leerDeDat.seek(puntero);//sumamos al puntero el tamaño de campo por dos ya que seria la segunda posicion donde se escuentra el nombre de la calle
            for (int i=0; i<tamañoContenedor; i++){
                campo2[i]=leerDeDat.readChar();      
            }
            String[] soloTexto= new String(campo2).replace('\0','1').split("1");

                tipo=soloTexto[soloTexto.length-1];
                
                if(calle.equals(consulta)){
                    contarTipo(tipo);
                }
            contador++;
         }
            System.out.println("La calle "+consulta+" tiene: "+papelCarton+" contenedores de papel y carton, "
                    +vidrio+" contenedores de vidiro , "+envases+" contendores de envases"
                    + ", "+electricos+" contendores de Electricos y "+solidos+" contendores de solidos");
            restaurarContadores();

        //return new String(campo).replace('\0',' ');
//        } 
        
    }
    
}
