package Modelo;

/**
 *
 * @author Vulture
 */
public class TiempoDeEjecucion {
        long inicio;// = System.currentTimeMillis();
         
        //Thread.sleep(2000);
         
        long fin;
         
        double tiempoTotal;
         
        //System.out.println(tiempo +" segundos");
        
        public void start(long tiempo){
            inicio = tiempo;
            
        }
        
        public String stop(long tiempo){
            fin = tiempo;
            tiempoTotal = (double) ((fin - inicio));
            return "--> Tiempo de ejecucion: "+tiempoTotal/1000+" Seg. ("+tiempoTotal+" miliseg)" ;
        }
}
